public class TabPar {
 
  public static void main(String[] args) {
    Para[] tabPar = new Para[10];
    for (int i=0; i < tabPar.length; i++) tabPar[i] = new Para(i+1, i+2);
    for (int i=0; i < tabPar.length; i++) tabPar[i].show("Para " + (i+1));
   } 
 
}
