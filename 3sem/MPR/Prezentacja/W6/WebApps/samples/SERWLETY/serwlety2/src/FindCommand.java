import java.util.*;
import java.io.*;
import java.util.regex.*;

public class FindCommand extends CommandImpl implements Serializable {

  public FindCommand() {}

  public void execute() {
    clearResult();
    String regex = (String) getParameter("regex");
    String input = (String) getParameter("input");
    if (regex == null || input == null) {
      setStatusCode(1);
      return;
    }
    Pattern pattern;
    try {
      pattern = Pattern.compile(regex);
    } catch (PatternSyntaxException exc) {
       setStatusCode(2);
       return;
    }
    Matcher matcher = pattern.matcher(input);
    boolean found = matcher.find();
    if (!found) setStatusCode(3);
    else {
      setStatusCode(0);
      do {
        addResult( new Object[] { "\"" + matcher.group() + "\"",
                                  new Integer(matcher.start()),
                                  new Integer(matcher.end()-1)
                                });

      } while(matcher.find());
    }
  }

}