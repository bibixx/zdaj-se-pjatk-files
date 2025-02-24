package Spotkanie10;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Zadanie01_Klient {
    public static void main(String[] args) throws Exception {
    	try{
    		Socket sin = new Socket(InetAddress.getByName("localhost"), 666);
    		InputStream input = sin.getInputStream();
			OutputStream output = sin.getOutputStream();		
			Scanner in = new Scanner(input); 
    		PrintWriter out = new PrintWriter(output, true);
    		BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
    		String s;
    		while((s=in2.readLine()).length()!=0){
    			out.println(s);
    			System.out.println(in.nextLine());
    		}
    	}
    	catch (ConnectException e) {System.out.println("Serwer nie odpowiada.");}
    }
}

