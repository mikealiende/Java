package p2e03;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Consumidor  implements Runnable {

	
	
	private LinkedList<Double > _buffer;
	private Semaphore _mutex;
	private Semaphore _dato_nuevo;
	private Semaphore _dato_cogido;
	public Consumidor(MemoriaCompartida m ) {
		_buffer = m.getBuffer();
		_mutex = m.getMutex();
		_dato_nuevo = m.getDatoNuevo();
		_dato_cogido = m.getDatoCogido();
		
	}
	
	public void run() {
	
		double elemento = 0;
			while (true) {
				try {
				_dato_nuevo.acquire(); 		//Hasta que no haya al menos un dato en la tabla estamos queitos
				}catch( InterruptedException e) {
					
				}
				
				try {
				_mutex.acquire();   		//Inicio del semaforo para entrar en SC
				 elemento  = _buffer.remove();     		//Seccion critica, guarda el elemento que hay en buffer y lo elimina
				}catch( InterruptedException e) {
					
				}
				
				_mutex.release();  			//Fin de semaforo
				
				_dato_cogido.release(); 	//indicamos que hemos generado un hueco en la tabla 
				System.out.println("elemento = "+elemento);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			
			}
	
	}
	
	
}
