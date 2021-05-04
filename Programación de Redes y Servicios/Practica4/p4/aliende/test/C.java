/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package alumnos.p4.aliende.test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import alumnos.p4.aliende.ms.Envelope;
import alumnos.p4.aliende.ms.MessageSystem;

public class C {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		String networkFile = "peers.txt";
		
		try {
			MessageSystem ms = new MessageSystem(0, networkFile);
			String valor;
			Envelope e;
			int src;
			e = ms.receive();
			src = e.getSource(); // NEW
			valor = (String)(e.getPayload());
			System.out.println("PRIMERO RECIBO " + valor + " DE "+src);
			e = ms.receive();
			src = e.getSource(); // NEW
			valor = (String)(e.getPayload());
			System.out.println("SEGUNDO RECIBO " + valor+ " DE "+src);
			ms.stopMailbox();
		} catch (FileNotFoundException e) {
			System.err.println("El fichero " + networkFile + " no existe.");
		}
	}

}
