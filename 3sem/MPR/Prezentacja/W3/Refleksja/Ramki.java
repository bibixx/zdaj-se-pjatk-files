import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.border.*; 
import java.lang.reflect.*; 

public class Ramki extends JFrame { 

   // Pola klasy okreœlaj¹ ró¿ne rodzaje ramek
   private Border 
     // empty = BorderFactory.createEmptyBorder(), 
     blackLine = BorderFactory.createLineBorder(Color.black), 
     blueLine3 = BorderFactory.createLineBorder(Color.blue, 3), 
     //titled1 = BorderFactory.createTitledBorder("Tytu³"), 
     titled2 = new TitledBorder(blueLine3,"Tytu³",
                   TitledBorder.CENTER, TitledBorder.CENTER,
                   new Font("Dialog", Font.BOLD | Font.ITALIC, 16), 
                   Color.blue),    
     etched = BorderFactory.createEtchedBorder(), 
     etchedC = BorderFactory.createEtchedBorder(Color.red, Color.yellow), 
     //raisedBevel = BorderFactory.createRaisedBevelBorder(), 
     //loweredBevel = BorderFactory.createLoweredBevelBorder(), 
     matteColor = BorderFactory.createMatteBorder(5, 10, 5, 15, Color.red), 
     matteIcon = new MatteBorder(new ImageIcon("FastForward24.gif")), 
     softBevR = new SoftBevelBorder(SoftBevelBorder.RAISED), 
     softBevL = new SoftBevelBorder(SoftBevelBorder.LOWERED), 
     compound1 = BorderFactory.createCompoundBorder(softBevR, softBevL), 
     compound2 = BorderFactory.createCompoundBorder(blueLine3, compound1), 
     compound3 = BorderFactory.createCompoundBorder(compound1, matteIcon); 

  
  Ramki() { 
    super("Prezentacja ramek");
    getContentPane().setLayout (new GridLayout(0,4,5,5)); 
    
    // Klasa tego obiektu
    Class c = getClass(); 
    
    // Uzyskanie tablicy wszystkich zadeklarowanych pól tej klasy
    Field[] field = c.getDeclaredFields(); 

    // Przebiegamy po polach-ramkach     
    for (int i=0; i< field.length; i++) { 

      // Nazwa pola (zmiennej) - opisuj¹cego kolejn¹ ramkê
      String fldName = field[i].getName();
      
      // Tê nazwê wypiszemy na etykiecie
      JLabel l = new JLabel(fldName, JLabel.CENTER); 
      
      // Uzyskanie referencji do obiektu, reprezentowanego przez 
      // pole field[i] tego (this) obiektu. Czyli - do kolejnej ramki
      
      Object ramka = null;
      try {
        ramka = field[i].get(this);
      } catch (IllegalAccessException exc) { // Ten wyjatek mo¿e wyst¹piæ
          exc.printStackTrace();             // gdy dostêp do pola jest zabroniony
      }                                      // np. z innej klasy do prywatnego pola 

      // Dostaliœmy oczywiœcie ramkê, ale jako Object 
      // - konieczna konwersja do Border
      
      l.setBorder((Border) ramka); 
      
      getContentPane().add(l); 
    } 
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    pack(); 
    show(); 
  } 
  
  public static void main(String[] args) {
    new Ramki(); 
  } 
} 
