package 服务器客户端;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket socket;
	BufferedReader in;
	PrintWriter out;
    DataInputStream inFromClient ;
    DataOutputStream outToClient ;
	public ServerThread  (Socket socket) throws IOException{
		this.socket = socket;
		/*in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());*/
	    inFromClient = new DataInputStream(socket.getInputStream());
        outToClient = new DataOutputStream(socket.getOutputStream());

	}
	public void run() {
		try{
            System.out.println("等待连接");
            
            System.out.println("连接请求来自："+socket.getInetAddress().getHostAddress());
            
            /*DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());*/
 
            String str;
            double radius, area;
            boolean goon = true;
 
            while(goon){
                //从socket中读取数据
                str = inFromClient.readUTF();
                if(!str.equals("bye")){
                    radius = Double.parseDouble(str);
                    System.out.println("接收到的半径值为：\t"+radius);
                    area = radius*radius*Math.PI;
                    str = Double.toString(area);
                    outToClient.writeUTF(str);
                    outToClient.flush();
                    System.out.println("圆的面积"+str+"已经发送");
                    System.out.println("连接请求来自线程："+this.getName());
                }else{
                    goon = false;
                    outToClient.writeUTF("bye");
                    outToClient.flush();
                }
            }
            inFromClient.close();
            outToClient.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
	}
}
