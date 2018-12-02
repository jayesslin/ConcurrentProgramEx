package 服务器客户端;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
public class Client_Base {
 
    public static void main(String[] args) {
        try {
            //1.创建套接字，端口号为5500，连接到本机
            Socket connectToServer = new Socket("192.168.3.4",5500);
            //2.将输入流连接到socket上
            DataInputStream inFromServer = new DataInputStream(connectToServer.getInputStream());
            //3. 将输出流连接到socket上
            DataOutputStream outToServer = new DataOutputStream(connectToServer.getOutputStream());
            //4.输入半径数值发送到服务器，输入bye结束。
            System.out.println("输入半径数值发送到服务器，输入bye结束。");
 
            String outStr,inStr;
            boolean goon = true;
            BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
            while(goon){
                outStr = buf.readLine();
                //4.向服务器发送消息，消息值为半径。
                outToServer.writeUTF(outStr);
               //5.清空发送消息的缓冲区     
                outToServer.flush();
               //6.接受服务器发送的消息
                inStr = inFromServer.readUTF();
                if(!inStr.equals("bye")){
                    System.out.println("从服务器返回的结果是："+inStr);
                }else
                    goon = false;
            }
 
            inFromServer.close();
            outToServer.close();
            connectToServer.close();
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
 
}
