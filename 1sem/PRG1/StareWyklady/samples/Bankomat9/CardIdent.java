import java.util.*;

public class CardIdent {

    private static Hashtable ht = new Hashtable();

    public static void init() {

      String[] pin = { "1234", "2345", "3456", "4567", "5678", "6789" };
      int[]  limit = { 200 , 300 , 400 , 500, 500, 500 };
      for (int i = 0; i < pin.length; i++) 
        ht.put(pin[i], new Integer(limit[i]));
    }
    
   public static int getLimit(String pin) {
     Integer limit = (Integer) ht.get(pin);
     if (limit == null) return -1;
     else return limit.intValue();
   }

   public static void changeLimit(String pin, int withdraw) {
     Integer limit = (Integer) ht.get(pin);
     if (limit != null) {
        int newLimit = limit.intValue() - withdraw;
        if (newLimit < 0) newLimit = 0;
        ht.put(pin, new Integer(newLimit));
     }
   }
}
     
     