public class Roznica {

  public static void main(String[] args) {
    
    // Operacje na zmiennych typ¢w pierwotnych
    int x, y, z;
    x = 3;
    y = 4;
    x = y;
    y = 5;
    z = 5;
    System.out.println("x = " + x);
    System.out.println("y = " + y);
    System.out.println("z = " + z);
    if (x == y) System.out.println ("x i y r¢wne."); 
    else  System.out.println ("x i y nier¢wne.");
    if (y == z) System.out.println ("y i z r¢wne."); 
    else  System.out.println ("y i z nier¢wne.");


    // Podobne operacje na zmiennych typu referencyjnego
    Para px = new Para(), py = new Para(), pz = new Para();
    px.set( 3, 3 );
    py.set( 4, 4 );
    pz.set( 5, 5 );
    px = py;
    py.set( 5, 5 );
    System.out.print("Para px: "); px.show();
    System.out.print("Para py: "); py.show();
    System.out.print("Para pz: "); pz.show();
    if (px == py) System.out.println ("px i py r¢wne."); 
    else  System.out.println ("px i py nier¢wne.");
    if (py == pz) System.out.println ("py i pz r¢wne."); 
    else  System.out.println ("py i pz nier¢wne.");

  }
} 
      
     
     

