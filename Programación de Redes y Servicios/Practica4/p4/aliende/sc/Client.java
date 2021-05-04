package alumnos.p4.aliende.sc;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Client
 * TIEMPO: 
 * DESCRIPCION: Pide al servidor mediante semaforos con paso mensaje permiso para acceder a una variable compartida, la modifica y luego la devuelve.
 * 				
 */

import java.io.IOException;

import alumnos.p4.aliende.ms.*;

public class Client {

	private static MessageSystem _ms;
	private static final String _usersFile ="peers.txt"; // mutex = user 0, server = user 1

	public static void main(String[] args) throws Exception{

		int mutex = 0;
		int server = 1;
			
		if (args.length < 3) { // Test para correcto # de args
			throw new IllegalArgumentException("Parameter(s): <user> <time[sec]> <n>");
		}
		int me = Integer.parseInt(args[0]); 
		long time = Long.parseLong(args[1]);
		int value = Integer.parseInt(args[2]); 
		
		Message msg = new Message(0, null);
		_ms = new MessageSystem(me, _usersFile);
		
		Envelope sobre;
		msg.setValues(0,"WAIT");
		
		_ms.send(0, msg);   //Bloqueamos Semaforo para acceder a SC
		System.out.println("\nEnviamos peticion.");
		sobre = _ms.receive();
		msg = (Message)sobre.getPayload();
		
		if(msg.getString().equals("OK")) {  //Confirmacion del semaforo
			msg.setValues(value, "PEDIR_DATO");
			_ms.send(1, msg);
			
			sobre = _ms.receive();			//Esperamos respuesta del server
			msg = (Message)sobre.getPayload();
			
			if(msg.getString().equals("DATO")) {
				System.out.println("\nHemos recibido "+msg.getInt()+" y le vamos a sumar "+value);
				value = value + msg.getInt();
				
				Thread.sleep(time);
				msg.setValues(value, "RESPONSE");	
				_ms.send(1,msg);
				System.out.println("\nCalculo completo y modificación enviada");
				msg.setValues(0, "SIGNAL");
				_ms.send(0,msg);
			_ms.stopMailbox();
			}
			
		}
		
			
		
		


	
	
	}
}
