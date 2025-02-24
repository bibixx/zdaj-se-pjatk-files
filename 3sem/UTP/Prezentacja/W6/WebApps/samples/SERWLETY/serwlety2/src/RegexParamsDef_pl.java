import java.util.*;

public class RegexParamsDef_pl extends ListResourceBundle {
     public Object[][] getContents() {
         return contents;
     }

    static final Object[][] contents = {
       { "charset", "windows-1250" },
       { "header", new String[] { "Testowanie wyra¿eñ regularnych" } },
       { "param_regex", "Wzorzec:" },
       { "param_input", "Tekst:" },
       { "submit", "Poka¿ wyniki wyszukiwania" },
       { "footer", new String[] { } },
       { "resCode", new String[]
                    { "Dopasowano", "Brak danych",
                      "Wadliwy wzorzec", "Nie znaleziono dopasowania" }
                    },
       { "resDescr",
            new String[] { "pod³añcuch", "od poz.", "do poz.", "" } },
    };
}