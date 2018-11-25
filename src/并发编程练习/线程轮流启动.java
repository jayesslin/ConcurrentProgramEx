package 并发编程练习;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 2、编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按ABC的顺序显示；如：ABCABC….依次递推。
 */
public class 线程轮流启动 {
	
	    Object lock = new Object();
		int flag = 1;
		void printA() {
			synchronized(lock) {
				while(flag!=1) {
					try {
						lock.wait();
					} catch(Exception e) {
						
					}
				}
					flag= 2; 
					lock.notifyAll();
					System.out.println(Thread.currentThread().getName());
				
			}
		}
		 void printB() {
			synchronized(lock) {
				while(flag!=2) {
					try {
						lock.wait();
					} catch(Exception e) {
						
					}
				}
					flag= 3; 
					lock.notifyAll();
					System.out.println(Thread.currentThread().getName());
				
			}
		}
		 void printC() {
			synchronized(lock) {
				while(flag!=3) {
					try {
						lock.wait();
					} catch(Exception e) {
						
					}
				}
					flag= 1; 
					lock.notifyAll();
					System.out.println(Thread.currentThread().getName());
				
			}
		 }
	/*	
	Object x =new Object();
	private Lock lock = new ReentrantLock();
	int flag = 1;
	void printA() {
		try {
			
			lock.lock();
				while(flag!=1) {
					try {
						x.wait();
					} catch(Exception e) {
						
					}
				}
					flag= 2; 
					x.notifyAll();
					System.out.println(Thread.currentThread().getName());
		} finally {
		lock.unlock();}
	}
	 void printB() {
		 try {
				
				lock.lock();
			while(flag!=2) {
				try {
					x.wait();
				} catch(Exception e) {
					
				}
			}
				flag= 3; 
				x.notifyAll();
				System.out.println(Thread.currentThread().getName());
		 }finally {
					lock.unlock();}
		}
	
	 void printC() {
		 try {
				
				lock.lock();
			while(flag!=3) {
				try {
					x.wait();
				} catch(Exception e) {
					
				}
			}
				flag= 1; 
				x.notifyAll();
				System.out.println(Thread.currentThread().getName());
		 }
				finally {
					lock.unlock();}
		
	 }
	*/
	
		/* 下面方法报错原因， 一开始我以为是执行地太快了，在main里面sleep了一会，结果发现还是崩了，
		 *  原来同样的线程不能循环执行，这和线程的生命周期有关系。销毁了之后就不能再进行了。
		 *  Exception in thread "main" java.lang.IllegalThreadStateException
		    at java.base/java.lang.Thread.start(Thread.java:804)
		    at thread.Test4.main(Test4.java:30
		 */
		/*for(int i=0;i<10;i++) {
			A.start();
			B.start();
			C.start();
		}*/
		
		public static void main(String args[]) {	
			线程轮流启动 w = new 线程轮流启动();
			Thread A = new Thread(new Runnable() {
				 
				@Override
				 public void run() {
					for (int i = 0; i < 10; i++) {
						w.printA();
		            }
				 }
			 },"A");
			
			Thread B = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 10; i++) {
						w.printB();
		            }
				}
				
			},"B");
			Thread C = new Thread(()->  {
				for (int i = 0; i < 10; i++) {
					w.printC();
	            }
			},"C");
			A.start();
			System.out.println(1);
			B.start();
			System.out.println(1);
			C.start();
			System.out.println(1);
		}
		
}
