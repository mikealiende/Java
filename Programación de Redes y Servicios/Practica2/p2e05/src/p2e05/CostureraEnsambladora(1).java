package p2e05;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class CostureraEnsambladora implements Runnable{
	
	private Semaphore _mutexManga; //Seccion critica
	private Semaphore _mutexCuerpo; //Seccion critica

    private Semaphore _notFullManga; //Controla si está lleno el buffer
    private Semaphore _notEmptyManga; //Controla si está vacío el buffer
    private LinkedList<String> _bufferManga; //Buffer
    private Semaphore _notFullCuerpo; //Controla si está lleno el buffer
    private Semaphore _notEmptyCuerpo; //Controla si está vacío el buffer
    private LinkedList<String> _bufferCuerpo; //Buffer
    
    public CostureraEnsambladora(MemoriaCompartida m){
        _mutexManga = m.getMutexManga();
        _mutexCuerpo = m.getMutexCuerpo();
        _notEmptyManga = m.getNotEmptyManga();
        _notEmptyCuerpo = m.getNotEmptyCuerpo();
        _notFullManga = m.getNotFullManga();
        _notFullCuerpo = m.getNotFullCuerpo();
        _bufferManga = m.getBufferManga();
        _bufferCuerpo = m.getBufferCuerpo();
    }
    
    public void run() {
    	//La ensambladora va a coger dos mangas y un cuerpo si el buffer de cada uno de ellos
    	//no está vacío ó no tiene mínimo esas piezas
    	
    	String manga1, manga2, cuerpo;
    	while(true) {
    		
    		try {
				_notEmptyManga.acquire(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		try {
				_notEmptyCuerpo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		//Sección crítica
    		
    		try {
				_mutexManga.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		//Ensambladora coge 2 mangas 
    		manga1 = _bufferManga.remove();
    		manga2 = _bufferManga.remove();
    		System.out.println("Ensambladora: "+ manga1 + manga2);
    		
    		_mutexManga.release(); 
    		_notFullManga.release(2); //Creamos los dos huecos en el buffer de mangas: indicamos que hay 2 huecos libres
    		
    		//Ensambladora coge 1 cuerpo
    		try {
				_mutexCuerpo.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		cuerpo = _bufferCuerpo.remove();
    		System.out.println("Ensambladora: "+ cuerpo);
    		
    		_mutexCuerpo.release();
    		_notFullCuerpo.release();  		
    		
    		try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    }
}
