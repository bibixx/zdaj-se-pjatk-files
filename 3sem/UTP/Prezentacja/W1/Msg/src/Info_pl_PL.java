import java.util.*;
public class Info_pl_PL extends ListResourceBundle {

   public Object[][] getContents() {
     return contents;
   }

   private Object[][] contents = {
     {  "english", "Angielski" },
     {  "polish", "Polski" },
     {  "enterData", "Wyp�ata" },
     {  "report", "Dnia {0,date} o godzinie {0,time}" +
                  " wyp�acono {1,number,currency}" },
     {  "parseError", "Nieprawid�owy format warto�ci do wyp�aty {0}" },
     {  "exceedError", "Mo�liwa suma wyp�at {0,number,currency}" +
                     " zosta�a przekroczona" },
   };

}

