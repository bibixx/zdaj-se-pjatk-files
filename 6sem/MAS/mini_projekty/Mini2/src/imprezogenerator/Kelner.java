package imprezogenerator;

public class Kelner 
{
	private int ID_KELNER;
	private double STAWKA;
	private boolean CZY_CERTYFIKOWANY;
	
	
//============set get ID_KELNER==========//	
	public void setID_KELNER(int x)
	{
		ID_KELNER = x;
	}
	
	public int getID_KELNER()
	{
		return ID_KELNER;
	}
	
//============set get CZY_CERTYFIKOWANY==========//	
	public void setCZY_CERTYFIKOWANY(boolean x)
	{
		CZY_CERTYFIKOWANY = x;
	}
	
	public boolean getCZY_CERTYFIKOWANY()
	{
		return CZY_CERTYFIKOWANY;
	}
	
//============set get STAWKA==========//	
	public void setSTAWKA(double x)
	{
		STAWKA = x;
	}
	
	public double getSTAWKA()
	{
		return STAWKA;
	}
	
	
}
