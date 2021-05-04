package p2e05;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;



public class Main {

		
		public static void main (String args[]) {
			
			Semaphore mutex_mangas = new  Semaphore (1,true);
			Semaphore mutex_cuerpos = new  Semaphore (1,true);
			Semaphore cesta_mangas = new Semaphore(0,true);
			Semaphore cesta_cuerpos = new Semaphore (0,true);
			Semaphore producir_mangas = new Semaphore (20,true);       
			Semaphore producir_cuerpos = new Semaphore(10,true);
			
			
			LinkedList<String > _buffer_mangas = new LinkedList<String>();
			LinkedList <String> _buffer_cuerpos = new LinkedList<String>();
			MemoriaCompartida m = new MemoriaCompartida(_buffer_mangas, _buffer_cuerpos, mutex_mangas, mutex_cuerpos, 
																										 cesta_mangas, cesta_cuerpos, producir_mangas, producir_cuerpos);
			
			CostureraManga costurera_manga= new CostureraManga(m); 
			CostureraCuerpo costurera_cuerpo= new CostureraCuerpo(m); 
			CostureraEnsambladora costurera_ensambladora = new CostureraEnsambladora(m);
			
			
			Thread mi_costurera_mangas = new Thread(costurera_manga);
			Thread mi_costurera_cuerpos = new Thread (costurera_cuerpo);
			Thread mi_costurera_ensambladora = new Thread (costurera_ensambladora);
			
			mi_costurera_mangas.start();
			mi_costurera_cuerpos.start();
			mi_costurera_ensambladora.start();
			
			
			
			
			
			

			
		}
}
