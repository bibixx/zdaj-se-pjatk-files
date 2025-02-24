package international;
import java.util.*;

public class Messages_pl extends ListResourceBundle {
     public Object[][] getContents() {
         return contents;
     }

    static final Object[][] contents = {
       { "hello", "Dzieñ dobry bardzo!" },
       { "now", "Teraz bêdzie, a w³aœciwie ju¿ jest" },
       { "charset", "ISO-8859-2" }
    };
}