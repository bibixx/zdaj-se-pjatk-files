package imprezogenerator;

import java.sql.Date;

public class Impreza {

	private Integer ID_IMPREZA,ID_SLUB,ID_CHRZCINY,ID_SYLWESTER,ID_ORGANIZATOR;
	private Date DATA_ROZP,DATA_ZAK;  
	private String NUMER_IMPREZY;
	private double CENA;
	
//============set get ID_IMPREZA==========//	
	
	public void setID_IMPREZA(Integer x)
	{
		ID_IMPREZA = x;
	}
	
	public Integer getID_IMPREZA()
	{
		return ID_IMPREZA;
	}
	
//============set get ID_SLUB==========//	
	
	public void setID_SLUB(Integer x)
	{
		ID_SLUB= x;
	}
	
	public Integer getID_SLUB()
	{
		return ID_SLUB;
	}
	
//============set get ID_CHRZCINY==========//	
	
	public void setID_CHRZCINY(Integer x)
	{
		ID_CHRZCINY= x;
	}
	
	public Integer getID_CHRZCINY()
	{
		return ID_CHRZCINY;
	}
	
//============set get ID_SYLWESTER==========//	
	
	public void setID_SYLWESTER(Integer x)
	{
		ID_SYLWESTER= x;
	}
	
	public Integer getID_SYLWESTER()
	{
		return ID_SYLWESTER;
	}
	
//============set get ID_ORGANIZATOR==========//	
	
	public void setID_ORGANIZATOR(Integer x)
	{
		ID_ORGANIZATOR = x;
	}
	
	public Integer getID_ORGANIZATOR()
	{
		return ID_ORGANIZATOR;
	}	
//============set get DATA_ROZP==========//	
	
	public void setDATA_ROZP(Date x)
	{
		DATA_ROZP = x;
	}
	
	public Date getDATA_ROZP()
	{
		return DATA_ROZP;
	}
	
//============set get DATA_ZAK==========//	
	
	public void setDATA_ZAK(Date x)
	{
		DATA_ZAK = x;
	}
	
	public Date getDATA_ZAK()
	{
		return DATA_ZAK;
	}
	
//============set get NUMER_IMPREZY==========//	
	
	public void setNUMER_IMPREZY(String x)
	{
		NUMER_IMPREZY = x;
	}
	
	public String getNUMER_IMPREZY()
	{
		return NUMER_IMPREZY;
	}
	
//============set get CENA==========//	
	
	public void setCENA(double x)
	{
		CENA = x;
	}
	
	public double getCENA()
	{
		return CENA;
	}
}
