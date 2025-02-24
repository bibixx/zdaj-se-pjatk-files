package Spotkanie09;
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
		
		try {stment = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);} 
		catch (SQLException ex) {System.out.println("B³¹d inicjalizacji" + ex);	System.exit(1);	}
		
		try {stment.execute("DROP TABLE Ksiazki");}
		catch (SQLException e){	System.out.println("Ksiazki nie istnieje");	}
		
		try {stment.execute("DROP TABLE Autorzy");}
		catch (SQLException e){	System.out.println("Autorzy nie istnieje");	}
		
		try
		{
			System.out.println("Tworzenie tabeli Autorzy");
			stment.execute("CREATE TABLE Autorzy ( id int not null, name varchar(20), age int not null, primary key(id))");
			
			System.out.println("Tworzenie tabeli Ksiazki");
			stment.execute("CREATE TABLE Ksiazki ( id int not null, kto int, nazwa varchar(20), primary key(id), FOREIGN KEY(kto) REFERENCES Autorzy(id))");

			System.out.println("Uzupelnianie danymi");
			stment.execute("INSERT INTO Autorzy (id, name, age) VALUES (1, 'Jan', 30), (2, 'Gosia', 40), (3, 'Stefan', 50)");
			stment.execute("INSERT INTO Ksiazki (id, kto, nazwa) VALUES (1, 1, 'Ksiazka Jana'), (2, 2, 'Ksiazka Gosii'), (3, 3,'Ksiazka Stefana')");
			
//			ResultSet rs = stment.executeQuery("SELECT * FROM Autorzy INNER JOIN Ksiazki ON ksiazki.id = autorzy.id");
			ResultSet rs = stment.executeQuery("SELECT * FROM Autorzy");
			
			for (int i=0; i<2; i++) 
			{
				rs.next();
				System.out.println(rs.getInt("id")+ ". " + rs.getString("name") + " " + rs.getInt("age"));
			}
			System.out.println("jestem na trzecim!");
			rs.updateString("name", "NieStefan");
			rs.updateRow();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try {stment = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);} 
		catch (SQLException ex) {System.out.println("B³¹d inicjalizacji" + ex);	System.exit(1);	}
		
		try
		{
			ResultSet rs = stment.executeQuery("SELECT * FROM Autorzy");
			while (rs.next()){
				System.out.println(rs.getInt("id")+ ". " + rs.getString("name") + " " + rs.getInt("age"));
			}
			rs.absolute(5);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
	}

}