/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package alumnos.p4.aliende.test;

import java.io.FileNotFoundException;

import alumnos.p4.aliende.ms.MessageSystem;

public class A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String networkFile = "peers.txt";
	
		
		try {
			MessageSystem ms = new MessageSystem(1, networkFile);
			ms.send(0, "hola soy 1");
			ms.stopMailbox();
		} catch (FileNotFoundException e) {
			System.err.println("El fichero " + networkFile + " no existe.");
		}
	}

}
