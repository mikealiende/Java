package p3.udp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;
import java.util.Random;

import p3.udp.Message;
import p3.udp.TransferMessages;

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
		 * TO-DO: lectura de argumentos
		 */

		Random rand = new Random(); // inicialización variables aleatorias 


		/*
		 * TO-DO: 
		 * ****** Utilización ADECUADA de SOCKETS UDP *****
		 * Generación del mensaje a enviar
		 * Envío del mensaje (utilización adecuada de DatagramPacket)
		 * Esperar respuesta 
		 * Mostrar por pantalla la lista de primos
		 */
	
	}
}




