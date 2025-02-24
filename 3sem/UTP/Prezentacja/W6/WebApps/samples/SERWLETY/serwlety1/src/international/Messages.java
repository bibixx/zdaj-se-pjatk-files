package international;
import java.util.*;

public class Messages extends ListResourceBundle {
     public Object[][] getContents() {
         return contents;
     }

    static final Object[][] contents = {
       { "hello", "Hello!" },
       { "now", "Now is: " },
       { "charset", "ISO-8859-1" }
    };
}