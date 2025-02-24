//import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import java.util.Scanner;

public class KeyboardKolkoKrzyzykPlayer extends KolkoKrzyzykPlayer {
    public KeyboardKolkoKrzyzykPlayer(String name) {
        super(name);
    }

    public java.awt.Point getPoint() {
        String ruch=null;
        while(true) {
            ruch = showInputDialog(getName() + " teraz twoja kolej!");
            if(ruch != null && !ruch.isEmpty()){
                break;
            }else if(ruch ==null){
                throw new pressCancelException();
            }
        }
        Scanner sc = new Scanner(ruch);
        int x=sc.nextInt();
        int y=sc.nextInt();
        return new java.awt.Point(x,y);
       
    }
} 