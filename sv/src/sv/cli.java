package sv;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
 
public class cli {
 
    public static void main(String[] args) {
 
		try {
			int portNumber=10001;
	        System.out.println("Starting Java Socket Server ...");
	        ServerSocket aServerSocket = new ServerSocket(portNumber);;
	        System.out.println("Listening at port "+portNumber+"...");
	        while(true){
	        	Socket sock=aServerSocket.accept();
	        	InetAddress clientHost=sock.getLocalAddress();
	        	int ClientPort = sock.getPort();
	        	System.out.println("A client connect host : "+clientHost+",port : "+ClientPort);
	        	ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
	        	Object obj=instream.readObject();
	        	System.out.println("input : "+obj);
	        	ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
	        	outstream.writeObject(obj+"from Server.");
	        	outstream.flush();
	        	sock.close();
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    }
 
}