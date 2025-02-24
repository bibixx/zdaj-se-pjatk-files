package Spotkanie11;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Zadanie01_Klient {

    public static ByteBuffer toByteBuffer(String a){
    	a = a.length() + "X" + a + "\n"; // \n mozna skasowac jak bedzie serwer, w echo musi byc niestety
    	ByteBuffer b=ByteBuffer.allocate(a.length());
    	b.put(a.getBytes());
    	b.rewind();
    	return b;
    }
	public static int getLength(SocketChannel channel) throws IOException
	{
		ByteBuffer r = ByteBuffer.allocate(1);
		byte[] b1 = new byte[1]; 
		String dlugosc = "";
		while (new String(b1).equals("X")==false) {
			r.rewind();
			channel.read(r);
			r.rewind(); 
			r.get(b1);
			if (b1[0]==0) {}
			else dlugosc+=new String(b1);
		}
		return Integer.parseInt(dlugosc.substring(dlugosc.length()-3, dlugosc.length()-1));
	}
	public static String getResp(SocketChannel channel) throws IOException
	{
		ByteBuffer r = ByteBuffer.allocate(1);
		byte[] b1 = new byte[1];
		int dlugosc = getLength(channel);
		String odpowiedz="";
		for (int i=0; i<dlugosc; i++){
			r.rewind();
			channel.read(r);
			r.rewind(); 
			r.get(b1);
			if (b1[0]!=0) odpowiedz+=new String(b1);
		}
		return odpowiedz;
	}
	
	public static void main(String[] args) throws InterruptedException {
		try{
			SocketChannel channel = SocketChannel.open();
			channel.connect(new InetSocketAddress("localhost", 777));
			channel.configureBlocking(false);
			
			System.out.println("wysylam: hello from client");
			channel.write(toByteBuffer("hello from client"));
			
			String odpowiedz = getResp(channel);

			System.out.println(odpowiedz);
			
			if (odpowiedz.equals("welcome, please start your work")){ 
				System.out.println("\nwysylam: what is your time?");
				channel.write(toByteBuffer("what is your time?"));
				System.out.println(getResp(channel));
			}
			channel.close();			
		} 
		
		catch (ConnectException e){	System.out.print(e.toString());} 
		catch (IOException e){e.printStackTrace();}
	}
}
