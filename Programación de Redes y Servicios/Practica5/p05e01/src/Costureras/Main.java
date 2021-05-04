
package Costureras;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Main
 * TIEMPO: 
 * DESCRIPCION:  Clase principal que se encarga de lanzar todos los hilos
 */
import java.util.LinkedList;

import Server.Mutex;

import Server.NotEmpy;
import Server.NotFull;


public class Main {
	
	private static final String usersFile = "peers.txt";
	private final static int mutex_cuerpos_id = 0;
	private final static  int not_empy_cuerpos_id = 1;
	private final static int not_full_cuerpos_id = 2;
	private final static int mutex_mangas_id = 3;
	private final static int not_empy_mangas_id = 4;
	private final static int not_full_mangas_id = 5;
	
	public static void main (String args[]) {

		LinkedList<String> cesta_mangas = new LinkedList<String>();
		LinkedList<String> cesta_cuerpos = new LinkedList<String>();
		
		MemoriaCompartida m = new MemoriaCompartida(cesta_cuerpos, cesta_mangas, usersFile);
		
		Mutex mutex_cuerpos = new Mutex(mutex_cuerpos_id, usersFile);
		NotEmpy not_empy_cuerpos = new NotEmpy(not_empy_cuerpos_id, usersFile);
		NotFull not_full_cuerpos = new NotFull(not_full_cuerpos_id, usersFile, 5);
		Mutex mutex_mangas = new Mutex (mutex_mangas_id, usersFile);
		NotEmpy not_empy_mangas = new NotEmpy(not_empy_mangas_id, usersFile);
		NotFull not_full_mangas = new NotFull(not_full_mangas_id, usersFile, 8);
		
		CostureraCuerpo costurera_cuerpo = new CostureraCuerpo(6,m);
		CostureraEnsambladora costurera_ensambladora = new CostureraEnsambladora(7,m);
		CostureraManga costurera_mangas = new CostureraManga(8,m);
		
		Thread mi_mutex_cuerpos = new Thread(mutex_cuerpos);
		Thread mi_not_empy_cuerpo = new Thread(not_empy_cuerpos);
		Thread mi_not_full_cuerpos = new Thread(not_full_cuerpos);
		Thread mi_mutex_mangas = new Thread(mutex_mangas);
		Thread mi_not_empy_mangas = new Thread(not_empy_mangas);
		Thread mi_not_full_mangas = new Thread(not_full_mangas);
		Thread mi_costurera_cuerpos = new Thread(costurera_cuerpo);
		Thread mi_costurera_mangas = new Thread (costurera_mangas);
		Thread mi_costurersa_Ensambladora = new Thread(costurera_ensambladora);
		
		
		System.out.println("emepzamos");
		mi_mutex_cuerpos.start();
	    mi_not_empy_cuerpo.start();
	    mi_not_full_cuerpos.start();
	    mi_mutex_mangas.start();
	    mi_not_empy_mangas.start();
	    mi_not_full_mangas.start();
	    
	    try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    mi_costurera_cuerpos.start();
	    mi_costurera_mangas.start();
	    mi_costurersa_Ensambladora.start();
		
		
	}

}
