package imprezogenerator;

import java.io.File;

import com.jenkov.db.MappingTool;

public class Main 
{

	public static void main(String[] args) 
	{
		Baza.test();
		//====== wyswietlanie rekordów tabel bazy danych=======//
		
		MappingTool Osoba = new MappingTool(new File("database.properties"),Osoba.class, "OSOBA");
		Osoba.run();
		
		MappingTool Kapela = new MappingTool(new File("database.properties"),Kapela.class, "KAPELA");
		Kapela.run();
		
		MappingTool Slub = new MappingTool(new File("database.properties"),Slub.class, "SLUB");
		Slub.run();
		
		MappingTool Sylwester = new MappingTool(new File("database.properties"),Sylwester.class, "SYLWESTER");
		Sylwester.run();
		
		MappingTool Chrzciny = new MappingTool(new File("database.properties"),Chrzciny.class, "CHRZCINY");
		Chrzciny.run();
		
		MappingTool Impreza = new MappingTool(new File("database.properties"),Impreza.class, "IMPREZA");
		Impreza.run();
		
		MappingTool Potrawa = new MappingTool(new File("database.properties"),Potrawa.class, "POTRAWA");
		Potrawa.run();
		
		MappingTool Potrawaimprezy = new MappingTool(new File("database.properties"),Potrawaimprezy.class, "POTRAWAIMPREZY");
		Potrawaimprezy.run();
		
		MappingTool Miejsce = new MappingTool(new File("database.properties"),Miejsce.class, "MIEJSCE");
		Miejsce.run();
		
		MappingTool Miejsceimprezy = new MappingTool(new File("database.properties"),Miejsceimprezy.class, "MIEJSCEIMPREZY");
		Miejsceimprezy.run();
		
		MappingTool Kelner = new MappingTool(new File("database.properties"),Kelner.class, "KELNER");
		Kelner.run();
		
		MappingTool Organizator = new MappingTool(new File("database.properties"),Organizator.class, "ORGANIZATOR");
		Organizator.run();
		
		MappingTool Klient = new MappingTool(new File("database.properties"),Klient.class, "KLIENT");
		Klient.run();
		
		MappingTool Pracownik = new MappingTool(new File("database.properties"),Pracownik.class, "PRACOWNIK");
		Pracownik.run();
		
		MappingTool Godzinypracy = new MappingTool(new File("database.properties"),Godzinypracy.class, "GODZINYPRACY");
		Godzinypracy.run();
		
		MappingTool Slubochrzciny = new MappingTool(new File("database.properties"),Slubochrzciny.class, "SLUBOCHRZCINY");
		Slubochrzciny.run();
		
		MappingTool Admin = new MappingTool(new File("database.properties"),Admin.class, "ADMIN");
		Admin.run();
		
		MappingTool Uzytkownik = new MappingTool(new File("database.properties"),Uzytkownik.class, "UZYTKOWNIK");
		Uzytkownik.run();
	}

}
