import com.ibm.icu.util.*;
import com.ibm.icu.text.*;
import java.io.*;
import java.util.*;

public class Detektor{


  public static void main(String[] args){
    try {
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(args[0]));
      CharsetDetector cd = new CharsetDetector();
      cd.setText(in);
      CharsetMatch[] csets = cd.detectAll();
      for (int i=0; i< csets.length; i++) System.out.println(csets[i].getName());
      in.close();
    } catch (Exception exc) {
        exc.printStackTrace();
    }
  }


}