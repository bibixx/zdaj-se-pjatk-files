import java.io.*;
 
public class CopyFile {
 
  public static void main(String[] args) {

    FileInputStream in;       // plik wej�ciowy
    FileOutputStream out;     // plik wyj�ciowy

    try {

      in  = new FileInputStream(args[0]);
      out = new FileOutputStream(args[1]);
      int c;
      while ((c = in.read()) != -1) out.write(c);  // kopiowanie
      in.close();
      out.close();

    } catch(ArrayIndexOutOfBoundsException exc) { // brak argumementu
        System.out.println("Syntax: CopyFile in out");
        System.exit(1);
    } catch(FileNotFoundException exc) {  // nieznany plik
        System.out.println("Plik " + args[0] + " nie istnieje.");
        System.exit(2);
    } catch(IOException exc) {   // inny b��d wej�cia- wyj�cia
        System.out.println(exc.toString());
        System.exit(3);
    }  
 
  } 
 
}
