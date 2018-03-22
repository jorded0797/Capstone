package socket_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	static ServerSocket listner;
	static ServerSocket listner2;
	public void run(){
		try{
			listner =new ServerSocket(6166);
			listner2 =new ServerSocket(6167);
			System.out.println("running thread Server");
			while(true){
				Socket socket = listner.accept();
				Socket socketout = listner2.accept();
				try{
					 PrintWriter out = new PrintWriter(socketout.getOutputStream(), true);		
					 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					 String answer = input.readLine();
					 if(answer!=null){
						 System.out.println(answer);
						 out.println("ack");
						 
					 }
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
