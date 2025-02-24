package Spotkanie10;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Zadanie01_Serwer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(666);
        
        while (true){
            Socket sin = ss.accept();
            Runnable r = new ObslugaKlienta(sin);
            Thread t = new Thread(r);
            t.start();
        }
    }
}

class ObslugaKlienta implements Runnable {

	private Socket sin;
    ObslugaKlienta(Socket s) { sin = s; }

    public void run(){
    	
        try {
            try {
                InputStream input = sin.getInputStream();
                OutputStream output = sin.getOutputStream();
    			Scanner in = new Scanner(input); 
    			PrintWriter out = new PrintWriter(output, true);
    			
                boolean quit = false;
    			while (quit==false)
    			{
    				String linia = in.nextLine();
    				System.out.println(linia);
    				out.println(linia);
    				if (linia.equals("quit")) quit=true;
    			}
            } 
            finally { sin.close(); }
        } 	
        catch (Exception e) {}
    }
}