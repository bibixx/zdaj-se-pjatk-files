package kik.plansza;

public class Plansza
{
	public static final char ZNAK_PUSTY = '-';
	public static final char ZNAK_X = 'X';
	public static final char ZNAK_O = 'O';
	
	private String wiersz1 = "" + ZNAK_PUSTY+ZNAK_PUSTY+ZNAK_PUSTY;
	private String wiersz2 = "" + ZNAK_PUSTY+ZNAK_PUSTY+ZNAK_PUSTY;
	private String wiersz3 = "" + ZNAK_PUSTY+ZNAK_PUSTY+ZNAK_PUSTY;
	
	private char ostatnioPostawionyZnak = ZNAK_PUSTY;
	
	public String toString()
	{
		return wiersz1 + "\n" + wiersz2 + "\n" + wiersz3;
	}
	
	public void draw()
	{
		System.out.println( "----------- Plansza: -----------" );
		System.out.println( this );
	}
	
	private boolean isWierszFull( String wiersz )
	{
		/*int iloscPustychZnakow = 0;
		
		for( int i = 0 ; i < wiersz.length() ; i++ )
		{
			if( !(wiersz.charAt( i ) == ZNAK_PUSTY) ) iloscPustychZnakow++;
		}
		
		return iloscPustychZnakow == 3;
		*/
		
		//int iloscPustychZnakow = 0;
		
		for( int i = 0 ; i < wiersz.length() ; i++ )
		{
			if( wiersz.charAt( i ) == ZNAK_PUSTY ) return false;
		}
		
		return true;
		//return iloscPustychZnakow == 3;		
		
	}
	
	public boolean isPlanszaFull()
	{
		return isWierszFull( wiersz1 ) && isWierszFull( wiersz2 ) && isWierszFull( wiersz3 );
	}
	
	private String getWiersz( int y )
	{
		if( y == 0 )
			return wiersz1;
		else
		if( y == 1 )
			return wiersz2;
		else
			return wiersz3;
	}
	
	private String getKolumna( int x )
	{
		char znak1 = getWiersz( 0 ).charAt( x );
		char znak2 = getWiersz( 1 ).charAt( x );
		char znak3 = getWiersz( 2 ).charAt( x );
		
		return "" + znak1 + znak2 + znak3;
	}
	
	private String getUkosZGoryNaDol()
	{
		char znak1 = getWiersz( 0 ).charAt( 0 );
		char znak2 = getWiersz( 1 ).charAt( 1 );
		char znak3 = getWiersz( 2 ).charAt( 2 );
		
		return "" + znak1 + znak2 + znak3;
	}
	
	private String getUkosZDoluNaGore()
	{
		char znak1 = getWiersz( 0 ).charAt( 2 );
		char znak2 = getWiersz( 1 ).charAt( 1 );
		char znak3 = getWiersz( 2 ).charAt( 0 );
		
		return "" + znak1 + znak2 + znak3;
	}	
	
	private void setWiersz( int y , String wiersz )
	{
		if( y == 0 )
			wiersz1 = wiersz;
		else
		if( y == 1 )
			wiersz2 = wiersz;
		else
			wiersz3 = wiersz;
	}
	
	private boolean czyPustePole( int x , int y )
	{
		String wiersz = getWiersz( y );
		return wiersz.charAt( x ) == ZNAK_PUSTY;
	}
	
	private boolean put( int x, int y, char znak )
	{
		if( znak != ostatnioPostawionyZnak )
		{
			if( x >=0 && x <= 2 && y >=0 && y <= 2 )
			{
				if( czyPustePole( x , y ) )
				{
					String wiersz = getWiersz( y );
					
					if( x == 0 )
					{
						wiersz = "" + znak + wiersz.charAt( 1 ) + wiersz.charAt( 2 );
					}
					else
					if( x == 1 )
					{
						wiersz = "" + wiersz.charAt( 0 ) + znak + wiersz.charAt( 2 );
					}
					else
					{
						wiersz = "" + wiersz.charAt( 0 ) + wiersz.charAt( 1 ) + znak;
					}
					
					setWiersz( y , wiersz );
					
					ostatnioPostawionyZnak = znak;
					
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
		{
			return false;
		}
	}
	
	public boolean putX( int x, int y )
	{
		return put( x , y , ZNAK_X );
	}
	
	public boolean putO( int x, int y )
	{
		return put( x , y , ZNAK_O );
	}	
	
	public boolean czyKolejX()
	{
		return ostatnioPostawionyZnak == ZNAK_O;
	}
	
	public boolean czyKolejO()
	{
		return ostatnioPostawionyZnak == ZNAK_X;
	}	
	
	private boolean czyTenStringJestWypelnionyTakimiSamymiZnakami( String s , char znak )
	{
		for( int i = 0 ; i < s.length() ; i++ )
		{
			if( s.charAt( i ) != znak ) return false;
		}
		
		return true;
	}
	
	private boolean czyWygral( char znak )
	{
		// sprawdzanie trzech wierszy
		
		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getWiersz( 0 ) , znak ) ) return true;
		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getWiersz( 1 ) , znak ) ) return true;
		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getWiersz( 2 ) , znak ) ) return true;
		
		// sprawdzaie trzech kolumn

		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getKolumna( 0 ) , znak ) ) return true;
		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getKolumna( 1 ) , znak ) ) return true;
		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getKolumna( 2 ) , znak ) ) return true;
		
		// sprawdzanie dwoch ukosow

		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getUkosZGoryNaDol() , znak ) ) return true;
		if( czyTenStringJestWypelnionyTakimiSamymiZnakami( getUkosZDoluNaGore() , znak ) ) return true;		
		
		return false;
	}
	
	public boolean czyWygralX()
	{
		return czyWygral( ZNAK_X );
	}
	
	public boolean czyWygralO()
	{
		return czyWygral( ZNAK_O );	
	}
	
	// metoda, ktora by znacznie pomogla to dla stringa setCharAt( char, int)
	// dzieki niej wiele tutaj by sie uproscilo
	
	// zly miks polskich i angielskich nazw
}
