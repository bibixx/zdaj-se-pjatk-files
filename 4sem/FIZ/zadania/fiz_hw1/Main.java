/*
 * Main.java
 *
 * Created on 5 paüdziernik 2007, 16:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fiz_hw1;

/**
 *
 * @author znak
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main(double number, int k) {
        double calculated=Math.pow(Math.E,number);
        System.out.println("E to the power of "+number+": "+calculated);
        System.out.println("");
        double result=0;
        for(int x=0;x<k;x++){
            result+=(Math.pow(Math.E,0)*Math.pow(number,x))/Main.fact(x);
        }
        System.out.println("E calculated: "+result);
        System.out.println("---------------------");
        System.out.println("Difference: "+Math.abs(calculated-result));
    }
    
    public static double fact(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fact(n-1);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length!=2)
            new Main(8,20);
        else{
            try {
                new Main(Double.valueOf(args[0]),Integer.valueOf(args[1]));
            } catch (NumberFormatException ex) {
                System.out.println("Niepoprawne argumenty.");
                System.exit(1);
            }
        }
    }
    
}
