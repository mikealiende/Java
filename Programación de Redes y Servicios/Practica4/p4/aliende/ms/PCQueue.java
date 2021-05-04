/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package alumnos.p4.aliende.ms;

import java.util.concurrent.Semaphore;
import java.util.ArrayList;

class PCQueue {
    private final int MAX;
    private ArrayList<Object> _buffer = new ArrayList<Object>();
    private Semaphore _mutex;
    private Semaphore _notFull;
    private Semaphore _notEmpty;
    
    public PCQueue(int n){
        MAX = n;
        _mutex = new Semaphore(1, true);
        _notFull = new Semaphore(MAX, true);
        _notEmpty = new Semaphore(0, true);
    }
    
    public void put(Object o) {
        try {_notFull.acquire();}
        catch (InterruptedException e) { }
        try{_mutex.acquire();}
        catch(InterruptedException e){}
        _buffer.add(o);
        _mutex.release();
        _notEmpty.release();
    }
    
    public Object get() {
        boolean unlock=false;
        Object o = null;
        try {
        	_notEmpty.acquire();
        	_mutex.acquire();
    		o = _buffer.remove(0);
    		_mutex.release();
    		_notFull.release();
    		}
		catch(InterruptedException e){unlock = true;}

        if(unlock)    return null;
        return o;
    }
}
