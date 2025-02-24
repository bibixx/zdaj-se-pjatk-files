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
Napisaæ program, który pobiera z linii wywo³ania 10 argumentów, umieszcza je
 we w³asnej tablicy ³añcuchów tekstowych i wypisuje na konsolê ich konkatenacjê
  oraz  najwiêkszy i najmniejszy element tablicy (stosujemy porz¹dek leksykograficzny). 


Pomoc: Do porównania dwóch ³añcuchów tekstowych mo¿na skorzystaæ z  metody compareTo() 
z klasy String (zob. dokumentacjê Java): 
s1.compareTo(s2) zwraca:
    *     liczbê ujemn¹, je¿eli ³añcuch tekstowy s1 znajduje, wed³ug porz¹dku 
    *     leksykograficznego, przed ³añcuchem tekstowym s2; 
 
    *     liczbê dodatni¹, je¿eli s1 znajduje, wed³ug porz¹dku leksykograficznym, po s2; 
    *     0, jeœli s1 i s2  s¹ takie same. 
Np. "ala".compareTo("ale") < 0, "2".compareTo("11") > 0, "java".compareTo("ja"+"va") = 0.*/