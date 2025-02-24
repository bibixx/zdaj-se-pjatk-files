import javax.swing.*;
 
public class Bankomat {

  private static String CANCEL_MSG = "Zrezygnowano z transakcji. Do widzenia";
  private String currentPin;
  private int  currentLimit;

  public Bankomat() {
    CardIdent.init();
  }

  public boolean askPin() {
    String pin = ask("Wprowadz numer PIN:");
    boolean pinOk = false;
    int limit = 0;
    if (pin == null)  say(CANCEL_MSG);
    else {
      limit =  CardIdent.getLimit(pin);
      if (limit == -1) say("Wadliwy PIN");
      else  { 
        pinOk = true;
        currentPin = pin;
        currentLimit = limit;
      }
    }
    return pinOk;
  }
    
   
  public boolean askAmmountAndWithdraw() {
    if (currentPin == null) return false;
    boolean withdrawAccepted = false;
    String request = ask("Podaj kwote do wyplaty:");
    if (request == null) say(CANCEL_MSG);
    else {
      int ammount = Integer.parseInt(request);
      if (ammount > currentLimit) say("Limit przekroczony.");
      else {
        CardIdent.changeLimit(currentPin, ammount);
        say("Wyplacam kwote : " + ammount + " zl");
        withdrawAccepted = true;
        } 
    }
    currentPin = null;
    return withdrawAccepted;
  } 
 


  private String ask(String txt) {
    return JOptionPane.showInputDialog(txt);
  }

  private void say(String txt) {
    JOptionPane.showMessageDialog(null, txt);
  }

 
}

class BankomatTest {
  public static void main(String[] args) {

    Bankomat b = new Bankomat();
    
    // Pierwszy klient
    b.askPin();
    b.askAmmountAndWithdraw();
    
    // Drugi klient
    b.askPin();
    b.askAmmountAndWithdraw();

    System.exit(0);

  } 
}