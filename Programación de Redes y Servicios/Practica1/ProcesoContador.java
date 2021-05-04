package practica1;

public class ProcesoContador implements Runnable {
	MemoriaCompartida _n;
	// el guion bajo es simplemente una notacion
	public ProcesoContador ( MemoriaCompartida n){
		_n = n;
	}
	
	public void run () {
		int temp ;
		for (int i = 0; i < 10; i ++){
				temp = _n. get ();
				Thread.yield(); //llama al scheduller que se encargar de gestionar tiempo de los hilos
				_n.set ( temp + 1); // y le da paso a otro hilo. El shceduller es libre de ignorar esta accion	
		}
	}
	
	
	public static void main ( String [] args ) {
		MemoriaCompartida n = new MemoriaCompartida (0);
		ProcesoContador count1 = new ProcesoContador (n);
		ProcesoContador count2 = new ProcesoContador (n);
		Thread p = new Thread ( count1 );
		Thread q = new Thread ( count2 );
		p. start ();
		q. start ();
	   
	
		try{
			p. join ();
			q. join ();
		}
		catch ( InterruptedException e){
			//no hacemos nada en caso de excepcon
		}
		System . out. println (" The value of _i is " + n.get ());
	}

}
