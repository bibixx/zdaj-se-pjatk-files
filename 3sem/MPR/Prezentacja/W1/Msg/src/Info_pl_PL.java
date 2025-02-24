import java.util.*;
public class Info_pl_PL extends ListResourceBundle {

   public Object[][] getContents() {
     return contents;
   }

   private Object[][] contents = {
     {  "english", "Angielski" },
     {  "polish", "Polski" },
     {  "enterData", "Wyp³ata" },
     {  "report", "Dnia {0,date} o godzinie {0,time}" +
                  " wyp³acono {1,number,currency}" },
     {  "parseError", "Nieprawid³owy format wartoœci do wyp³aty {0}" },
     {  "exceedError", "Mo¿liwa suma wyp³at {0,number,currency}" +
                     " zosta³a przekroczona" },
   };

}

