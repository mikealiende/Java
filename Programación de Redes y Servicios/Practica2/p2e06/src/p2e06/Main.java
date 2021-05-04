package p2e06;

import java.util.concurrent.Semaphore;

public class Main {
	
	public static void main (String [] args) {
		Semaphore mutexA = new Semaphore(1);
		Semaphore mutexB = new Semaphore(1);
		Semaphore barreaA = new Semaphore(0);
		Semaphore barreraB = new Semaphore(0);
		int countA = 0; 
		int countB = 0;	
		
		
		MemoriaCompartida m = new MemoriaCompartida(mutexA, mutexB,barreaA, barreraB, countA, countB);
		
		ProcesoA procesoA = new ProcesoA(m);
		ProcesoB  procesoB = new ProcesoB(m);
		
	
		
		
		
		while(true) {
			
			int valorDado = (int) Math.floor(Math.random()*10+1);
			
			if(valorDado%2 ==0) {
				Thread mi_procesoA = new Thread(procesoA);
				 mi_procesoA.start();
			 }
			else {
				Thread mi_procesoB = new Thread(procesoB);
				mi_procesoB.start();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	}

}
