package p05e03;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: InvokeMutex
 * TIEMPO: 
 * DESCRIPCION: 
 */

import java.util.concurrent.Semaphore;

import ms.MessageSystem;

public class Invoke_Mutex implements Runnable{

	private int _me; 
	private SharedDataBase _shared_data_base;
	private Semaphore _shared_vars;
	private int _N;
	MessageSystem _ms;
	private Semaphore _wait_for;
	
	
	public Invoke_Mutex( int me, SharedDataBase shared_data_base, Semaphore shared_vars, MessageSystem ms, Semaphore wait_for ) {
		_me = me;
		_shared_data_base = shared_data_base;
		_shared_vars  = shared_vars;
		_ms = ms;
		_wait_for = wait_for;
	}


	public void run() {
		
		while(true) {
			
			
			//PreProtocol
			try {
				_shared_vars.acquire();    //WAIT
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			_shared_data_base.Set_Requesting_CriticalSection(true);   
			_shared_data_base.Set_Our_Sequence_Number(_shared_data_base.Get_HSN()+1);
			
			_shared_vars.release();
			
			_shared_data_base.Set_Outstandign_Reply_Count(_shared_data_base.Get_N()-1);
			
			for(int j =0;j < _shared_data_base.Get_N();j++) {
				if(j != _me) {
				
					_ms.send(j, _shared_data_base.Get_Our_Sequence_Number());
				}	
			}
			
			try {
				_wait_for.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//Critical Section
			System.out.println("Critical Section processing can be performed at this point");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Release the Critical Section");
			
			_shared_data_base.Set_Requesting_CriticalSection(false);
			
			
			//PostProtocol
			boolean [ ] reply_deferred = _shared_data_base.Get_Reply_Deferred();
			for(int j =0; j < _shared_data_base.Get_N(); j++) {
				if(reply_deferred	[j] == true) {
					_shared_data_base.Set_Reply_Deferred(j, false);   //reply_deferred[j] = false
					_ms.send(j, "REPLY");
				}
			}
			
			
			
			
			
			
		}
	
	
	}
	
}	