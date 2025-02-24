import java.beans.*;
import java.awt.*;
import javax.swing.*;

public class DynamicExec {

  public static void main(String[] args) throws Exception {

    Statement stmt;
    Expression expr;

    JButton b = new JButton();

    // Na rzecz przycisku wo³amy dynamicznie metodê setText
    // z argumentem "Przycisk"
    stmt = new Statement(b, "setText", new Object[] { "Przycisk" });
    stmt.execute();

    // Jaki wynik? Najpierw statyczne odwo³anie
    System.out.println("Tekst na przycisku 1: " + b.getText());

    // Teraz dynamicznie: stwórzmy wyra¿enie, którego wynikiem
    // jest wynik podanej metody z podanymi argumentami wywolanej
    // na rzecz b
    // Uwaga: brak argumentów - czyli tablica Object o rozmiarze 0
    expr = new Expression(b, "getText", new Object[0]);

    // Je¿eli wyra¿enie expr nie ma jeszcze wyniku
    // metoda getValue() wywo³uje podan¹ w wyra¿eniu metodê
    // i zwraca jej wynik; w przeciwnym razie zwraca
    // ustalony wczesniej wynik

    String txt = (String) expr.getValue();
    System.out.println("Tekst na przycisku 2: " + txt);

    // Mo¿emy te¿ stosowaæ klasy Statement i Expression
    // wobec naszych w³asnych klas JavaBeans

    TestBean tbean = new TestBean();

    // Uwaga: przy przekazywaniu argumentów i zwrocie wynikow
    // nastêpuj¹ automatyczne przeksztalcenia pomiedzy
    // typami prostymi i odpowiadaj¹cymi im klasami opakowuj¹cymi
    // np. int - Integer  - setCount wymaga argumentu int,
    // my podajemy Integer

    stmt = new Statement(tbean, "setCount",
                               new Object[] { new Integer(22) });
    stmt.execute();

    // Jaka jest teraz wartoœæ w³aœciwoœci count
    // I znowu: getCount() zwraca int, my odbieramy Integer

    expr = new Expression( tbean, "getCount", new Object[0] );
    Integer val = (Integer) expr.getValue();

    System.out.println("Wartoœæ count: " + val);

    // Czy mo¿emy dzia³aæ na tabliach? Ale¿ tak!

    stmt = new Statement(tbean, "setHeaders",
                         new Object[] { new String[] { "a", "b" } }
                        );
    stmt.execute();

    expr = new Expression(tbean, "getHeaders", new Object[0]);
    String[] hdr = (String[]) expr.getValue();

    System.out.println("Ustalone nag³ówki");
    for (int i=0; i<hdr.length; i++)
       System.out.println(hdr[i]);

    // Mo¿emy nawet stworzyæ nowy obiekt
    // u¿ywaj¹c specjalnej nazwy metody - new  (oczywiœcie)

    expr = new Expression(TestBean.class, "new",
                          new Object[] { new Integer(111) }
                         );
    TestBean tb2 = (TestBean) expr.getValue();

    expr = new Expression (tb2, "getCount", new Object[0]);
    val = (Integer) expr.getValue();

    System.out.println("W nowym obiecie count = " + val);

  }
}
