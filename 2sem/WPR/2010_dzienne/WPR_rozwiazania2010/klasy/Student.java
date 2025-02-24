class Student extends Osoba{

		static int ilosc;
		int id;
		int grupa;
		public Student(String nazwisko, int wiek, int grupa) {
			super(nazwisko, wiek);
			this.grupa = grupa; 
			this.ilosc++;
			id = ilosc;
			
		
		}
	
		public void setGr(int grupa) {grupa = this.grupa;}
		public String getImie() {return super.nazwisko;}
		public int getWiek() {return super.wiek;}
		public void setNazwisko(String nazwisko) {this.nazwisko = super.nazwisko;}
		public void setWiek(int wiek) {this.wiek = super.wiek;}
		
		public String toString() {
			return (super.toString() + " gr:" + this.grupa);
		}
		
	}
