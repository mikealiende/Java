package p2e05;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class CostureraCuerpo implements Runnable{ //Productor 2
	private Semaphore _mutexCuerpo; //Seccion critica

    private Semaphore _notFullCuerpo; //Controla si est� lleno el buffer
    private Semaphore _notEmptyCuerpo; //Controla si est� vac�o el buffer
    private LinkedList<String> _bufferCuerpo; //Buffer

    public CostureraCuerpo(MemoriaCompartida m){
        _mutexCuerpo = m.getMutexCuerpo();
        _notEmptyCuerpo = m.getNotEmptyCuerpo();
        _notFullCuerpo = m.getNotFullCuerpo();
        _bufferCuerpo = m.getBufferCuerpo();
    }

    public void run() { 
    	String cuerpo = "Cuerpo ";
    	
    	while(true) {
    		//PRODUCE
    		try {
    			_notFullCuerpo.acquire(); //Si no est� lleno, seguimos
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		//Secci�n cr�tica
    		
    		try {
    			_mutexCuerpo.acquire();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		
    		_bufferCuerpo.add(cuerpo); //Escribimos en el buffer
    		System.out.println("Creo "+cuerpo); 

    		//Signal para desbloquear los procesos
    		_mutexCuerpo.release();
    		_notEmptyCuerpo.release();
    		try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
}
