package Client;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: clientes
 * TIEMPO: 
 * DESCRIPCION:  Hilos que iran haciendo las peticiones y reciviendo las respueestas de los servidores
 */
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import ms.Envelope;
import ms.MessageSystem;

public class Clientes implements Runnable {
	private   MessageSystem _ms;
	//private   String _usersFile = "peers.txt";
	static Envelope sobre;
	int _id;
	int _ini;
	int _fin;
	int _servidor;
	public Clientes(MessageSystem ms,int ini, int fin, int servidor) {
		_ms  = ms;
		_ini = ini;
		_fin = fin;
		_servidor = servidor;
		
	}
	
	public void run() {
		
		
		Interval interval = new Interval (_ini,_fin);
		List <Long> perfectos =  new ArrayList <Long>();
		
		System.out.println("Enviamos mensaje "+_ms.getMe());
		
		
		
		
		_ms.send(_servidor, (Serializable) interval);  //Enviamos al servidor
		
		
		
		sobre = _ms.receive();
		
		System.out.println("Perfecto en el intervalo: "+_ini+" - "+_fin);
		perfectos = (List<Long>) sobre.getPayload();
		System.out.println("Perfectos de "+_ms.getMe()+": "+ Arrays.deepToString(perfectos.toArray())+"\n");
		
		
		
		
	}

}
