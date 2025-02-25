import java.util.ArrayList;

public class Box<Dowolna> {
	
	ArrayList<Dowolna> elementy = new ArrayList<Dowolna>();
	
	public Box<Dowolna> add(Dowolna element){
		if (elementy.contains(element)==false) 
		{
			elementy.add(element); 
		}
		return this;
	}

	/*dodaje elementy podanej tablicy element�w do zestawu, 
	 * o ile nie by�o ich w zestawie przed operacj�,
	 */
	
	public Box<Dowolna> addAll(Dowolna tablica[]){
		{
			for (Dowolna key : tablica) 
			{
				add(key);
			}
		}
		return this;
	}

	public Box<Dowolna> delete(Dowolna element){ // usuwa element z zestawu, o ile wystapi� on w zestawie przed operacj�,
		if (elementy.contains(element)==true) 
		{
			elementy.remove((Dowolna) element);
		}
		return this;
	}
	
	public Box<Dowolna> swap(int i, int j){ // zamiana element�w w zestawie na dw�ch podanych pozycjach
		
		Dowolna temp1=this.elementy.get(i);  // E1
		Dowolna temp2=this.elementy.get(j); // E2
		
		this.elementy.set(i, temp2);
		this.elementy.set(j, temp1);
		
		return this;
	}
	
	public Dowolna min(){
		Dowolna najmniejszy = elementy.get(0);
		
		for (Dowolna objekt : elementy) 
			{
				if (((Comparable) najmniejszy).compareTo(objekt) > 0) 
				{
					najmniejszy = objekt;
				}
			}
		return najmniejszy;
	}
	
	public Dowolna max(){
		Dowolna najwiekszy = elementy.get(0);
			
			for (Dowolna objekt : elementy) 
			{
				if (((Comparable) najwiekszy).compareTo(objekt) < 0) 
				{
					najwiekszy = objekt;
				}
			}
		return najwiekszy;
	}
	
	public boolean search(Dowolna objekt){
		return (elementy.contains(objekt));
	}
	
	public void print(){
		for (int i=0; i<elementy.size(); i++) {
			System.out.println(elementy.get(i));
		}
	}

}

/*
. add - dodaje element do zestawu, o ile nie by�o go w zestawie przed operacj�,
b. addAll - dodaje elementy podanej tablicy element�w do zestawu, o ile nie by�o ich w zestawie przed operacj�,
c. delete - usuwa element z zestawu, o ile wystapi� on w zestawie przed operacj�,
d. swap - zamiana element�w w zestawie na dw�ch podanych pozycjach,
e. min (max) - znajdowanie (bez sortowania) minimum (maksimum) zestawu zgodnie ze zdefiniowanym porz�dkiem,
f. search - sprawdzanie, czy podany element znajduje si� w zestawie,
g. print - wy�wietla wszystkie elementy  zestawu.
*/