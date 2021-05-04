package p2e05;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class CostureraManga implements Runnable { //Productor 1
	
	private Semaphore _mutexManga; //Seccion critica

    private Semaphore _notFullManga; //Controla si está lleno el buffer
    private Semaphore _notEmptyManga; //Controla si está vacío el buffer
    private LinkedList<String> _bufferManga; //Buffer

    public CostureraManga(MemoriaCompartida m){
        _mutexManga = m.getMutexManga();
        _notEmptyManga = m.getNotEmptyManga();
        _notFullManga = m.getNotFullManga();
        _bufferManga = m.getBufferManga();
    }

    public void run() { 
    	String mangas = "Manga ";
    	while(true) {
    		//PRODUCE
    		try {
				_notFullManga.acquire(); //Semáforo para que no esté llena
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		//SECCIÓN CRÍTICA
    		try {
    			_mutexManga.acquire();
    		} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		_bufferManga.add(mangas);
    		System.out.println("Creo "+mangas); 
    		_mutexManga.release(); //(==SIGNAL)
    		_notEmptyManga.release();
    		
    		try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    }
    
    public static void main (String[] args) {
    	
    	final int K=5;
        LinkedList<String> bufferManga= new LinkedList<String>();
        LinkedList<String> bufferCuerpo= new LinkedList<String>();

        Semaphore mutexManga = new Semaphore(2);
        Semaphore mutexCuerpo = new Semaphore(1);
        
        Semaphore notEmptyManga = new Semaphore(0);
        Semaphore notEmptyCuerpo = new Semaphore(0);
        
        Semaphore notFullManga = new Semaphore(K);
        Semaphore notFullCuerpo = new Semaphore(K);

        MemoriaCompartida m = new MemoriaCompartida(bufferManga, bufferCuerpo, mutexManga, mutexCuerpo, notFullManga,
        		notFullCuerpo, notEmptyManga, notEmptyCuerpo);

        CostureraCuerpo costureraCuerpo = new CostureraCuerpo(m);
        CostureraManga costureraManga = new CostureraManga(m);

        CostureraEnsambladora ensambladora = new CostureraEnsambladora(m);
        Thread procesoProductorMangas = new Thread(costureraManga); //Arrancamos los threads
        Thread procesoProductorCuerpo = new Thread(costureraCuerpo); //Arrancamos los threads
        Thread procesoConsumidorEnsamblador = new Thread(ensambladora);
        
        procesoProductorMangas.start();
        procesoProductorCuerpo.start();
        procesoConsumidorEnsamblador.start();
    }
}
