class Player extends Thread{
	int suma;
	String nazwa_gracza;

	public Player(String nazwa)	{	nazwa_gracza = nazwa;	}
	
	public void run()
	{
		while (true)
		{
			try {Thread.sleep((long)(Math.random()*100));} 
			catch (InterruptedException e) {return;}
			suma+=(int)(Math.random()*100);
		}
	}
}