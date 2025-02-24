class Referee extends Thread{
	int czas;
	public Referee(int i) {	czas = i;}

	public void run() 
	{
	
		try 
		{
			Thread.sleep(czas);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
    }
}