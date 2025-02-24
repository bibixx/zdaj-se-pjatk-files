import java.io.*;
import java.util.*;

public class StringCommand extends CommandImpl implements Serializable {

  public StringCommand() {}

  public void execute() {
    clearResult();
    String input1 = (String) getParameter("input1");
    String input2 = (String) getParameter("input2");
    String cmd =   (String) getParameter("cmd");
    if (input1 == null || input2 == null || cmd == null) {
      setStatusCode(1);
      return;
    }


   String input = input1 + " " + input2;
   
   
   setStatusCode(0);
   if (cmd.equals("upper")) addResult(input.toUpperCase());
   else if (cmd.equals("lower")) addResult(input.toLowerCase());
   else if (cmd.equals("words")) {
     StringTokenizer st = new StringTokenizer(input);
     while (st.hasMoreTokens())  addResult(st.nextToken()); 
   }
   else setStatusCode(2);
 }

}