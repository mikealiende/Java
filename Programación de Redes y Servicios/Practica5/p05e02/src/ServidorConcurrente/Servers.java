package ServidorConcurrente;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Servers
 * TIEMPO: 
 * DESCRIPCION:  hilos creados por el servidor para procesar las peticiones y enviarle la respuesta a los clientes
 */
import java.io.FileNotFoundException;
import Client.Interval;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import ms.MessageSystem;

public class Servers implements Runnable {

	Interval _interval;
	int _id;
	int _source;
	private MessageSystem _ms;
	//private   String _usersFile = "peers.txt";
	
	public Servers(MessageSystem ms, Interval interval, int source ) {
		_ms = ms;
		_interval = interval;
		_source = source;
		
	}
	
	
	public void run() {
		
		System.out.println("Intervalo "+_interval.getLowerBound()+"-"+_interval.getUpperBound());
		System.out.println("somos "+_ms.getMe()+" y vamos a hacer calculos para: "+_source+"\n");
		List <Long> perfectos = PerfectNumbersServer.getPerfectNumbers(_interval);
		
		_ms.send(_source, (Serializable) perfectos);

	 
 }


}


