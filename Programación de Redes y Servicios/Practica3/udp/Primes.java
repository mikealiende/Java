/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package p3.udp;

import java.util.ArrayList;

public abstract class Primes {

	public static int[] GetPrimes(int ini, int fin){
		    int numPrimes = 0;
		    ArrayList <Integer>primesList = new ArrayList<Integer>();
		    
		  
		    // Calculamos el conjunto de primos en el intervalo
		    for (int i = ini; i <= fin; i++) {
		    	boolean esPrimo = true;
		    	int raiz = (int) Math.sqrt((double) i);
				int j = 2;
				while ((j <= raiz) && esPrimo){
					esPrimo = (i % j) != 0;
					j++;
				}
				if (esPrimo){
					primesList.add(numPrimes,i);
					numPrimes++;
				}	
		    } 

		    int [] primes = null;
		    if(numPrimes>0) {
		    	primes= new int[numPrimes];
			    for(int i=0;i<numPrimes;i++)
			    	primes[i] = primesList.get(i);
		    }
		    return primes;
	  }

}
