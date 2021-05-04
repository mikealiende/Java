package p204;

import java.util.concurrent.Semaphore;

public class CodigoTurnstileB implements Runnable {

	private Semaphore _turnstile ;
	public CodigoTurnstileB ( Semaphore turnstile ){
	_turnstile = turnstile ;
	}
	public void run () {

	try {
		Thread . sleep (100);
		_turnstile . acquire ();
		Thread . sleep (10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	_turnstile . release ();
	}
}
