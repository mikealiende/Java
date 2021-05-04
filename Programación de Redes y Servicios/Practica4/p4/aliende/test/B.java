/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package alumnos.p4.aliende.test;

import java.io.FileNotFoundException;

import alumnos.p4.aliende.ms.MessageSystem;

public class B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String networkFile = "peers.txt";
		
		try {
			MessageSystem ms = new MessageSystem(2, networkFile);
			ms.send(0, "Hola soy 2");
			ms.stopMailbox();
		} catch (FileNotFoundException e) {
			System.err.println("El fichero " + networkFile + " no existe.");
		}
	}

}
