import java.util.*;
import javax.swing.*;

public class Account {

  private double balance;          // stan konta
  private double monthIncome;      // sta�e miesi�czne wp�ywy (doch�d) 
  private double monthExpend;      // sta�e miesi�czne wydatki
  private double interest;         // stopa oprocentowania (roczna)

  // Konstruktor

  public Account(double s, double wpl, double wypl, double p) {
    balance = s;
    monthIncome = wpl;
    monthExpend = wypl;
    interest = p;
  }

  // Metoda - zwraca aktualny stan konta

  public double getBalance() {
    return balance;
  }

  // Metoda - zwraca liczb� miesi�cy potrzebnych 
  // by stan konta osi�gn�� warto�� targetBalance

  public int getMonthsToBalance(double targetBalance) {
   
    double wspOds = (interest/100)/12;   
    double diff = targetBalance - balance;
    int n = 0;
    while (diff > 0) {         
      n++;
      balance += wspOds*balance +  monthIncome - monthExpend;  
      double prevDiff = diff;
      diff = targetBalance - balance;
      if (diff >= prevDiff) return -1;
    }                                        
    return n;                                
  }

  public double getBalanceAfter(int n) {
     double wspOds = (interest/100)/12;   
     for (int i = 1; i <= n; i++)
       balance += wspOds*balance +  monthIncome - monthExpend; 
     return balance;
  }  
 
 
}


// Klasa testuj�ca konto

class TestKonta {

  public static void main(String[] args) {
    Account ac = new Account(2000, 2400, 1800, 10); 
    double cel = 10000; 
    int m =  ac.getMonthsToBalance(cel);
    System.out.println("Miesiace do osiagniecia co najmniej " + cel + ":");
    System.out.println(m + " --- stan konta " + ac.getBalance());
    ac = new Account(2000, 2400, 1800, 10); 
    System.out.println("Stan konta po " + m + " miesi�cach " + ac.getBalanceAfter(m));
   }

}