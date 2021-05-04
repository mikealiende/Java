package p2e03;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Productor implements Runnable {
	
	
	private Semaphore _mutex ;   //Para acceder a la SC
	private Semaphore _dato_nuevo;  //Inidica cuando el productor mete un dato nuevo
	private  Semaphore _dato_cogido;  //Consumidor coge un dato (Hueco libre en buffer)
	
	private LinkedList<Double> _buffer;
	
	public Productor(MemoriaCompartida m) {
		_mutex = m.getMutex();
		_dato_nuevo = m.getDatoNuevo();
		_dato_cogido = m.getDatoCogido();
		_buffer = m.getBuffer();
		
	}
	
	public void run() {
	
			double elemento = 10000000;
			while (elemento > 1.0) {
				try {
					elemento = Math.sqrt(elemento); 
				_dato_cogido.acquire();     //Para saber cuando el consumidor puede coger datos, es decir cunado hay espacio en el buffer
				}	catch( InterruptedException e){
					}
				
			try {
				_mutex.acquire();       //
				     /*Zona critica, aqui modificamos elemento y lo guardamos en el buffer*/
				_buffer.add(elemento);
			}catch( InterruptedException e){
				
			}
				_mutex.release();      //Semaforo a 1
				
				_dato_nuevo.release();  // Decimos que tenemos un dato nuevo, es decir que el consumidor tiene algo que coger
			
			
			}
	
			
	
		
	}
	
	public static void main (String args[]) {
		
		
		Semaphore muttex = new Semaphore (1);
		Semaphore DatoNuevo = new  Semaphore (0);
		Semaphore DatoCogido = new Semaphore (100);
		 LinkedList<Double> buffer = new LinkedList <Double>() ;
		MemoriaCompartida m = new MemoriaCompartida(buffer,muttex,DatoNuevo,DatoCogido);
		
		
		Productor productor = new Productor (m);
		Consumidor consumidor = new Consumidor (m);
		
	  Thread miproductor = new Thread (productor);
	  Thread miconsumidor = new Thread (consumidor);
	  
	  miproductor.start();
	  miconsumidor.start();
		
	}

}
