package p3.tcp;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import p3.tcp.Message;

public abstract class TransferMessages {
	public static Message receive(Socket sock)  throws IOException  { 
		
		Message msg=null;

		/*
		 * TO-DO: Utilizaci�n adecuada del m�todo readObject para leer el mensaje
		 */
		return msg;
	 }
	 
	
	public static void send(Message msg, Socket sock) throws IOException  {
		
		/*
		 * TO-DO: Utilizaci�n adecuada del m�todo writeObject para enviar el mensaje
		 */
	}

}
