package alumnos.p4.aliende.sc;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Server
 * TIEMPO: 
 * DESCRIPCION: Cuando recibe una peticion de algun cliente le envia el dato y luego espera a que el cliente porcese ese dato y lo recibe
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import alumnos.p4.aliende.ms.Envelope;
import alumnos.p4.aliende.ms.MessageSystem;


public class Server {
	
	private static MessageSystem _ms;
	private static final String _usersFile ="peers.txt"; // mutex = user 0, server = user 1
	
	private static int _value;

	public static void main(String[] args) {
		Message msg = new Message(0,null); 

		if (args.length < 1) { // Test para correcto # de args
			throw new IllegalArgumentException("Parameter(s): <n>");
		}
		_value = Integer.parseInt(args[0]);
		System.out.println("\nServer operativo, valor esogido: "+_value);
		
		try {
			_ms = new MessageSystem(1,_usersFile);//EL servidor sera el 1
			Envelope sobre;
			while(true) {
				sobre = _ms.receive();
				msg = (Message)sobre.getPayload();
				
				if(msg.getString().equals("PEDIR_DATO")) {
					
					msg.setValues(_value,"DATO");
					_ms.send(sobre.getSource(), msg);    //Enviamos al Cliente
					sobre = _ms.receive();    //Esperamos respuesta del cliente
					msg = (Message)sobre.getPayload();
					if (msg.getString().equals("RESPONSE")){
						_value = msg.getInt();
						System.out.println("\nValor modificado por "+sobre.getSource()+": "+_value);
					}					
				}
			}
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
