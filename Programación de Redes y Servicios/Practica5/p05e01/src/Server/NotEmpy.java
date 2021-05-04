package Server;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: NotEmpy
 * TIEMPO: 
 * DESCRIPCION:  Servidor que sirve para indicar a la costurera ensambladora que hay algo en la cesta, es decir que hay al menos 2 mangas y un cuerpos
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import ms.Envelope;
import ms.MessageSystem;

public class NotEmpy implements Runnable {
	
	private int  _identifier;
	private  MessageSystem _ms;
	private String _userFile;
	private  int _count  = 0;
	static String comando;
	private  ArrayList<Integer> _queue = new ArrayList<Integer>();
	
	public NotEmpy(int identifier, String usersFile) {
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
				else if (comando.equals("WAIT") && _count >=1) {
					_ms.send(sobre.getSource(),"NOT_EMPY");   
				}
				else {       //SIGNAL
					_count++;
					
					if (!_queue.isEmpty()) {

						_ms.send(_queue.remove(0),"NOT_EMPY");  
						
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


