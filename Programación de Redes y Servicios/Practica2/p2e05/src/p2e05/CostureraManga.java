package p2e05;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class CostureraManga  implements Runnable{

	
	private  Semaphore _mutex_mangas;
	private Semaphore _cesta_mangas;
	private Semaphore _producir_mangas;
	 
	private LinkedList <String> _buffer_mangas; 
	 public  CostureraManga (MemoriaCompartida m) {
	
		 _mutex_mangas =m.Getmutex_mangas();
		 _cesta_mangas = 	m.GetCestaMangas();
		 _producir_mangas = m.GetProducirMangas();
		 _buffer_mangas = m.GetBufferMangas();
		 
	 }
	
	public void run() {
		
		String manga = "Manga";
		

			while (true) {
				try {
					_producir_mangas.acquire();     //Para saber si la cesta esta llena
				} catch (InterruptedException e) {
		
						e.printStackTrace();
					} 
				
				try {
					_mutex_mangas.acquire();   						//Para acceder a la Seccion critica del buffer de mangas
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				
				_buffer_mangas.add(manga);   //Ponemos manga en la cesta
				_mutex_mangas.release();
				
				_cesta_mangas.release();  //Decimos que hay algo en la cesta de mangas
				
				System.out.println(manga);
				
				
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				}
		}
}


