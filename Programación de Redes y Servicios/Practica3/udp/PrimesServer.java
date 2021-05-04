package p3.udp;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Random;

import p3.udp.Primes;
import p3.udp.TransferMessages;
import p3.udp.Message;

public class PrimesServer {
	
	public static final int MAXPRIMES = 8192;
	
	
	public static void main(String args[]) throws Exception {
	
		if (args.length == 0 || args.length > 1) { // Test para correcto # de args
			throw new IllegalArgumentException("Use: >$java SimpleServerUDP <Port>");
		} 

		/*
		 * TO-DO: lectura de argumentos de entrada
		 */

		/*
		 * TO-DO: 
		 * ****** Utilizaci�n ADECUADA de SOCKETS UDP *****
		 * Espera continuada de peticiones de clientes
		 * Gesti�n iterativa (secuencial) de cada petici�n:
		 *  --- utilizaci�n de la clase Primes para el c�lculo de primos
		 *  
		 *  int [] primes= Primes.GetPrimes(ini, fin);
		 */
    }
}
