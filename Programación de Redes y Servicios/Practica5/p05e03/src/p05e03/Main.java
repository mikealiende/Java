package p05e03;

import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;

import ms.MessageSystem;

public class Main {
	
	private static int me;
	private static int N;
	private static String usersFile = "peers.txt";
	
	public static void main(String args[]) {
		
		me = Integer.parseInt(args[0]);
		N = Integer.parseInt(args[1]);
		
		
		boolean requesting_critical_section = false;
		boolean[ ] Reply_deferred = new boolean [N];		
		for(int i = 0;i< N ;i++) {
			Reply_deferred[i] = false;   //Inicliazamos Reply_Deferred a false
		}
		
		try {
			 MessageSystem _ms = new MessageSystem(me, usersFile);
		
		
			 System.out.println("Emepzamos");
			 SharedDataBase shared_data_base = new SharedDataBase( N,requesting_critical_section,Reply_deferred);
			 Semaphore shared_vars = new Semaphore(1);
			 Semaphore wait_for = new Semaphore(0);
		
			 Invoke_Mutex inv_mutex = new Invoke_Mutex(me,shared_data_base,shared_vars,_ms,wait_for);
			 Recives_Request_Reply recives = new Recives_Request_Reply(me,shared_data_base,shared_vars,_ms,wait_for);
		
			 Thread mi_inv_mutex = new Thread(inv_mutex);
			 Thread mi_recives = new Thread (recives);
		
	
			Thread.sleep(3000);
			mi_inv_mutex.start();
			mi_recives.start();
		} catch (InterruptedException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

}
