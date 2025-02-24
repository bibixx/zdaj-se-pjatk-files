
  /*
"We should forget about small efficiencies, say about 97% of the time: 
premature optimization is the root of all evil." 
-Donald Knuth 
This source code has not been optimized for speed or efficiency and 
is not suitable for production. The intended purpose was to create 
a step-by-step illustration of BST self-adjusting algorithms.

*/

class BSTree
{
   public static final int INSERT  = +1;
   public static final int DELETE  = -1;

   public static final int FINDMAX = +1;
   public static final int FINDMIN = -1;

   BTNode root;
// results of the last locate()
   BTNode lastnode; // last node on the search path
   int nextside;    // side of a new child

   public BSTree()
   {
     root = null;
   }

// BST methods

// returns located node or null if not found
// saves last node on the search path and the side on the next node
// for other methods
   public BTNode locate(BTData data)
   {
     BTNode node = root;
     BTNode next = null;
     int side = 0;

     while(node != null)
     {
       side = node.compareTo(data);
       next = node.getChild(side);
       if(next == node || next == null)
         break;
       node = next;
     }
     lastnode = node;
     nextside = side;
     return next;
}

// create a leaf and attach it to the specified node on the specified side
// method can be used on an empty tree as well
   public BTNode add(BTNode node, int side, BTData data)
   {
     BTNode newnode = new BTNode(data);
     link(node, side, newnode);
     return newnode;
   }

// remove the node from the tree
// the return value is the parent of the removed node
   public BTNode remove(BTNode node)
   {
     int side;
     BTNode child, parent;

     child = node.getChild(); // single child
     parent = node.getParent();
     side = node.getSide();
     link(parent, side, child);
     destroy(node);
     return parent;
   }

// perform cleanup here (free memory etc)
// in Java, release node pointer for garbage collection
   public void destroy(BTNode node)
   {
     node = null;
   }

// if the node has two children
// swap the nodes before delete()
// does not apply to splay trees
   public BTNode swap(BTNode node, int minmax)
   {
     BTNode temp = node;
     BTNode swap = (minmax == FINDMAX) ? node.prevInO() : node.nextInO();
     swapData(node, swap); // swap data first
     node = swap;          // now swap nodes
     swap = temp;
     return node;
   }

// helper method for swap()
   public void swapData(BTNode node1, BTNode node2)
   {
     BTData data;
     data = node1.getData();
     node1.setData(node2.getData());
     node2.setData(data);
   }

// rotate() links the participating nodes and 
// returns the node which is on top after the rotation.
// the 'node' is rotated down and to the specified 'side'
   public BTNode rotate(BTNode node, int side)
   {
     BTNode parent = node.getParent();    // may be null
     BTNode child = node.getChild(-side); // never null
     BTNode grand = child.getChild(side); // may be null

     link(node, -side, grand);
     link(parent, node.getSide(), child);
     link(child, side, node);
     if(node == root)
       root = child;
     return child;
   }

// link() method is used by add(), remove() and rotate()
// handles null pointers and updates root pointer when needed
   public void link(BTNode parent, int side, BTNode child)
   {
     if(child != null)
       child.setParent(parent);
     if(parent != null)
       parent.setChild(side, child);
     else
       root = child;
   }

// Methods below for different tree types do not belong together
// and should be implemented in separate modules (classes)

// AVL methods

// insert data into an AVL tree
// returns new node or null if the data already exists
   public BTNode insertAVL(BTData data)
   {
     BTNode node;
     if(root == null)
       return add(null, 0, data);
     if(locate(data) != null)
       return null;
     node = add(lastnode, nextside, data);
     rebalanceAVL(lastnode, nextside, INSERT);
     return node;
   }

// delete data from an AVL tree
// returns the parent of the deleted node
// or null if the data has not been found
// uses helper method swap() if necessary
   public BTNode deleteAVL(BTData data, int minmax)
   {
     int side;
     BTNode node;

     node = locate(data);
     if(node == null)
       return null;

     if(node.hasTwoChildren())
       node = swap(node, minmax);

     side = node.getSide();
     node = remove(node);
     rebalanceAVL(node, side, DELETE);
     return node;
   }

// rebalanceAVL() performs AVL rebalancing of the tree
// after INSERT or DELETE using rotations when necessary
// argument node is the parent of inserted or deleted node
// argument side is the grown or shrunken side
// argument in is -1 for DELETE and +1 for INSERT
   public void rebalanceAVL(BTNode node, int side, int in)
   {
     for(; node != null; node = node.getParent())
     {
       if(node.getBalance() != in*side)
         node.setBalance(node.getBalance() + in*side);
       else
         node = rotateAVL(node);

       if(in == INSERT && node.getBalance() == 0 || in == DELETE && node.getBalance() != 0)
         break;
       side = node.getSide();
     }
   }

// rotateAVL() is called by rebalanceAVL() after INSERT or DELETE
// It performs rotation(s) and updates balance factors.
// Returns the top node of the rotated subtree.
// If the balance factor of this node is 0:
// for INSERT: the subtree has not grown,
// for DELETE: the subtree has shrunken.
// If the balance factor of this node is not 0 (left-high or right-high):
// for INSERT: the subtree has grown,
// for DELETE: the subtree has not shrunken.
   public BTNode rotateAVL(BTNode node)
   {
     int side = node.getBalance();
     BTNode child = node.getChild(side);

     if(child.getBalance() == -side)
     {
       BTNode grand = child.getChild(-side);
       if(grand.getBalance() == -side)
       {
         grand.setBalance(0);
         child.setBalance(side);
         node.setBalance(0);
       } else
       if(grand.getBalance() == side)
       {
         grand.setBalance(0);
         child.setBalance(0);
         node.setBalance(-side);
       } else
       {
         node.setBalance(0);
         child.setBalance(0);
       }
       rotate(child, side);
     } 
     else 
     if(child.getBalance() == side)
     {
       node.setBalance(0);
       child.setBalance(0);
     } 
     else 
     if(child.getBalance() == 0)      // only after DELETE, never after INSERT
     {
       node.setBalance(side);
       child.setBalance(-side);
     }
     node = rotate(node, -side);
     return node;
   }

// Splay methods

// attempt to find data
// if data has been found, splay the lastnode (lastnode = node) to the root
// if data has not been found, splay the lastnode (node = null)
// and return FAILURE
   public BTNode find(BTData data)
   {
     BTNode node;
     node = locate(data);
     splay(lastnode);
     return node;
   }

// locate place to insert
// splay the lastnode to the root regardless of the search results
// if data already exists, do not insert, return FAILURE
// otherwise, split the tree and insert a new root
   public BTNode insertSPL(BTData data)
   {
     BTNode node;
     if(root == null)
       return add(null, 0, data);
     node = locate(data);
     splay(lastnode);
     return (node != null) ? null : addSPL(data);
   }

// create new root, split the tree, 
// making sure that L < X < R
// and attach the subtrees to the new root
   public BTNode addSPL(BTData data)
   {
     BTNode newroot, subroot;
     int side;

     newroot = new BTNode(data);
     side = -root.compareTo(data);
     subroot = root.getChild(-side);

     root.setChild(-side, null);
     link(newroot,  side, root);
     link(newroot, -side, subroot);
     root = newroot;
     return root;
   }

// locate data and splay the lastnode to the root 
// regardless of the search results
// if data has not been found, do not delete, return FAILURE
// if data has been found, the node is now root, 
// proceed with finding min or max
// if min/max does not exist, remove current root, 
// whose single child becomes the new root.
// otherwise, splay min/max to root, remove current root 
// and attach its child to the new root
   public BTNode deleteSPL(BTData data, int minmax)
   {
     BTNode node;
     BTNode temproot;

     node = locate(data);
     splay(lastnode);

     if(node == null)
       return null;

     temproot = node;
     node = (minmax == FINDMAX) ? temproot.prevInO() : temproot.nextInO();
     if(node != null)
       splay(node);
     return remove(temproot);
   }

// bottom-up splaying
// each rotation of ancestors brings the node up,
// maintaining pointers is not necessary
   public void splay(BTNode node)
   {
     BTNode parent;
     int side;
     while((parent = node.getParent()) != null)
     {
       side = node.getSide();
       BTNode grandparent = parent.getParent();

       if(grandparent == null)       // ZIG or ZAG
         rotate(parent, -side);
       else
       if(parent.getSide() == side)  // ZIGZIG or ZAGZAG
       {
         rotate(grandparent, -side);
         rotate(parent, -side);
       }
       else
       if(parent.getSide() != side)  // ZIGZAG or ZAGZIG
       {
         rotate(parent, -side);
         rotate(grandparent, side);
       }
     }
   }
}

 

// class BTNode defines the tree node data structure
// and provides a collection of get/set methods protecting private data
// all balancing methods are defined in BSTree class
class BTNode
{
   public static final int LEFT  =  -1;
   public static final int RIGHT =  +1;
   public static final int NONE  =   0;
   public static final int EQUAL =   0;
   public static final int ERROR = 666;

   BTNode parent;
   BTNode lchild;
   BTNode rchild;
   int    balance;
   int    color;
   BTData data;

   public BTNode()
   {
     parent  = null;
     lchild  = null;
     rchild  = null;
     balance = 0;
     color   = 0;
     data    = null;
   }
   public int getBalance()
   {
     return balance;
   }
   public void setBalance(int balance)
   {
     this.balance = balance;
   }
   public int getColor()
   {
     return color;
   }
   public void setColor(int color)
   {
     this.color = color;
   }
   public BTData getData()
   {
     return data;
   }
   public void setData(BTData data)
   {
     this.data = data;
   }
   public BTNode getParent()
   {
     return parent;
   }
   public void setParent(BTNode parent)
   {
     this.parent = parent;
   }
   public BTNode getChild(int side)
   {
     if(side == LEFT)
       return lchild;
     else
     if(side == RIGHT)
       return rchild;
     else
     if(side == EQUAL)
       return this;       // match
   }
   public BTNode getChild()
   {
     return (lchild != null) ? lchild : rchild;
   }
   public void setChild(int side, BTNode node)
   {
     if(side == LEFT)
       lchild = node;
     else
     if(side == RIGHT)
       rchild = node;
   }
   public int getSide() // on which side of the parent am I?
   {
     if(parent == null)
       return NONE;       // this is root
     else
     if(this == parent.lchild)
       return LEFT;
     else 
     if(this == parent.rchild)
      return RIGHT;
     return ERROR;
   }

   public BTNode prevInO()
   {
     BTNode temp = this;
     BTNode node = null;
     if(temp.lchild != null)
       node = temp.lchild.lastInO();
     else
       for(; (node = temp.parent) != null && temp == node.lchild; temp = node)
         ;
     return node;
   }

   public BTNode nextInO()
   {
     BTNode temp = this;
     BTNode node = null;
     if(temp.rchild != null)
       node = temp.rchild.firstInO();
     else
       for(; (node = temp.parent) != null && temp == node.rchild; temp = node)
         ;
     return node;
   }

   public BTNode firstInO()
   {
     BTNode node;
     for(node = this; node.lchild != null; node = node.lchild)
       ;
     return node;
   }

   public BTNode lastInO()
   {
     BTNode node;
     for(node = this; node.rchild != null; node = node.rchild);
     return node;
   }
 

// wrappers

   public int compareTo(BTData data)
   {
     int side = data.compareTo(this.data);
     return side;
   }

   public boolean hasTwoChildren()
   {
     return (lchild != null && rchild != null);
   }
   public boolean isLeaf()
   {
     return (lchild == null && rchild == null);
   }
   

}

// BTData class is a wrapper for the actual data
// It must provide a method for comparing values
// Ideally, it should implement Comparable interface

class BTData
{
   public static final int LT = BTNode.LEFT;
   public static final int EQ = BTNode.EQUAL;
   public static final int GT = BTNode.RIGHT;

   String key;

// null pointers eliminated for convenience
   public BTData()
   {
     key = "";
   }
   public BTData(String key)
   {
     this.key = key;
   }
   public String getKey()
   {
     return (key != null) ? key : "";
   }
   public void setKey(String key)
   {
     this.key = key;
   }
   public int compareTo(BTData data)
   {
     int cmp = key.compareTo(data.key);
     if(cmp < 0)
       cmp = LT;
     else if(cmp > 0)
       cmp = GT;
     else if(cmp == 0)
       cmp = EQ;
     return cmp;
   }
}
 
 