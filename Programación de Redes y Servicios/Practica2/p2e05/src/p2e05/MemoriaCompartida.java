package p2e05;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;
public class MemoriaCompartida {
	
   private LinkedList<String > _buffer_mangas;
   private LinkedList <String> _buffer_cuerpos;
   private Semaphore _mutex_mangas;
   private Semaphore _mutex_cuerpos;
   private Semaphore _cesta_cuerpos;
   private Semaphore _producir_cuerpos;
   private Semaphore _producir_mangas;
   private Semaphore _cesta_mangas;
	
	
	
	public MemoriaCompartida (LinkedList<String > buffer_mangas,LinkedList<String > buffer_cuerpos,Semaphore mutex_mangas, Semaphore mutex_cuerpos, 
													  Semaphore cesta_mangas, Semaphore cesta_cuerpos, Semaphore producir_mangas, Semaphore producir_cuerpos) {
		
		_buffer_mangas = buffer_mangas;
		_buffer_cuerpos = buffer_cuerpos;
		_mutex_mangas = mutex_mangas;
		_mutex_cuerpos = mutex_cuerpos;
		_cesta_mangas = cesta_mangas;
		_cesta_cuerpos = cesta_cuerpos;
		_producir_mangas = producir_mangas;
		_producir_cuerpos = producir_cuerpos;
		
		
		
		
	}
	
	//GETs
	public LinkedList<String> GetBufferMangas(){
		return _buffer_mangas;
	}
	public LinkedList<String> GetBufferCuerpos(){
		return _buffer_cuerpos;
	}
	public Semaphore Getmutex_mangas() {
		return  _mutex_mangas;
	}
	public Semaphore Getmutex_cuerpos() {
		return  _mutex_cuerpos;
	}
	public Semaphore GetCestaCuerpos() {
		return _cesta_cuerpos;
		}
	public Semaphore GetProducirCuerpos() {
		return _producir_cuerpos;
	}
	public Semaphore GetCestaMangas() {
		return _cesta_mangas;
	}
	public Semaphore GetProducirMangas() {
		return _producir_mangas;
	}
	
	
	//SETs
	
	public void SetBufferMangas(LinkedList<String> buffer_mangas) {
		_buffer_mangas = buffer_mangas;
	}
   public void SetBufferCuerpos(LinkedList<String> buffer_cuerpos) {
	   _buffer_cuerpos = buffer_cuerpos;
   }
   public void Setmutex_mangas(Semaphore mutex_mangas) {
	   _mutex_mangas = mutex_mangas;
   }
   public void Setmutex_cuerpos(Semaphore mutex_cuerpos) {
	   _mutex_cuerpos = mutex_cuerpos;
   }
   public void SetCestaCuerpos( Semaphore cesta_cuerpos) {
	   _cesta_cuerpos = cesta_cuerpos;
   }
   public void SetProducirCuerpos (Semaphore producir_cuerpos) {
	   _producir_cuerpos = producir_cuerpos;
   }
   public void SetCestaMangas (Semaphore cesta_mangas) {
	   _cesta_mangas = cesta_mangas;
   }
   public void SetProducirMangas (Semaphore producir_mangas) {
	   _producir_mangas  = producir_mangas;
   }
}


	
