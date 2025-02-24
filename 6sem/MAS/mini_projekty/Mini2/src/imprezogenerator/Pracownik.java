package imprezogenerator;

public class Pracownik {
	
	private int ID_PRACOWNIK,ID_ORGANIZATOR,ID_KELNER;
	private double PENSJA;

//============set get ID_PRACOWNIK==========//	
	
	public void setID_PRACOWNIK(int x)
	{
		ID_PRACOWNIK = x;
	}
	
	public int getID_PRACOWNIK()
	{
		return ID_PRACOWNIK;
	}
	
//============set get ID_ORGANIZATOR==========//	
	
	public void setID_ORGANIZATOR(int x)
	{
		ID_ORGANIZATOR = x;
	}
	
	public int getID_ORGANIZATOR()
	{
		return ID_ORGANIZATOR;
	}
	
//============set get ID_OSOBA==========//	
	
	public void setID_KELNER(int x)
	{
		ID_KELNER = x;
	}
	
	public int getID_KELNER()
	{
		return ID_KELNER;
	}
	
//============set get PENSJA==========//	
	
	public void setPENSJA(double x)
	{
		PENSJA = x;
	}
	
	public double getPENSJA()
	{
		return PENSJA;
	}
}
