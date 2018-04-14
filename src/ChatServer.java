import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public ChatServer(int port) throws IOException{
		ServerSocket server = new ServerSocket(port);
		while(true) {
			Socket client = server.accept();
			System.out.println("aceptado desde "+client.getInetAddress());
			ChatHandler c = new ChatHandler(client);
			c.start();
		}
	}
}
