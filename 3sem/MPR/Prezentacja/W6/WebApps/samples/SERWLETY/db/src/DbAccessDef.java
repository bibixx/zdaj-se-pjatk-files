import java.util.*;

public class DbAccessDef extends ListResourceBundle {
     public Object[][] getContents() {
         return contents;
     }

    static final Object[][] contents = {
       { "charset", "ISO-8859-2" },
       { "header", new String[] { "Baza danych ksi¹¿ek" } },
       { "param_command", "Polecenie (select lub insert):" },
       { "submit", "Wykonaj" },
       { "footer", new String[] { } },
       { "resCode", new String[]
                    { "Wynik:", "Brak bazy", "B³¹d SQL",
                      "Wadliwe polecenie; musi zaczynaæ siê od select lub insert" }
                    },
       { "resDescr",
            new String[] { "" } },
    };
}