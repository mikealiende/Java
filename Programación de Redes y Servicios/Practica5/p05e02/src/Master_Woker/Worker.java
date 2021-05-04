package Master_Woker;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Worker
 * TIEMPO: 
 * DESCRIPCION:  Se trata de las clases que van a hacer las cuentas, estos worker estaran esperando a que un master le envie una  peticion, estos haran las correspondientes
 *				 cuentas y le enviaran la respuesta al master, son transparentes a los clientes y los clientes transparentes a ellos.
 */
import java.io.FileNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Client.Interval;
import ms.Envelope;
import ms.MessageSystem;

public class Worker {

	public static List<Long> getPerfectNumbers(Interval interval) {
		ArrayList<Long> perfectNumbersList = new ArrayList<Long>();
		
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

	
	
	
	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Worker operativo");
		int worker = Integer.parseInt(args[0]);
		String networkFile = "peers.txt";
		Envelope sobre;
		
		MessageSystem _ms = new MessageSystem(worker, networkFile);
		
		while(true) {
			sobre = _ms.receive();
			System.out.println("Algo nos ha llegado");
			Interval interval = (Interval)sobre.getPayload();
			
			
			
			List <Long> perfectos = getPerfectNumbers(interval);
			_ms.send(sobre.getSource(), (Serializable)perfectos);
			
		}
		
		
		
		
	}
}
