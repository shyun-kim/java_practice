package chat_practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server_Practice {
	public final static int PORT = 9000;
	public static ArrayList<ClientHandler> list =new ArrayList<ClientHandler>();
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("서버 실행 중(Port): "+PORT);
			System.out.println("클라이언트 접속 대기중");
			
			while(true) {
				Socket s= server.accept();
				System.out.println("클라이언트 접속");
				ClientHandler ch = new ClientHandler(s);
				list.add(ch);
				ch.start();
			}
		} catch(Exception e) {
			
		}
	}//main

}//server_practice

class ClientHandler extends Thread{
	Socket s;
	DataOutputStream output;
	DataInputStream input;
	
	public ClientHandler (Socket s) {
		try {
			this.s=s;
			this.output=new DataOutputStream(s.getOutputStream());
			this.input=new DataInputStream(s.getInputStream());
			String str = "[서버] 환영합니다.";
			output.writeUTF(str);			
		} catch(Exception e) {
			
		}
	}
	
	public void run() {
		try {
			boolean flag = true;
			while(flag) {
				String receiveMsg = input.readUTF();
				if(receiveMsg.equals("exit")) {
					flag = false;
				} else {
					Server_Practice.list.forEach(ch ->{
					try {
						ch.output.writeUTF(receiveMsg);
					} catch (Exception e) {
						
					}
					});						
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


