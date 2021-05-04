package p2e03;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class MemoriaCompartida {
	
	
	private LinkedList<Double> _buffer;
	private Semaphore _mutex;
	private Semaphore _dato_nuevo;
	private Semaphore _dato_cogido;
	
	public MemoriaCompartida (  LinkedList<Double> buffer, Semaphore muttex, Semaphore DatoNuevo, Semaphore DatoCogido){
		_buffer = buffer;
		_mutex = muttex;
		_dato_nuevo = DatoNuevo;
		_dato_cogido = DatoCogido;
	}
	
	//SETs
	public  LinkedList<Double > getBuffer(){
		return _buffer;
	}
	
	public Semaphore getMutex() {
		return _mutex;
	}
	
	public Semaphore getDatoNuevo() {
	return _dato_nuevo;
	}
	
	public Semaphore getDatoCogido() {
		return _dato_cogido;
	}

//GETs
	public void SetBuffer (LinkedList <Double>buffer) {
		_buffer = buffer;
	}
	
	public void SetMutex(Semaphore muttex) {
		_mutex =muttex;
	}
	
	public void SetDatoNuevo(Semaphore DatoNuevo) {
		_dato_nuevo = DatoNuevo;
	}
public void SetDatoCogido (Semaphore DatoCogido) {
	_dato_cogido = DatoCogido;
}


}
