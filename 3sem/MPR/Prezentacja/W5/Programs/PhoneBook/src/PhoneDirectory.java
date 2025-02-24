import java.util.*;
import java.io.*;

public class PhoneDirectory {

  private Map pbMap = new HashMap();

  public PhoneDirectory(String fileName) {
    // Inicjalna zawartoœæ ksi¹¿ki telefonicznej
    // jest wczytywana z pliku o formacie
    //  imiê  numer_telefonu
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

  // Dodaje now¹ osobê do ksi¹¿ki
  // Wynik:
  // - true - dodana
  // - false - nie (przy próbie dodania osoby zapisanej ju¿ w ksi¹¿ce)
  public boolean addPhoneNumber(String name, String num) {
    if (pbMap.containsKey(name)) return false;
    pbMap.put(name, num);
    return true;
  }

  // Zastêpuje numer podanej osoby nowym
  // Wynik:
  // - true (numer zast¹piony)
  // - false (nie - próba podania nowegu numeru nieistniej¹cej osoby)
  public boolean replacePhoneNumber(String name, String num) {
    if (!pbMap.containsKey(name)) return false;
    pbMap.put(name, num);
    return true;
  }

}