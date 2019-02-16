package ClientController;



import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.Monkey;
import view.GameGUI;

public class GameController {
	
	public void MoveMonkey(Socket socket, String str) {
        //对象输入流
        try {
        	//2.将输入流连接到socket上
            DataInputStream inFromServer = new DataInputStream(socket.getInputStream());
            //3. 将输出流连接到socket上
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            //对象输入流
			ObjectInputStream objis =  new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Object obj;
			
			try {
				outToServer.writeUTF(str);
				outToServer.flush();
				obj = objis.readObject();
				Monkey monkeyreturn = (Monkey)obj;
				Monkey s = Monkey.getInstance();
				s.setScore(monkeyreturn.getScore());
				s.setX(monkeyreturn.getX());
				s.setY(monkeyreturn.getY());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

	}

