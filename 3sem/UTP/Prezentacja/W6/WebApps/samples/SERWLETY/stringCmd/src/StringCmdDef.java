import java.util.*;

public class StringCmdDef extends ListResourceBundle {
     public Object[][] getContents() {
         return contents;
     }

    static final Object[][] contents = {
       { "charset", "ISO-8859-2" },
       { "header", new String[] { "Dzia�ania na Stringach" } },
       { "param_input1", "Tekst 1:" },
       { "param_input2", "Tekst 2:" },
       { "param_cmd", "Polecenie:" },
       { "submit", "Wykonaj" },
       { "footer", new String[] { } },
       { "resCode", new String[]
                    { "Wynik:", "Brak danych",
                      "Wadliwe polecenie, dost�pne: upper, lower, words" }
                    },
       { "resDescr",  new String[] { "" } }, 
    };
}