package Spotkanie08;
import java.sql.*;
public class Zadanie01 {

	public static void main(String[] args) {

		String driverName = "org.apache.derby.jdbc.ClientDriver";
		String url = "jdbc:derby://localhost:1527/baza;create=true;";

		Connection con = null;

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url);
		} 
		catch (ClassNotFoundException exc){
			System.out.println("Brak klasy sterownika" + exc);
			System.exit(1);
		}
		catch (SQLException exc) {
			System.out.println("Nieudane po³¹czenie z " + url + exc);
			System.exit(1);
		}

		System.out.println("Po³¹czenie OK!");

		Statement stment = null;
		try {stment = con.createStatement();} 
		catch (SQLException ex) {
			System.out.println("B³¹d inicjalizacji" + ex);
			System.exit(1);
		}

		try {stment.execute("DROP TABLE TAB1");}
		catch (SQLException e){	System.out.println("TAB1 nie istnieje");}
		
		try{
			stment.execute("CREATE TABLE TAB1 ( id int not null, name varchar(20), age int not null, primary key(id))");
			stment.execute("INSERT INTO TAB1 ( id, name, age ) VALUES (1, 'Jan', 30), (2, 'Gosia', 40), (3, 'Stefan', 50)");
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}