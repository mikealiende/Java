package ServidorConcurrente;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: PerfectNumbersServer
 * TIEMPO: 
 * DESCRIPCION:  Servidor concuerrente que cuando le llega una peticion crea un hilo para que este la gestione
 */
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ms.Envelope;
import ms.MessageSystem; 
import Client.Interval;



public class PerfectNumbersServer {
	
	private static  MessageSystem _ms_server;
	private static  String _usersFile = "peers.txt";
	static Envelope sobre;
	static int i = 20;
	
	private boolean firstime = true;
	
	
	public static List<Long> getPerfectNumbers(Interval interval) {
		ArrayList<Long> perfectNumbersList = 
				new ArrayList<Long>();
		
		// Calculamos el conjunto de perfectos en el intervalo
		for (long i = interval.getLowerBound();
					i <= interval.getUpperBound(); i++) {
			int suma = 0;
			for (int j = 1; j <= i - 1; j++) {
				if (i % j == 0) // ¿es j un divisor de i?
					suma = suma + j;
			}
			if (suma == i){ //  i es Perfecto
				perfectNumbersList.add(i); 
			}
		} 
		return perfectNumbersList; 
	}
	
	
	
	public static void main(String args[]){ 
		
		MessageSystem _ms = null;
		try {
			_ms = new MessageSystem (26,_usersFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Interval interval;
		try {
			_ms_server = new MessageSystem(0,_usersFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			
			_ms.SetId(i);
			System.out.println("Servidor operativo");
			sobre = _ms_server.receive();
			System.out.println("han llegado datos de "+sobre.getSource());
			interval = (Interval)sobre.getPayload();
			
			_ms.SetId(i);
			Servers esc_server = new Servers(_ms,interval,(int)sobre.getSource());

			
			Thread mi_esc_server = new Thread(esc_server);
			mi_esc_server.start();
			i--;
			if(i == 11) {
				System.out.println("Acabamos ");
				i =20;
			}	
			
			
			
			
			
		} 
	}
	
}	