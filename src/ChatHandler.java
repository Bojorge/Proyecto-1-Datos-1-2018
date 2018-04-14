import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class ChatHandler extends Thread{
	protected Socket s;
	protected DataInputStream in;
	protected DataOutputStream out;
	protected String Host;
	
	public ChatHandler (Socket s)throws IOException{
		this.s = s;
		
//		para informacion de entrada desde el cliente
		in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		
//		para informacion de salida hacia el cliente
		out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
		
	}
//	cada vez que un usuario se conecta se va a almacenar en handler
	protected static Vector handlers = new Vector();
	
	public void run() {
//		obtiene la direccion del socket que se conecta
		String name = s.getInetAddress().toString();
		try {
			System.out.println("se ha unido "+name);
			
//			informa que se ha unido una direccion ip
			broadcast(name+" has joined ",name);
			this.Host = name;
			handlers.addElement(this);
			while(true) {
//				espera a que alguno de los que esta conectado escriba algo, lee el dataInpusStream
//				se ejecuta para cada uno de los clientes del servidor
				String msg = in.readUTF();
				System.out.println(" "+msg);
				broadcast(name+"mensaje recibido "+msg,name);
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		finally {
			handlers.removeElement(this);
			broadcast(name+" ",name);
			try {
				s.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	protected static void broadcast(String message,String hostEnvia) {
		synchronized(handlers) {
			Enumeration e = handlers.elements();
			while(e.hasMoreElements()) {
				ChatHandler c = (ChatHandler)e.nextElement();
				System.out.println("Envia "+hostEnvia+" se une "+c.Host);
//				if
				try {
					synchronized(c.out) {
						c.out.writeUTF(message);
					}
					c.out.flush();
				}catch(IOException ex) {
					c.stop();
				}
			}
		}
	}
}
