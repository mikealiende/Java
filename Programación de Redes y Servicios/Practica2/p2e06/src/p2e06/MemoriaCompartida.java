package p2e06;


import java.util.concurrent.Semaphore;

public class MemoriaCompartida {

	
    private Semaphore _mutexA;
    private Semaphore _mutexB;
    private Semaphore _BarreraA;
    private Semaphore _BarreraB;
    private int _countA;
    private int _countB;
    
    
    public MemoriaCompartida( Semaphore mutexA, Semaphore mutexB, Semaphore BarreraA, Semaphore BarreraB, int countA, int countB  ) {
    	
    	
    	_mutexA = mutexA;
    	_mutexB = mutexB;
    	_BarreraA  = BarreraA;
    	_BarreraB = BarreraB;
    	_countA = countA;
    	_countB = countB;
    }
    
    
    //GETs
    

    public Semaphore GetMutexA(){
    	return _mutexA;
    }
    public Semaphore GetMutexB(){
    	return _mutexB;
    }
    public Semaphore GetBarreraA() {
    	return _BarreraA;
    }
    public Semaphore GetBarreraB() {
    	return _BarreraB;
    }
    public int GetcountA () {
    	return _countA;
    }
    public int GetcountB () {
    	return _countB;
    }
    
    //SETs
    

    public void SetMutexA(Semaphore mutexA) {
    	_mutexA = mutexA;
    }
    public void SetMutexB(Semaphore mutexB) {
    	_mutexB = mutexB;
    }
    public void SetBarreraA(Semaphore BarreraA) {
    	_BarreraA = BarreraA;
    }
    public void SetBarreraB(Semaphore BarreraB) {
    	_BarreraB = BarreraB;
    }
    public void SetCountA(int countA) {
    	_countA = countA;
    }
    public void SetCountB(int countB) {
    	_countB = countB;
    }
    
    
}
