package imprezogenerator;

public class Admin 
{
	private int ID_ADMIN,ID_OSOBA;
	private String LOGIN,PASSWORD;
	
//set get ID_KLIENT==========//	
	
	public void setID_ADMIN(int x)
	{
		ID_ADMIN = x;
	}
	
	public int getID_ADMIN()
	{
		return ID_ADMIN;
	}
	
//set get ID_OSOBA==========//	
	
	public void setID_OSOBA(int x)
	{
		ID_OSOBA = x;
	}
	
	public int getID_OSOBA()
	{
		return ID_OSOBA;
	}

//set get LOGIN==========//	
	
	public void setLOGIN(String x)
	{
		LOGIN = x;
	}
	
	public String getLOGIN()
	{
		return LOGIN;
	}

//set get PASSWORD==========//	
	
	public void setPASSWORD(String x)
	{
		PASSWORD = x;
	}
	
	public String getPASSWORD()
	{
		return PASSWORD;
	}
}
