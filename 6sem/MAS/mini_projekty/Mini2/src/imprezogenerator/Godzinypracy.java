package imprezogenerator;

public class Godzinypracy 
{
	private int ID_KELNER,ID_IMPREZA;
	private String GODZ_OD,GODZ_DO;
	
	
	
//============set get ID_IMPREZA==========//	
	
	public void setID_IMPREZA(int x)
	{
		ID_IMPREZA = x;
	}
	
	public int getID_IMPREZA()
	{
		return ID_IMPREZA;
	}
	
//============set get ID_KELNER==========//	
	
	public void setID_KELNER(int x)
	{
		ID_KELNER = x;
	}
	
	public int getID_KELNER()
	{
		return ID_KELNER;
	}
	
//============set get GODZ_OD==========//	
	
	public void setGODZ_OD(String x)
	{
		GODZ_OD = x;
	}
	
	public String getGODZ_OD()
	{
		return GODZ_OD;
	}	
	
//============set get GODZ_DO==========//	
	
	public void setGODZ_DO(String x)
	{
		GODZ_DO = x;
	}
	
	public String getGODZ_DO()
	{
		return GODZ_DO;
	}
}
