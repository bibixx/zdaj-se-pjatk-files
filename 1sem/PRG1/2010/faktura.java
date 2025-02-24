
public class faktura  // opisujemy z czego ma się składać faktura
{
	private int numerFaktury; // faktura ma miec numer
	private int kwotaFakturyNetto=0;
	private boolean pusty_portfel=false;
	private static int portfel=1200;
	public static final int MIN_NUMERFAKTURY = 1; // min. numer faktury to 1
	public static final int MAX_NUMERFAKTURY = 99; // a max. numer faktury to 99
	
	
	

	public faktura(int a, int c) // tworzac fakture, trzeba bedzie podac jej numer i kwote
	{
		
		setnumerFaktury(a); // ustawia numer faktury
		
	}

	public void setnumerFaktury(int b)  // sprawdzamy numer faktury
	{
		if (b<MIN_NUMERFAKTURY) // jezeli jest mniejszy od min
		{
			this.numerFaktury = MIN_NUMERFAKTURY; // to ustaw min
		}
		else if (b>MAX_NUMERFAKTURY) // jezeli jest wiekszy niz max
		{
			this.numerFaktury = MAX_NUMERFAKTURY; // to ustaw max
		}
		else
		{
			this.numerFaktury = b;
		}
	}
	public int getnumerFaktury() // zwracamy numer fv
	{
		return this.numerFaktury;
	}
	
	public void setkwotaFakturyNetto(int d) // sprawdzamy kwote netto
	{
		this.kwotaFakturyNetto = d;
	}
	public int getKFN() // zwracamy kwote netto
	{
		return kwotaFakturyNetto;
	}
	
	
	// moduł, który daje to że jak wpiszemy jakąś cene to zwiekszy sie o tyle kwota netto fv
	
	public void cena ( int kwotaFakturyNetto)
	{
		this.kwotaFakturyNetto = kwotaFakturyNetto;
	}
	// sprawdzamy ile zostało w portfelu
	
	public int getW_Portfelu()
	{
		return this.portfel = (portfel - kwotaFakturyNetto);
	}
	
	// modul zwiekszania kasy w portfelu
	public static int getP_P()
	{
		return portfel;
	}
	
	
	public boolean isP_P() 
	{
		return pusty_portfel;
	}
	public void zapytanie(boolean pusty_portfel)
	{
		if(isP_P() && !pusty_portfel)
		{
			this.pusty_portfel = pusty_portfel;
			portfel--;
		}
		else
		{
			if (!isP_P() && pusty_portfel)
			{
				this.pusty_portfel = pusty_portfel;
				portfel++;
			}
		}
	}
	
	
	
}







public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		faktura f1 = new faktura(6, 600); // 6 to numer naszej faktury. kiedy damy liczbe mniejsza niz 1 zwroci 1, kiedy >99 zwórci 99
		System.out.println("Przyszedł klient do sklepu i ma w portfelu 1200zł. Na wejściu dostał zerową fakture nr 6");
		System.out.println("FV nr: " + f1.getnumerFaktury() + "    kwota netto: " + f1.getKFN()); // kwote powinien zwrocić 0
		System.out.println();
		
		//zwiekszamy kwote faktury poprzez dodanie nowej ceny
		f1.cena(800);
		System.out.println("Klient zakupił na FV towar za 800zł, więc teraz odpowiednio faktura wygląda tak:");
		System.out.println("FV nr: " + f1.getnumerFaktury() + "    kwota netto: " + f1.getKFN()); // kwote powinien zwrocić 800
		System.out.println();
		System.out.println("W porftelu zostało:" + f1.getW_Portfelu()); //ile zostało w portfelu
		f1.zapytanie(false); // tutaj zwiekszamy wartość portfela. false jezeli nie, true jezeli tak
		System.out.println();
		System.out.println("Nasz klieknt zauważył coś na ziemi..to chyba moneta.. ile ma teraz hajsu ?");
		System.out.println();
		System.out.println("W porftelu zostało:" + f1.getP_P());
	}

}
