package Spotkanie01;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Zadanie3 {

  public static void main(String[] args) {

    Calendar c = Calendar.getInstance();
    Date teraz = c.getTime();

    SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
    df.applyPattern("dd-MM-yyyy");
    System.out.println(df.format(teraz));
    }

  }

