package imprezogenerator;

public class Uzytkownik 
{
	private int ID_UZYTKOWNIK,ID_OSOBA;
	private String LOGIN,PASSWORD;
	
//============set get ID_UZYTKOWNIK==========//	
	
	public void setID_UZYTKOWNIK(int x)
	{
		ID_UZYTKOWNIK = x;
	}
	
	public int getID_UZYTKOWNIK()
	{
		return ID_UZYTKOWNIK;
	}
	
//============set get ID_OSOBA==========//	
	
	public void setID_OSOBA(int x)
	{
		ID_OSOBA = x;
	}
	
	public int getID_OSOBA()
	{
		return ID_OSOBA;
	}

//============set get LOGIN==========//	
	
	public void setLOGIN(String x)
	{
		LOGIN = x;
	}
	
	public String getLOGIN()
	{
		return LOGIN;
	}

//============set get PASSWORD==========//	
	
	public void setPASSWORD(String x)
	{
		PASSWORD = x;
	}
	
	public String getPASSWORD()
	{
		return PASSWORD;
	}
}
