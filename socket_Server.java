package socket_connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class socket_Server {
	static ServerSocket listner;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			listner =new ServerSocket(6166);
			while(true){
				Socket socket = listner.accept();
				try{
					 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					 out.println("hello");
					 
				}finally{
					socket.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				listner.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
