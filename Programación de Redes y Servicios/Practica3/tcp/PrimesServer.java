package p3.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import p3.tcp.Primes;
import p3.tcp.TransferMessages;
import p3.tcp.Message;

public class PrimesServer {
	
	
	public static void main(String args[]) throws Exception {
	
		int port;
		
		if (args.length == 0 || args.length > 1) { // Test para correcto # de args
			throw new IllegalArgumentException("Use: >$java BasicServerTCP <Port>");
		} 

		/*
		 * TO-DO: lectura de argumentos de entrada
		 */

		/*
		 * TO-DO: 
		 * ****** Utilizaci�n ADECUADA de SOCKETS TCP *****
		 * Espera continuada de peticiones de cliente
		 * Gesti�n iterativa (secuencial) de cada petici�n:
		 *  --- utilizaci�n de la clase Primes para el c�lculo de primos
		 *  
		 *  int [] primes= Primes.GetPrimes(ini, fin);
		 */
	}
}
