package Spotkanie03;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Zadanie2 {

	public static void main(String[] args) {
		try {
			String nazwaKlasy = args[0];
			String parametr = args[1];
			Class klasa = Class.forName(nazwaKlasy);
			Constructor konstruktor = klasa.getConstructor(Class.forName("java.lang.String"));
			Object obiekt = konstruktor.newInstance(parametr);
			System.out.println(obiekt.toString());
		} 
		catch (Exception e) {System.out.println("Brak argumentow");
		}
	}
}

/*
Stworz instajce klasy, ktorej nazwa jest przekazana jako pierwszy argument programu. 
Do stworzenia instacji uzyj konstruktora z jednym argumentem (typu String) ktorego 
wartosc bedzie przekazana jako drugi parametr programu. Stworzona instancje 'wypisz' 
na konsole ( .toString() ).
*/