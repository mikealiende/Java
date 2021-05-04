package p05e03;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Recieves_Request_Reply
 * TIEMPO: 
 * DESCRIPCION: 
 */
import java.util.concurrent.Semaphore;

import ms.Envelope;
import ms.MessageSystem;

public class Recives_Request_Reply implements Runnable{
	
	private int _me;
	private SharedDataBase _shared_data_base;
	private Semaphore _shared_vars;
	private MessageSystem _ms;
	private Semaphore _wait_for;
	
	
	private int j;
	private boolean defer_it;
	
	public Recives_Request_Reply(int me, SharedDataBase shared_data_base, Semaphore shared_vars, MessageSystem ms, Semaphore wait_for) {
		_me = me;
		_shared_data_base = shared_data_base;
		_shared_vars  = shared_vars;
		_ms = ms;
		_wait_for = wait_for;
	}
		
	
	
	public void run() {
		
		
		while(true) {
			
			Envelope sobre = _ms.receive();
			j = sobre.getSource();
			
			
			
			if(sobre.getPayload().equals("REPLY")) {  
				
				 //REPLY
				_shared_data_base.Set_Outstandign_Reply_Count(_shared_data_base.Get_Outstandign_Reply_Count()-1);
				if(_shared_data_base.Get_Outstandign_Reply_Count() == 0) {
					_wait_for.release();
				}
			}else {	 //REQUEST
				
				defer_it = false;
				int k = (int)sobre.getPayload();
				System.out.println("our_secucne_number" + k);
				_shared_data_base.Set_HSN(Math.max(k, _shared_data_base.Get_HSN()));
				
				try {
					_shared_vars.acquire();  //WAIT
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				defer_it = _shared_data_base.Get_Requesting_CriticalSection() && ((k > _shared_data_base.Get_Our_Sequence_Number())
							||(k == _shared_data_base.Get_Our_Sequence_Number() && j > _me)); 
				
				_shared_vars.release();
				
				if(defer_it == true) {
					_shared_data_base.Set_Reply_Deferred(j, true);
				}else {
					System.out.println("Enviamos reply a: "+j);
					_ms.send(j, "REPLY");
				}
		
			}
		}
		
	}

}
