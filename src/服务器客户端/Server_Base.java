package 服务器客户端;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Monkey;
public class Server_Base {

    public static void main(String[] args) {
        try{
            System.out.println("等待连接");
            //1.创建服务器套接字
            ServerSocket serverSocket = new ServerSocket(5500);
            //2.侦听来自客户端的连接请求
            Socket connectFromClient = serverSocket.accept();
            System.out.println("连接请求来自："+connectFromClient.getInetAddress().getHostAddress());
 
            DataInputStream inFromClient = new DataInputStream(connectFromClient.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectFromClient.getOutputStream());
            ObjectOutputStream oop  =  new ObjectOutputStream(connectFromClient.getOutputStream());
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
                    //写物体
                    oop.writeObject(new Monkey());
                    oop.flush();
                    System.out.println("圆的面积"+str+"已经发送");
                }else{
                    goon = false;
                    outToClient.writeUTF("bye");
                    outToClient.flush();
                }
            }
            inFromClient.close();
            outToClient.close();
            serverSocket.close();
            connectFromClient.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
