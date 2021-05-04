package practica1;

public class HelloRunnable implements Runnable{
	
 long id;
	
	public void run () {
		System.out.println("Thread: "+ id);
	}
	
	public static void main (String args[]) {
		
			
		
		for(int i = 0; i<50; i++) {
			HelloRunnable miRunnable = new HelloRunnable();
			Thread miThread = new Thread(miRunnable);
			   
			miRunnable.id = miThread.getId();
			miThread.start();  //El metodo Start llama al run
			
			
		}
			
	}
}
