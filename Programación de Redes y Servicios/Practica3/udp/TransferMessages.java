package p3.udp;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import p3.udp.Message;

public abstract class TransferMessages {
	public static Message receive(DatagramSocket sock)  throws IOException  { 
		
		Message msg=null;

		/*
		 * TO-DO: Utilizaci�n adecuada del m�todo readObject para leer el mensaje
		 */
		return msg;
	 }
	 
	
	public static void send(Message msg, DatagramSocket sock) throws IOException  {
		
		/*
		 * TO-DO: Utilizaci�n adecuada del m�todo writeObject para enviar el mensaje
		 */
	}

}
