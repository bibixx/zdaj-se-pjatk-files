
public class Zadanie31 {
	public static void main(String[] args) {
		
		Complex liczba1 = new Complex(1);
		Complex liczba2 = new Complex(1,2);
		System.out.println(liczba1.getRe());
	    System.out.println(liczba1.getIm());
	    System.out.println(liczba1);
	    System.out.println(liczba1.toString());
	    System.out.println(liczba1.multiply(liczba2));
	    System.out.println(liczba1.divide(2));
		}	
	}
	
	class Complex 
	{
		private double im,re;
	
		//Konstruktory.
		public Complex() {re = 0;im = 0;}
		public Complex(double re, double im){this.re=re; this.im=im;}
		public Complex(double re) {this.re=re; this.im=0;}
		public Complex(Complex c) {re=c.re; im=c.im;}
		
		//Metody.
		public double getRe() {return re;}
		public double getIm() {return im;}
    //    public String toString() {return ("Re: " +re+ " Im: " + im);}
        public Complex multiply(double a) {re=re*a; im=im*a; return this;}
        public Complex multiply(Complex c) {
        	double a=re,b=im;
        	re=(a*c.re)-(b*c.im);
        	im=(b*c.im)+(a*c.im);
        	return this;}
        public Complex divide(double a){
        re = re/a;
        im = im/a;
        return this;}
	}
	
/*
Zadanie 31 (5p) 

Zaprojektowaæ i testowaæ klasê Complex reprezentuj¹c¹ liczby zespolone: 

    Pola:                    private double  im, re - czêœæ rzeczywista i zesplona 
    Konstruktory:     public Complex() - konstruuje liczbê zespolon¹: 0 + i*0 
                                public  Complex(double re, double im) - konstruuje liczbê zespolon¹: re + i*im 
                                public  Complex(double re) - konstruuje liczbê zespolon¹: re + i*0 
                                public Complex(Complex c) - konstruktor "kopiuj¹cy", tworzy identyczn¹ kopiê c 
    Metody:          public double getRe() - zwraca re 
                            public double getIm() - zwraca im 
                            public String toString() - wypisuje komunikat o liczbie zespolonej 
                            public Complex multiply1(double a) - mno¿y przez liczbê rzeczywist¹ a 
                            public Complex multiply2(Complex c) - mno¿y przez liczbê zespolon¹ c 
                            public Complex divide(double a) - dzieli przez liczbê rzeczywist¹ a

*/
