package imprezogenerator;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.*;
import com.jenkov.db.*;
import com.jenkov.db.itf.*;
import com.jenkov.db.jdbc.*;

import imprezogenerator.Impreza;

class Baza 
{
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/imprezogenerator";
	private static String userID = "root";
	private static String pwd = "root";
	private static DataSource dataSource = new SimpleDataSource(driverName, dbUrl, userID, pwd);
	private static final PersistenceManager persistenceManager = new PersistenceManager(dataSource);
	private static IDaos daos = null;
	
// Test polaczenia  =========================================//
	
	public static boolean test()
	{
		try
		{
			daos = persistenceManager.createDaos();
			daos.getConnection().close();
			System.out.println("PPO£¥CZONO Z BAZ¥ DANYCH");
		}
		catch(Exception exc)
		{
			System.out.println("exception: " + exc.getMessage());
			return false;
		}
		return true;
	}

// Dodaj Impreze =============================================//
	
	public static boolean dodajImpreze(Impreza impreza){
		boolean OK = false;
		try
		{
			daos = persistenceManager.createDaos();
			daos.getObjectDao().update(impreza);
			System.out.println("Impreza DODANO");
		}
		catch(Exception exc)
		{
			System.out.println("exception: " + exc.getMessage());
		}
		finally
		{
			try
			{
				daos.getConnection().close();
				OK = true;
			}
			catch (SQLException exc) 
			{
				System.out.println("SQL except.: " + exc.getMessage());
			}
		}
		return OK;
	}
	
// Wyci¹gniêcie jednej imprezy z bazy ================================================//
	
	public static Impreza getImpreza(int ID)
	{
			String sql = "select * from impreza where ID_IMPREZA = "+ID;
			Impreza impreza = new Impreza();
			
			try
			{
				daos = persistenceManager.createDaos();
				impreza = (Impreza)daos.getObjectDao().read(Impreza.class, sql);
			}
			catch(Exception exc)
			{
				System.out.println("exception: " + exc.getMessage());
				return null;
			}
			finally{
				try
				{
					daos.getConnection().close();
				}
				catch (SQLException exc) 
				{
					System.out.println("SQL except.: " + exc.getMessage());
					return null;
				}
			}
			return impreza;
	}
	
// Dane Imprez -- lista =============================//
	
	public static List<?> getImprezy()
	{
		String sql = "select * from impreza";
		List<?> imprezy = new ArrayList<Object>();
		try
		{
			daos = persistenceManager.createDaos();
			imprezy = daos.getObjectDao().readList(Impreza.class, sql);
		}
		catch(Exception exc)
		{
			System.out.println("exception: " + exc.getMessage());
		}
		finally
		{
			try
			{
				daos.getConnection().close();
			}
			catch (SQLException exc) 
			{
				System.out.println("SQL except.: " + exc.getMessage());
			}
		}
		return imprezy;
		
	}
// Dane Organizatora===============================//
	
	public static List<?> getOrganizator()
	{
		String sql = "select * from organizator";
		List<?> organ = new ArrayList<Object>();
		
		try
		{
			daos = persistenceManager.createDaos();
			organ = daos.getObjectDao().readList(Organizator.class, sql);
		}
		catch(Exception exc)
		{
			System.out.println("exception: " + exc.getMessage());
		}
		finally
		{
			try
			{
				daos.getConnection().close();
			}
			catch (SQLException exc) 
			{
				System.out.println("SQL except.: " + exc.getMessage());
			}
		}
		return organ;
	}
	
//Dane Uzytkownika ============================================//
	
	public static Uzytkownik getUzytkownik(String login, String password)
	{
		
			String sql = "select * from Uzytkownik where LOGIN = '"+login + "' and PASSWORD = '" + password + "'";
			Uzytkownik uzytkownik = new Uzytkownik();
			try
			{
				daos = persistenceManager.createDaos();
				uzytkownik = (Uzytkownik)daos.getObjectDao().read(Uzytkownik.class, sql);
			}
			catch(Exception exc)
			{
				System.out.println("exception: " + exc.getMessage());
				return null;
			}
			finally
			{
				try
				{
					daos.getConnection().close();
				}
				catch (SQLException exc) 
				{
					System.out.println("SQL except.: " + exc.getMessage());
					return null;
				}
			}
			return uzytkownik;
	}

	
//Sprawdz czy istnieje taki rekord =================================//	
	
	public static boolean checkForExists(Class<?> x, int ID)
	{
		boolean wynik = false;
		try
		{
			daos = persistenceManager.createDaos();
			if(daos.getObjectDao().readByPrimaryKey(x, ID) != null)
				wynik = true;
		}
		catch(Exception exc)
		{
			System.out.println("exception: " + exc.getMessage());
		}
		finally
		{
			try
			{
				daos.getConnection().close();
			}
			catch (SQLException exc) 
			{
				System.out.println("SQL except.: " + exc.getMessage());
			}
		}
		return wynik;
	}
	
}
