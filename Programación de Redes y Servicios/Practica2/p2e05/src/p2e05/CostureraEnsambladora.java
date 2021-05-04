package p2e05;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class CostureraEnsambladora implements Runnable{

 private Semaphore _mutex_mangas;
 private Semaphore _mutex_cuerpos;
 private Semaphore _cesta_cuerpos;
 private Semaphore _producir_cuerpos;
 private Semaphore _producir_mangas;
 private Semaphore _cesta_mangas;
 
 private LinkedList <String> _buffer_mangas; 
 private LinkedList <String> _buffer_cuerpos; 
 
 
 public CostureraEnsambladora (MemoriaCompartida m) {
	 
	 	
	 	_mutex_cuerpos = m.Getmutex_cuerpos();
	 	_mutex_mangas = m.Getmutex_mangas();
	 	_cesta_cuerpos = m.GetCestaCuerpos();
	 	_producir_cuerpos = m.GetProducirCuerpos();
	 	_producir_mangas = m.GetProducirMangas();
	 	_cesta_mangas = m.GetCestaMangas();
	 	_buffer_cuerpos = m.GetBufferCuerpos();
	 	_buffer_mangas = m.GetBufferMangas();
 } 
	
	

	public void run() {	
			String manga_1;
			String manga_2;
			String cuerpos;
			while (true) {
			
					try {
						_cesta_cuerpos.acquire();             //Preguntamos si hay al menos 1 cuerpo en la cesta
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				
				try {
					_cesta_mangas.acquire(2);       //Preguntamos si hay al menos 2 magnas en la cesta de mangas
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}  
				
				try {
					_mutex_mangas.acquire();      //Para acceder al buffer de mangas
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				manga_1 = _buffer_mangas.remove();
				manga_2 = _buffer_mangas.remove();       //Cogemos mangas de la cesta
				
				_mutex_mangas.release();
				
			    _producir_mangas.release(2);   //Como hemos cogido dos mangas decimos que tenemos 2 huecos libres en la cesta
				
			    
				try {
					_mutex_cuerpos.acquire();    //Para acceder a la cesta de cuerpos
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				cuerpos = _buffer_cuerpos.remove();		//Cogemos cuerpo de la cesta
				
				_mutex_cuerpos.release();
				
				_producir_cuerpos.release();    //Decimos que tenemos un espacio en la cesta de cuerpos
				
				
		        System.out.println("Creamos: "+manga_2+" "+cuerpos+ " "+manga_1);    //Fabricamos Jersey
		      
		        
		        try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				
			}

		}
	
}
