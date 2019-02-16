package ClientController;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientHttp {
	public static void main(String[] args) {
		Socket connectToServer;
		try {
			connectToServer = new Socket("192.168.3.4",5500);
			//2.将输入流连接到socket上
	        DataInputStream inFromServer = new DataInputStream(connectToServer.getInputStream());
	        //3. 将输出流连接到socket上
	        DataOutputStream outToServer = new DataOutputStream(connectToServer.getOutputStream());
	        //对象输入流
	        ObjectInputStream objis =  new ObjectInputStream(new BufferedInputStream(connectToServer.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
