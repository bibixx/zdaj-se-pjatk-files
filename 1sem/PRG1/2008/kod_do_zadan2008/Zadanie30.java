package CW_07_11_08;


public class Zadanie30
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
		Counter2.op();
		
	}
}
	
	class Counter2 
	{
		int state;
		private static int op;
		public Counter2() {state = 0;}
		
		public Counter2 inc(){state++; System.out.println("Licznik: " + state); op++; return this;}
		public Counter2 dec(){state--;System.out.println("Licznik: " + state); op++; return this;}
		public void reset() {state = 0;System.out.println("Licznik: " + state); op++;}
		public static void op(){System.out.println("Wykonane operacje: " + op);}
	}
	

	/*Rozbudowaæ klasê Counter z zadania 29 o dodatkowe pole: 
	 * private static int op - rejestruje iloœæ operacji wyk. 
	 * na obiektach klasy Counter do chwili obecnej. */
