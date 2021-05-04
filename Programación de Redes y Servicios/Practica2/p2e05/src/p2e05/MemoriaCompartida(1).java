package p2e05;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class MemoriaCompartida {
	private Semaphore _notFullManga;
	private Semaphore _notFullCuerpo;
	private Semaphore _mutexManga;
	private Semaphore _mutexCuerpo;
	private Semaphore _notEmptyManga;
	private Semaphore _notEmptyCuerpo;
	private LinkedList<String> _bufferManga;
	private LinkedList<String> _bufferCuerpo;
	
	public MemoriaCompartida(LinkedList<String> bufferManga, LinkedList<String> bufferCuerpo,
			Semaphore mutexManga, Semaphore mutexCuerpo, Semaphore notFullManga, 
			Semaphore notFullCuerpo, Semaphore notEmptyManga, Semaphore notEmptyCuerpo) {
    	_mutexManga = mutexManga;
    	_mutexCuerpo = mutexCuerpo;
    	_notFullManga = notFullManga;
    	_notFullCuerpo = notFullCuerpo;
    	_notEmptyManga = notEmptyManga;
    	_notEmptyCuerpo = notEmptyCuerpo;
    	_bufferManga = bufferManga;
    	_bufferCuerpo = bufferCuerpo;
    }
	
	public Semaphore getMutexManga() {
		return _mutexManga;
	}
	public Semaphore getMutexCuerpo() {
		return _mutexCuerpo;
	}
	public void setMutexManga(Semaphore mutexManga) {
		_mutexManga = mutexManga;
	}
	public void setMutexCuerpo(Semaphore mutexCuerpo) {
		_mutexCuerpo = mutexCuerpo;
	}
	
	public Semaphore getNotFullManga() {
		return _notFullManga;
	}
	public void setNotFullManga(Semaphore notFullManga) {
		_notFullManga = notFullManga;
	}
	
	
	public Semaphore getNotEmptyCuerpo() {
		return _notEmptyCuerpo;
	}
	
	public void setNotEmptyCuerpo(Semaphore notEmptyCuerpo) {
		_notEmptyCuerpo = notEmptyCuerpo;
	}
	
	public Semaphore getNotEmptyManga() {
		return _notEmptyManga;
	}
	public void setNotEmptyManga(Semaphore notEmptyManga) {
		_notEmptyManga = notEmptyManga;
	}
	
	
	public Semaphore getNotFullCuerpo() {
		return _notFullCuerpo;
	}
	
	public void setNotFullCuerpo(Semaphore notFullCuerpo) {
		_notFullCuerpo = notFullCuerpo;
	}
	
	
	public LinkedList<String> getBufferManga(){
		return _bufferManga;
	}
	public void setBufferManga(LinkedList<String> bufferManga) {
		_bufferManga = bufferManga;
	}
	
	public LinkedList<String> getBufferCuerpo(){
		return _bufferCuerpo;
	}
	public void setBufferCuerpo(LinkedList<String> bufferCuerpo) {
		_bufferCuerpo = bufferCuerpo;
	}
}
