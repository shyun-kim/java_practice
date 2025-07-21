package chat_practice;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client_practice {
	
	public static void main (String [] args) {
		try {
			String id = "김아무개";
			final String HOST = "localhost";
			final int PORT = 9000;
			
			Frame f = new Frame("채팅 클라이언트");
			Panel p = new Panel();
			
			f.setLayout(new BorderLayout());
			TextField tf = new TextField(40);
			TextArea ta = new TextArea();
			Button send = new Button("전송");
			Button button1 = new Button("종료");
			ta.setEditable(false);
			tf.setText("["+id+"]: ");
			p.add(tf);
			p.add(send);
			
			Socket s = new Socket(HOST, PORT); //localhost, 127.0.0.1, 192.168.50.54
			System.out.println("클라이언트 접속 성공");
			
			DataInputStream input = new DataInputStream(s.getInputStream());
			DataOutputStream output = new DataOutputStream(s.getOutputStream());
			
			new ServiceThread(input, ta).start();
			
			f.add(p, BorderLayout.NORTH);
			f.add(ta, BorderLayout.CENTER);
			f.add(button1, BorderLayout.SOUTH);
			f.setSize(400,500);
			f.setVisible(true);
			
			//전송 버튼 이벤트
			send.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						output.writeUTF(tf.getText());
						tf.setText("["+id+"]: ");					
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});
			
			//종료 이벤트
			button1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("--클라이언트 종료--");
					try {
						output.writeUTF("exit");						
					} catch (Exception e2){
						
					}
					System.exit(0);
				}
			});
		} catch (Exception e) {
			
		}
		
		
		
	}
	
}


class ServiceThread extends Thread{
	DataInputStream input;
	TextArea ta;
	
	public ServiceThread(DataInputStream input, TextArea ta) {
		this.input = input;
		this.ta = ta;
	}
	
	public void run() {
		while(true) {
			try {
				ta.append(input.readUTF()+"\n");				
			} catch (Exception e) {
				
			}
		}
	}
}