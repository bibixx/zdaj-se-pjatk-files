
public class Zadanie10 {


	public static void main(String[] args) {
		Buffer b = new Buffer() ;
		Producer p = new Producer(b);
		Consumer c = new Consumer(b);

		p.start();
		c.start();
		
		try 
		{
			Thread.sleep(30000);
			c.interrupt(); p.interrupt();
		} 
		catch (InterruptedException e)	{}
		
		System.out.println("Koniec!");
	}

}