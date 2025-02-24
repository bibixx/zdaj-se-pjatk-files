package imprezogenerator;

public class Slub 
{
	 private int ID_SLUB,ID_KAPELA;
	 private String NAZWA_KOSCIOLA,NAZWA_SAMOCHODU;

//============set get ID_SLUB==========//	
		
		public void setID_SLUB(int x)
		{
			ID_SLUB = x;
		}
		
		public int getID_SLUB()
		{
			return ID_SLUB;
		}
		
//============set get ID_KAPELA==========//	
		
		public void setID_KAPELA(int x)
		{
			ID_KAPELA = x;
		}
		
		public int getID_KAPELA()
		{
			return ID_KAPELA;
		}
	 
//============set get NAZWA_KOSCIOLA==========//	
		
		public void setNAZWA_KOSCIOLA(String x)
		{
			NAZWA_KOSCIOLA = x;
		}
		
		public String getNAZWA_KOSCIOLA()
		{
			return NAZWA_KOSCIOLA;
		}
		
//============set get NAZWA_SAMOCHODU==========//	
		
		public void setNAZWA_SAMOCHODU(String x)
		{
			NAZWA_SAMOCHODU = x;
		}
		
		public String getNAZWA_SAMOCHODU()
		{
			return NAZWA_SAMOCHODU;
		}
}
