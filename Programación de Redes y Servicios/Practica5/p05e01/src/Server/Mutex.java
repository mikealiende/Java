package Server;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Mutex
 * TIEMPO: 
 * DESCRIPCION:  Serviodor mutex que se encarga de gestionar los accesos a la seccion critica
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import ms.Envelope;
import ms.MessageSystem;

public class Mutex implements Runnable{
	private  int _identifier;
	private  MessageSystem _ms;
	private static  String _userFile ;
	private  int _count  = 1;
	static String comando;
	private  ArrayList<Integer> _queue = new ArrayList<Integer>();
	
	public Mutex (int identifier,String usersFile) {
		_identifier = identifier;
		_userFile = usersFile;
				
	}
	
	
	public void run() {
		
		try {
			_ms = new MessageSystem(_identifier , _userFile);
			Envelope sobre;
			
			
			
			while (true) {
				
				sobre = _ms.receive();
				
				comando = (String)sobre.getPayload();
				
				if(comando.equals("WAIT")&& _count == 0) {
					_queue.add(sobre.getSource());    //Metemos en la cola
					
				}
				else if (comando.equals("WAIT") && _count ==1) {
					_ms.send(sobre.getSource(),"OK");     //Eviamos confirmacion de que se puede acceder a la SC
					
					_count--;
				}
				else {       //SIGNAL
					_count++;
					if (!_queue.isEmpty()) {
						
						_ms.send(_queue.remove(0),"OK");
						_count --;
					}
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
