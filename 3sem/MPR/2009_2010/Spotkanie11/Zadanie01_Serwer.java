package Spotkanie11;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Zadanie01_Serwer {

	public static ByteBuffer toByteBuffer(String a) {
		a = a.length() + "X" + a; 
		ByteBuffer b = ByteBuffer.allocate(a.length());
		b.put(a.getBytes());
		b.rewind();
		return b;
	}
	public static int getLength(SocketChannel channel) throws IOException {
		String s = "";
		ByteBuffer r = ByteBuffer.allocate(1);
		byte[] b1 = new byte[1];
		while (s.contains("X") == false) {
			r.rewind();
			channel.read(r);
			r.rewind();
			r.get(b1);
			s += new String(b1);
		}
		return Integer.parseInt(s.split("X")[0].trim()); 
//		return Integer.parseInt((String) s.subSequence(s.length()-3, s.length()-1));
	}
    public static String toString(ByteBuffer b){
		String s="";
    	b.rewind();
		while (b.hasRemaining()) s+=(char)b.get();
    	return s;
    }
	public static String getResp(SocketChannel channel) throws IOException {
		
		ByteBuffer r = ByteBuffer.allocate(getLength(channel));
		r.rewind();
		channel.read(r);
		System.out.println(toString(r));
		return toString(r);
	}

	public static void main(String[] args) throws InterruptedException {
		try {

			ServerSocketChannel channel = ServerSocketChannel.open();
			channel.socket().bind(new InetSocketAddress(777));
			channel.configureBlocking(false);

			Selector selector = Selector.open();
//			SelectionKey sscKey = 
				channel.register(selector, SelectionKey.OP_ACCEPT);
			boolean dziala=true;
			
			while (dziala) {
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iter = keys.iterator();
				while (iter.hasNext()) {
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
				
					if (key.isAcceptable()) {
						SocketChannel cc = channel.accept();
						cc.configureBlocking(false);
						cc.register(selector, SelectionKey.OP_READ);
						continue;
					}

					if (key.isReadable()) {
						SocketChannel cc = (SocketChannel) key.channel();
						
						if (getResp(cc).equals("hello from client")) {
							if (Math.random() > 0.3) {
								cc.write(toByteBuffer("welcome, please start your work"));
								if (getResp(cc).equals("what is your time?")) cc.write(toByteBuffer(new Date() + ""));
								else if (getResp(cc).equals("shutdown")) { cc.write(toByteBuffer("shutting down...")); dziala=false;}
								cc.close();
							} 
							else { cc.write(toByteBuffer("you are not welcome, please disconenct")); cc.close(); }
						}}
						continue;
					}
				}
//			}
//			 channel.close();
		}
		catch (ConnectException e) {
			System.out.print(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
