package p204;

import java.util.concurrent.Semaphore;

public class CodigoTurnstileA implements Runnable {
	
	private Semaphore _turnstile ;
	
	public CodigoTurnstileA ( Semaphore turnstile ){
			_turnstile = turnstile ;
	}
	public void run () {
		try {
			_turnstile . acquire ();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		_turnstile . release ();
		System . out. println (" Proceso ejecutando esto ");
	}
	
	public static void main ( String args []) {
		Semaphore turnstile = new Semaphore (1, true );
		CodigoTurnstileB codigob = new CodigoTurnstileB ( turnstile );
		codigob . start ();  //????? 
		for(int i = 0; i < 50; i ++){
				CodigoTurnstileA codigo = new CodigoTurnstileA ( turnstile );
				Thread miThread = new Thread ( codigo );
				miThread . start ();
			}
		}

}
