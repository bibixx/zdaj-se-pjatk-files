package CW_07_11_08;


public class Zadanie29
{
	public static void main(String[] args){
		Counter licznik=new Counter();
		licznik.inc();
		licznik.inc();
		licznik.inc();
		licznik.reset();
		licznik.dec();
		licznik.dec();
		licznik.dec();
	}
}
	
	class Counter 
	{
		int state;
		public Counter() {state = 0;}
		
		public Counter inc(){state++; System.out.println("Licznik: " + state); return this;}
		public Counter dec(){state--;System.out.println("Licznik: " + state); return this;}
		public void reset() {state = 0;System.out.println("Licznik: " + state);}
		
	}
	
