package Spotkanie03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Zadanie1 {

	public static void main(String[] args) {
		try {
			String nazwaKlasy = args[0];
			Class klasa = Class.forName(nazwaKlasy);
			
			System.out.println("\n------Konstruktory:------ ");
			for(Constructor c: klasa.getDeclaredConstructors()) {
				nazwaKlasy =  c.getName();
				System.out.print(Modifier.toString(c.getModifiers()) + " " + nazwaKlasy + " " );
				for ( Class c1 : c.getParameterTypes() ) {System.out.print(c1.getSimpleName());}
				}
				
				System.out.println("\n----Metody:------ ");
				for(Method c: klasa.getDeclaredMethods()) {
					System.out.print(Modifier.toString(c.getModifiers()) + " " + c.getName() + " ");
					for ( Class c1 : c.getParameterTypes() ) {System.out.print(c1.getSimpleName());}
				}
				
				
				System.out.println("\n------Pola:------ ");
				for(Field c: klasa.getDeclaredFields()) {
					System.out.print(Modifier.toString(c.getModifiers()) + c.getName() + " ");
				}		
			
		} catch (Exception e) {	System.out.println("Brak argumentow!");
		}
	}
}

/*Zadanie 1 (java.lang.reflect)
Przebadaj klasê przekazan¹ jako pierwszy argument programu wypisuj¹c jej wszystkie :
– konstruktory
– pola
– metody
Przy kazdej z powyzszych danych wypisz jakiego jest typu : public, protected czy private. Dla
metod wypisz jaki typ danych zwracaja, dla metod i konstruktorow ile i jakiego typu argumenty
pobieraja.*/