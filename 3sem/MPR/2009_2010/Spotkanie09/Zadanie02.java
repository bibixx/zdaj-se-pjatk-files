package Spotkanie09;

import java.sql.*;

public class Zadanie02 {

	public static void main(String[] args) {

		String driverName = "org.apache.derby.jdbc.ClientDriver";
		String url = "jdbc:derby://localhost:1527/baza;create=true;";
		Connection con = null;

		try {Class.forName(driverName);	con = DriverManager.getConnection(url);	} 
		catch (ClassNotFoundException exc) {System.out.println("Brak klasy sterownika" + exc);System.exit(1);} 
		catch (SQLException exc) {System.out.println("Nieudane po³¹czenie z " + url + exc);	System.exit(1);	}

		System.out.println("Po³¹czenie OK!");

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Autorzy");
			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount(); // liczba kolumn
			for (int i = 1; i <= cc; i++)  {             				 // i-ta kolumna:
				  System.out.print(rsmd.getColumnName(i));               // - nazwa
				  System.out.print(" " + rsmd.getColumnDisplaySize(i));  // - szerokoœæ
				  System.out.print(" " + rsmd.getColumnClassName(i));    // - klasa Javy
				  System.out.print(" " + rsmd.getColumnType(i));         // - typ SQL
				  System.out.println(" " + rsmd.getColumnTypeName(i));   // - typ RDBMS
				}

			stmt.close();
			con.close();
		} 
		catch (SQLException exc) {System.out.println(exc.getMessage());	}
	}

}