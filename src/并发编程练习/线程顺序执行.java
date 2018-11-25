package 并发编程练习;
/*
 * 写一个程序，线程C在线程B后执行，线程B在线程A之后进行
 */
public class 线程顺序执行 {
	//第一种方法 写在 线程的run里 
	
	public static  void InAOrder1(){
	 Thread A = new Thread(new Runnable() {
		 @Override
		 public void run() {
			System.out.println("======+A+===="); 
		 }
	 });
	 Thread B = new Thread(()-> {
		try {
			A.join();
		}catch (Exception e) {
			
		}
		System.out.println("=======+B+=======");
	 });
	 Thread C = new Thread(new Runnable() {
		 @Override
		public void run() {
			 try {
				B.join();
			 }catch(Exception e) {
			
		}
			 System.out.println("+++++++C++++++");
		 }
	 });
	 A.start();
	 B.start();
	 C.start();
	}
	
	
	//第二种方法，在外调join方法
	public static void InAOrder2() {
		Thread A  = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("A");
			}
			
		});
		Thread B = new Thread(()-> {
			System.out.println("B");
		});
		Thread C =new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("c");
			}
			
		});
		try {
		A.start();
		A.join();
		B.start();
		B.join();
		C.start();
		C.join();
		}catch(Exception e) {
			
		}
	}
	public static void main(String args[]) {
		//test
		InAOrder1();
		InAOrder2();
	}
}
