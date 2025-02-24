import java.beans.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class SerialBean {

  String fname = "test.xml";

  public SerialBean() {
    JButton b = new JButton("K¹œliwie wróbel æwierka³");
    b.setBackground(Color.red);
    b.setForeground(Color.yellow);

    try {
      XMLEncoder enc = new XMLEncoder(
                         new BufferedOutputStream(
                            new FileOutputStream(fname)
                            )
                      );
      enc.writeObject(b);
      enc.close();
    } catch (FileNotFoundException exc) {
        exc.printStackTrace();
        System.exit(1);
    }
    nowReadAndReport();
  }

  private void nowReadAndReport() {
    try {
      XMLDecoder dec = new XMLDecoder(
                          new BufferedInputStream(
                              new FileInputStream(fname)));
      Object obj = dec.readObject();
      JButton b = (JButton) obj;
      dec.close();
      System.out.println("Napis na przycisku: " + b.getText());
      System.out.println("Kolor t³a: " + b.getBackground());
      System.out.println("Kolor tekstu : " + b.getForeground());

    } catch (FileNotFoundException exc) {
        exc.printStackTrace();
        System.exit(1);
    }
  }



  public static void main(String[] args) {
    SerialBean serialbean = new SerialBean();
  }


}