import java.util.*;

public class RegexParamsDef extends ListResourceBundle {
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
       { "resCode_0", "Dopasowano" },
       { "resCode_1", "Brak danych" },
       { "resCode_2", "Wadliwy wzorzec" },
       { "resCode_3", "Nie znaleziono dopasowania" },
       { "resDescr",
            new String[] { "pod³añcuch", "od poz.", "do poz.", "" } },
    };
}