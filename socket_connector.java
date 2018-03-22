package socket_connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class socket_connector {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Socket socket = new Socket("localhost",6167);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String answer = input.readLine();
			System.out.println(answer);
		}catch(Exception e){
			
		}
	}

}
