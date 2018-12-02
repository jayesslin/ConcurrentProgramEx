package 服务器客户端;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool_Server {
	
		Socket socket;
	    /**线程池**/
	    ExecutorService executors;
	    public ThreadPool_Server() throws Exception{
	        /**服务器绑定端口**/
	         ServerSocket server = new ServerSocket(5500);
	         /**创建线程池**/
	         executors = Executors.newCachedThreadPool();
	        /**时刻等待客户端发出请求**/
	        while(true){
	            socket = server.accept();
	            //把用户每一次请求放入到线程池中
	            executors.execute(new ServerThread(socket));
	        }
	    
	}
	    public static void main(String[] args) {
	    	try {
				new ThreadPool_Server();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

