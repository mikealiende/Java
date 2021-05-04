package Server;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: NotFull
 * TIEMPO: 
 * DESCRIPCION:  Servidor que sirve para indicar a los productores que hay espacio en la cesta y por lo tanto que pudeen seguir producioendos
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import ms.Envelope;
import ms.MessageSystem;

public class NotFull implements Runnable {

	private  int _identifier;
	private  MessageSystem _ms;
	private   String _userFile ;
	private  int _count;
	static String comando;
	private  ArrayList<Integer> _queue = new ArrayList<Integer>();
	
	public NotFull (int identifier,String usersFile, int count) {
		_identifier = identifier;
		_userFile = usersFile;
		_count = count;
				
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
				else if (comando.equals("WAIT") && _count >0) {
					_ms.send(sobre.getSource(),"NOTFULL");     //Eviamos confirmacion de que se puede acceder a la SC
					
					_count--;
				}
				else {       //SIGNAL
					_count++;
					if (!_queue.isEmpty()) {
						
						_ms.send(_queue.remove(0),"NOTFULL");
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
