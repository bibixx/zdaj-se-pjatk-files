package imprezogenerator;

public class Organizator 
{
	private Integer ID_ORGANIZATOR;
	private String SPECJALIZACJA;
	
//============set get ID_ORGANIZATOR==========//	
	
	public void setID_ORGANIZATOR(Integer x)
	{
		ID_ORGANIZATOR = x;
	}
	
	public Integer getID_ORGANIZATOR()
	{
		return ID_ORGANIZATOR;
	}
	
	
//============set get SPECJALIZACJA==========//	
	
	public void setSPECJALIZACJA(String x)
	{
		SPECJALIZACJA = x;
	}
	
	public String getSPECJALIZACJA()
	{
		return SPECJALIZACJA;
	}
}
