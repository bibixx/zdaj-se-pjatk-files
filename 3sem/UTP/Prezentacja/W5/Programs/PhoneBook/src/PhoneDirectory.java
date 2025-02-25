import java.util.*;
import java.io.*;

public class PhoneDirectory {

  private Map pbMap = new HashMap();

  public PhoneDirectory(String fileName) {
    // Inicjalna zawarto�� ksi��ki telefonicznej
    // jest wczytywana z pliku o formacie
    //  imi�  numer_telefonu
    try {
      BufferedReader br = new BufferedReader(
                             new FileReader(fileName));
      String line;
      while ((line = br.readLine()) != null) {
        String[] info = line.split(" +", 2);
        pbMap.put(info[0], info[1]);
      }
    } catch (Exception exc) {
        exc.printStackTrace();
        System.exit(1);
    }
  }

  // Zwraca numer telefonu dla podanej osoby
  public String getPhoneNumber(String name) {
    return (String) pbMap.get(name);
  }

  // Dodaje now� osob� do ksi��ki
  // Wynik:
  // - true - dodana
  // - false - nie (przy pr�bie dodania osoby zapisanej ju� w ksi��ce)
  public boolean addPhoneNumber(String name, String num) {
    if (pbMap.containsKey(name)) return false;
    pbMap.put(name, num);
    return true;
  }

  // Zast�puje numer podanej osoby nowym
  // Wynik:
  // - true (numer zast�piony)
  // - false (nie - pr�ba podania nowegu numeru nieistniej�cej osoby)
  public boolean replacePhoneNumber(String name, String num) {
    if (!pbMap.containsKey(name)) return false;
    pbMap.put(name, num);
    return true;
  }

}