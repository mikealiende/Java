package alumnos.p4.aliende.sc;

/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: SemaphoreServer
 * TIEMPO: 
 * DESCRIPCION: Implementa un patron mutex con paso de mensaje para gestionar los accesos a la seccion critica de los clientes y el servidor
 */
import java.util.ArrayList;

import alumnos.p4.aliende.ms.Envelope;
import alumnos.p4.aliende.ms.MessageSystem;

import java.io.FileNotFoundException;
import java.io.IOException;




public class SemaphoreServer {
	

	private static MessageSystem _ms;
	private static final String usersFile ="peers.txt"; // mutex = user 0, server = user 1
	
	private static int _count = 1;
	private static ArrayList<Integer> _queue = new ArrayList<Integer>();
	

	
	public static void main(String[] args) {
		System.out.println("\nSemaforo operativo\n");
		Message msg = new Message(0,null);
		
		
		try {
			_ms = new MessageSystem(0,usersFile);  //el Semaforo sera el 0
			Envelope sobre;
			while(true) {
				sobre= _ms.receive();
			
				 msg = (Message)sobre.getPayload();
				 
				
				 
				if(msg.getString().equals("WAIT") && _count == 0){
					
					_queue.add(sobre.getSource());
					
					System.out.println("Metemos en la cola a: "+sobre.getSource());
				}
				
				else if(msg.getString().equals("WAIT") && _count == 1) {
					msg.setValues(0, "OK");
					_ms.send(sobre.getSource(),msg);
					_count --;
				}
				else {
					_count ++;
					if(!_queue.isEmpty()) {
						msg.setValues(0, "OK");
						_ms.send(_queue.remove(0), msg);
						_count --;
					}
				}
						
			
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		/*
		 * TO-DO: Comportamiento del servidor semáforo:
		 * - gestión del contador y la cola
		 * - recepción de señalización (wait/signal)
		 * - respuesta a los clientes
		 * 
		 * Inicialización y utilización adecuada del MessageSystem
		 */
		
	}
}
