public class Zadanie42 {

	public static void main(String[] args) {
		String konstylacja=args[0];
		for (int i=1; i<=10; i++) konstylacja = konstylacja + args[i];
		System.out.println(konstylacja);
		System.out.println("Element najmniejszy: " + min(args));
		System.out.println("Element najwiekszy: " + max(args));
		
	}
		
	    public static String min(String[] tab) {
	        int dl = tab.length;
	        String min = tab[0];
	        for (int i=1; i<dl; i++) 
	        {
	            if (tab[i].compareTo(min) < 0)
	            {
	            	min = tab[i];
	            }
	        }
	        return min;
	    }
	    
	    public static String max(String[] tab) {
	        int dl = tab.length;
	        String max = tab[0];
	        for (int i=1; i<dl; i++) 
	        {
	            if (tab[i].compareTo(max) > 0)
	            {
	            	max = tab[i];
	            }
	        }
	        return max;
	    }
	}


/*Zadanie 42 (2p) 
Napisa� program, kt�ry pobiera z linii wywo�ania 10 argument�w, umieszcza je
 we w�asnej tablicy �a�cuch�w tekstowych i wypisuje na konsol� ich konkatenacj�
  oraz  najwi�kszy i najmniejszy element tablicy (stosujemy porz�dek leksykograficzny). 


Pomoc: Do por�wnania dw�ch �a�cuch�w tekstowych mo�na skorzysta� z  metody compareTo() 
z klasy String (zob. dokumentacj� Java): 
s1.compareTo(s2) zwraca:
    *     liczb� ujemn�, je�eli �a�cuch tekstowy s1 znajduje, wed�ug porz�dku 
    *     leksykograficznego, przed �a�cuchem tekstowym s2; 
 
    *     liczb� dodatni�, je�eli s1 znajduje, wed�ug porz�dku leksykograficznym, po s2; 
    *     0, je�li s1 i s2  s� takie same. 
Np. "ala".compareTo("ale") < 0, "2".compareTo("11") > 0, "java".compareTo("ja"+"va") = 0.*/