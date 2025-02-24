import java.io.*;
import java.util.*;

public class zadanie3 {

  public static void main(String[] args) {
	  
      System.out.println(sumujListe(lista("tekst.txt")));

  }
  public static ArrayList lista(String nazwapliku)
  {
      ArrayList<String> al = new ArrayList<String>();
     
      Scanner scan = null;
       
      try {
          scan = new Scanner(new File(nazwapliku));
          
          
        } catch (FileNotFoundException exc) {
        	
           System.out.println("Nie ma takiego pliku jak" + nazwapliku);
        }
        while(scan.hasNext())
        {
            al.add( scan.next());
        }
    return al;
     
  }
  public static int sumujListe(ArrayList<String> ar)
  {
      int suma = 0;
      int parsowana = 0;
      for(int i = 0; i < ar.size(); i++)
      {
          try {
             parsowana = Integer.parseInt(ar.get(i));
             suma+= parsowana;
            } catch (Exception e) {
               System.out.println(i + " blad " + e.getMessage());
            }
      }
      return suma;
  }

}