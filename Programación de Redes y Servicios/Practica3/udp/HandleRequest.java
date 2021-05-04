package p3.udp;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import p3.udp.Message;
import p3.udp.Primes;
import p3.udp.TransferMessages;


public class HandleRequest implements Runnable {

	public static final int MAXPRIMES = 8192;

	/*
	 * TO-DO: miembros de la clase
	 */
	private DatagramSocket _sock;
	
	public HandleRequest(DatagramSocket sock, /*TO-DO: otros argumentos */) {
		_sock = sock;
	}

  public void run() {
		/*
		 * TO-DO
		 */
	  try {
    	} catch (Exception e) {
   		e.printStackTrace();
    	}
    }
	
}
