package p3.tcp;

import java.net.InetAddress;

import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import p3.tcp.Message;
import p3.tcp.TransferMessages;

public class PrimesClient {


	public static void main(String args[]) throws Exception {
		
		if (args.length < 4) { // Test para correcto # de args
			throw new IllegalArgumentException("Parameter(s): <HostServer> <ServerPort> <ini> <fin>");
			/*
			 * Argumentos: 	<Num> n?mero de servidores, y para cada uno de ellos:
			 * 				<ServerN> <PortN> direcci?n IP y puerto del servidor N-?simo
			 */
		}

		/*
		 * TO-DO: lectura de argumentos de entrada
		 */

		Random rand = new Random(); // inicialización para variables aleatorias


		/*
		 * TO-DO: 
		 * ****** Utilización ADECUADA de SOCKETS TCP *****
		 * Generación del mensaje a enviar
		 * Conexión con el servidor y envío del mensaje
		 * Esperar respuesta 
		 * Mostrar por pantalla la lista de primos
		 */
	}
}




