abstract class Figura implements WymiaryFigur, Comparable<Figura> {
	protected int x, y;                                 	// okreœlaj¹ po³o¿enie figury
    protected String fig = "Jeszcze nie wiadomo";       	// okreœla nazwê figury z wartoœci¹ pocz¹tkow¹ 
    public Figura(int x, int y) {this.x = x; this.y = y;}   // konstruuje figurê na podstawie podanego po³o¿enia 
    public void pokaz() {System.out.println(this.fig);}     // wypisuje nazwê figury i po³o¿enie 
    public abstract void pozycja(int x, int y); 			// wypisuje komunikat, czy punkt (x, y) le¿y wewn¹trz figury
    
    public int compareTo(Figura fig){
    	int i = 0;
    	double pole1 = pole();
    	double pole2 = fig.pole();
    	if (pole1 > pole2) {i = 1;}
    	else{
    		if (pole1 == pole2) {
    			double ob1 = obwod();
    			double ob2 = fig.obwod();
    			if (ob1 > ob2) {i = 1;}
    			else {i=-1;}
    		}
    		else {i=-1;}
    	}
		return i;
    	
 
    }
}