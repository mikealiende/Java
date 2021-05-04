package p2e05;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class CostureraCuerpo implements Runnable{
	
	private Semaphore _mutex_cuerpos;
	private Semaphore _cesta_cuerpos;
	private Semaphore _producir_cuerpos;
	private LinkedList <String> _buffer_cuerpos;
	

	
	public CostureraCuerpo (MemoriaCompartida m) {
		_mutex_cuerpos =m.Getmutex_cuerpos();
		_cesta_cuerpos = m.GetCestaCuerpos();
		_producir_cuerpos = m.GetProducirCuerpos();   
		_buffer_cuerpos = m.GetBufferCuerpos();
		
	}
	
	public void run() {
		
		String cuerpo;
		cuerpo = "Cuerpo";
	
		while (true) {
			try {
				_producir_cuerpos.acquire();   //Preguntamos si el hay espacio en el buffer.
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}   
			
			try {
				_mutex_cuerpos.acquire();				//Semaforo para entrar en la SC de el buffer de cuerpos
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			_buffer_cuerpos.add(cuerpo);    //Ponemos cuerpo en cesta.
			_mutex_cuerpos.release();
			
			_cesta_cuerpos.release();   //Decimos que hay algo en la cesta de cuerpos
			System.out.println(cuerpo);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
			}
							
		}
	
	}




