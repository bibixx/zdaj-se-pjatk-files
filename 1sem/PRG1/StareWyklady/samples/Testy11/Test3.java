 
public class Test3 {
 
  public static void main(String[] args) {
    new Test3();
  } 

  Test3() {
    int[] tab = { 2, 5, 7 };
    chgTab1(tab);
    showTab("Po wywo³aniu metody chgTab1 tablica oznaczana przez tab", tab);
    chgTab2(tab);
    showTab("Po wywo³aniu metody chgTab2 tablica oznaczana przez tab", tab);
  }

  void chgTab1(int[] tab) {
    int[] nowa = { 3, 6, 8 };
    tab = nowa;
    showTab("W metodzie chgTab tablica oznaczana przez tab", tab);
  }

  void chgTab2(int[] tab) {
    for (int i=0; i < tab.length; i++) tab[i]++;
  }

  void showTab(String s, int[] tab) {
    System.out.println(s);
    for (int i=0; i < tab.length; i++) System.out.print(" " + tab[i]);
    System.out.print('\n');
  }
 
}
