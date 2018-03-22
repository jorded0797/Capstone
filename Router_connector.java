package socket_connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Router_connector {
	static ServerSocket socket;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			socket = new ServerSocket(6616);
			Socket listner = socket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
