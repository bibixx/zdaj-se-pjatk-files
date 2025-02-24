package kik.plansza;

public class TesterPlanszy
{

	public static void main(String[] args)
	{
	
		Plansza p = new Plansza();

		System.out.println( "Czy plansza jest zapeliona? " + p.isPlanszaFull() );
		
		p.draw();

		System.out.println( "Czy postawilem X na pozycji 0,0? " + p.putX( 0,0 ) );
		
		p.draw();
		
		System.out.println( "Czy postawilem X na pozycji 0,0? " + p.putX( 0,0 ) );
		
		p.draw();
		
		System.out.println( "Czy postawilem O na pozycji 0,0? " + p.putO( 0,0 ) );
		
		p.draw();
		
		System.out.println();
		
		System.out.println( "Czy teraz kolej X? " + p.czyKolejX() );
		System.out.println( "Czy teraz kolej O? " + p.czyKolejO() );
		
		System.out.println();
		System.out.println( "=============================" );
		System.out.println( "Zapelniam plansze..." );
		
		p = new Plansza();
		p.putX( 0 , 0 );
		p.putO( 0 , 1 );
		p.putX( 0 , 2 );
		p.putO( 1 , 0 );
		p.putX( 1 , 1 );
		p.putO( 1 , 2 );
		p.putX( 2 , 0 );
		p.putO( 2 , 1 );
		p.putX( 2 , 2 );
		
		p.draw();
		
		System.out.println( "Czy plansza jest zapeliona? " + p.isPlanszaFull() );
		
		System.out.println( "Czy wygral X? " + p.czyWygralX() );
		System.out.println( "Czy wygral O? " + p.czyWygralO() );
		
		System.out.println();
		System.out.println( "=============================" );
		
		p = new Plansza();
		
		p.putX( 0 , 0 );
			p.putO( 1 , 0 );
		p.putX( 0 , 1 );
			p.putO( 1 , 1 );
		p.putX( 0 , 2 );
				
		p.draw();
		
		System.out.println( "Czy wygral X? " + p.czyWygralX() );
		System.out.println( "Czy wygral O? " + p.czyWygralO() );
		
		System.out.println();
		System.out.println( "=============================" );
		
		p = new Plansza();
		
		p.putX( 0 , 0 );
			p.putO( 1 , 0 );
		p.putX( 1 , 1 );
			p.putO( 2 , 1 );
		p.putX( 2 , 2 );
				
		p.draw();
		
		System.out.println( "Czy wygral X? " + p.czyWygralX() );
		System.out.println( "Czy wygral O? " + p.czyWygralO() );		
		
		System.out.println();
		System.out.println( "=============================" );
		
		p = new Plansza();
		
		p.putX( 2 , 0 );
			p.putO( 1 , 0 );
		p.putX( 1 , 1 );
			p.putO( 2 , 1 );
		p.putX( 0 , 2 );
				
		p.draw();
		
		System.out.println( "Czy wygral X? " + p.czyWygralX() );
		System.out.println( "Czy wygral O? " + p.czyWygralO() );				
				
		
	}
	


}
