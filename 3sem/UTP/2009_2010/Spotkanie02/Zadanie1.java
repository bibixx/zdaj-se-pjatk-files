package Spotkanie02;
import java.util.*;
 
public class Zadanie1 {
        public static void main(String[] args)
        {
                sayHello();
                Locale.setDefault(new Locale("en"));
                sayHello();
        }
 
        static void sayHello() {
                Locale defLoc = Locale.getDefault();
                ResourceBundle msgs = ResourceBundle.getBundle("HelloMessages", defLoc);
                String powitaj = msgs.getString("hello");
                String pozegnaj = msgs.getString("bye");
                System.out.println(powitaj);
                System.out.println(pozegnaj);
        }
}