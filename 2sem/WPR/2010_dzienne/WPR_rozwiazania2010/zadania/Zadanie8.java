
public class Zadanie8 {


	public static void main(String[] args) {
		
        Box<Osoba>  bo = new Box<Osoba>();
        bo.add(new Osoba("Kowalski", 19));               // nazwisko, wiek
        bo.add(new Student("Kowalska", 18, 100));        // nazwisko, wiek, grupa
        bo.add(new Student("Kowalska", 20, 200));
        Osoba[] to = new Osoba[] {new Osoba("Nowak", 21), new Student("Nowak", 22, 200)};
        bo.addAll(to);
        bo.print();
        System.out.println(bo.min());   // Student 1: Kowalska, 18, 100
        
        Box<Figura> bf = new Box<Figura>();
        bf.add(new Kolo(10,10,40));
        bf.add(new Prostokat(10,10,40,40));
        Figura[] tf = new Figura[]{new Prostokat(0,0,50,50), new Kolo(0,0,50)};
        bf.addAll(tf);
        bf.print();
        System.out.println(bf.max());    // Kolo - srodek (0,0), promien 50
       
        Box<Integer> bi = new Box<Integer>();
        Integer[] ti = new Integer[] {new Integer(1), 2, 3};
        bi.addAll(ti);
        bi.print();    // 1, 2, 3
        
        Box<String> bs = new Box<String>(); 
        String[] ts = new String[] {"cpp", new String("java")};
        bs.addAll(ts);
        bs.swap(0,1);
        bs.print();    // java, cpp
		
	}

}


