import java.util.*;
public class Info_en_GB extends ListResourceBundle {

   public Object[][] getContents() {
     return contents;
   }

   private Object[][] contents = {
      { "english", "English" },
      { "polish", "Polish" },
      {  "enterData", "Withdrawal" },
      { "report",
         "At {0,time} on {0,date} value of {1,number,currency} withdrawed" },
      { "parseError", "Invalid value format: {0}" },
      { "exceedError", "Total ammount {0,number,currency} exceeded" },
   };

}

