package p2e06;


import java.util.concurrent.Semaphore;

public class ProcesoA implements Runnable {


	private Semaphore _mutexA;
	private Semaphore _mutexB;
	private Semaphore _barreraB;
	private Semaphore _barreraA;
	private String _param = "A";

	MemoriaCompartida _m;

	public ProcesoA(MemoriaCompartida m) {
		_m = m;
	}


	public void run() {
		_mutexB = _m.GetMutexB();
		_mutexA = _m.GetMutexA();
		_barreraB = _m.GetBarreraB();
		_barreraA = _m.GetBarreraA();
		try {
			_mutexA.acquire();     //Aceder a la variable A 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		_m.SetCountA(_m.GetcountA()+1);   //Cremos un proceso A nuevo
		
		_mutexA.release();

		System.out.println("ProcesoA: "+_m.GetcountA());
		
		try {
			_mutexA.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			_mutexB.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	
		
		if(_m.GetcountB() >= 2  && _m.GetcountA() >=1) {    //Comprobamos si hay 2 procesos B
			_barreraB.release(2);   //Levantamos 2 barreras de procesos B
			_barreraA.release();   //Levantamos 1 barrera de proceso A
			_m.SetCountA(_m.GetcountA()-1);  //Restamos un proceso A
			_m.SetCountB(_m.GetcountB()-2);  //Restamos dos proceso B
			Recurso.assamble(_param);         //Ensamblamos
			_mutexA.release();
			_mutexB.release();
			
		}else {
			_mutexA.release();
			_mutexB.release();
		try {
			_barreraA.acquire();      //Barrera
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		


		}
}

}

