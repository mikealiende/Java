package p2e06;


import java.util.concurrent.Semaphore;

public class ProcesoB implements Runnable {

	
	private Semaphore _mutexB;
	private Semaphore _mutexA;
	private Semaphore _barreraA;
	private Semaphore _barreraB;
	private String param = "B";

	MemoriaCompartida _m;
    
	

	public ProcesoB(MemoriaCompartida m) {
		_m = m;

	}
	
	public void run() {
		_mutexB = _m.GetMutexB();
		_mutexA = _m.GetMutexA();
		_barreraA = _m.GetBarreraA();
		_barreraB = _m.GetBarreraB();

			try {
				_mutexB.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			_m.SetCountB(_m.GetcountB()+1);
			
			_mutexB.release();
			
			System.out.println("ProcesoB: "+_m.GetcountB());
			
			
			try {
				_mutexB.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				_mutexA.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			if(_m.GetcountB() >= 2 && _m.GetcountA() >=1 ) {   //Preguntamos si hay 1 B y 1 A al menos
				_barreraB.release(2);
				_barreraA.release();
				_m.SetCountA(_m.GetcountA()-1);
				_m.SetCountB(_m.GetcountB() - 2);
				Recurso.assamble(param);
				_mutexB.release();
				_mutexA.release();
				}
			
			else {
				_mutexB.release();
				_mutexA.release();
				
			
				
				try {
					_barreraB.acquire();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
			}
		}

		
	}


