package Spotkanie09;

import java.sql.*;

public class Zadanie03 {

	public static void main(String[] args) {

		String driverName = "org.apache.derby.jdbc.ClientDriver";
		String url = "jdbc:derby://localhost:1527/baza;create=true;";
		Connection con = null;

		try {Class.forName(driverName);	con = DriverManager.getConnection(url);	con.setAutoCommit(false);} 
		catch (ClassNotFoundException exc) {System.out.println("Brak klasy sterownika" + exc);System.exit(1);} 
		catch (SQLException exc) {System.out.println("Nieudane po³¹czenie z " + url + exc);	System.exit(1);	}

		System.out.println("Po³¹czenie OK!");

		try {
			Statement stment = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			ResultSet rs = stment.executeQuery("SELECT * FROM Ksiazki");
			
//			Zapalenie 1 : Na poczatku wypisz na konsoli zawartosc tabelki. Wykonaj kilka SQLek
//			zmieniajacych/dodajacych jakies pola, a nastepnie cofnij transakcje.
//			while (rs.next()){System.out.println(rs.getInt("id")+ ". " + rs.getString("kto") + " " + rs.getString("nazwa"));}
//			rs.absolute(1);
			stment.execute("INSERT INTO Autorzy (id, name, age) VALUES (10, 'Jan', 30)");
			stment.execute("INSERT INTO Ksiazki (id, kto, nazwa) VALUES (10, 1, 'Ksiazka Jana')");
//			rs.updateString("nazwa", "Zapalenie 1");
//			rs.updateRow();
//			con.rollback();
			con.commit();
			
			
//			//Zapalenie 2 : Ponownie wypisz na konsoli zawartosc tabelki.
//			rs.absolute(0);
//			while (rs.next()){System.out.println(rs.getInt("id")+ ". " + rs.getString("kto") + " " + rs.getString("nazwa"));}
//			
////			//Zapalenie 3 : Nastepnie wykonaj to samo, ale potwierdzajac transakcje.
////			rs.absolute(1);
////			rs.updateString("nazwa", "Nowa ksiazka!");
////			rs.updateRow();
////			con.commit();
//
////			//Zapalenie 4 : Wypisz na konsoli zawartosc tabeli.
////			rs.absolute(0);
////			while (rs.next()){System.out.println(rs.getInt("id")+ ". " + rs.getString("kto") + " " + rs.getString("nazwa"));}
			
		} 
		catch (SQLException exc) {System.out.println(exc.getMessage());	}
	}

}