package Client;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: ClientePerfectos
 * TIEMPO: 
 * DESCRIPCION:  Esta clase se encarga de crear hilos para que hagan las peticiones al servidor de manera concurrente, tiene un menu desde el que podremos 
 * 				 configurar tanto escenario como si queremos que la peticion la procese un servidor concurrente o un Master-Worker
 */
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ms.Envelope;
import ms.MessageSystem;

public class ClientePerfectos {

		
	private static  String _usersFile = "peers.txt";
	static Envelope sobre;
	
	public static void main(String args[]) throws FileNotFoundException{

		
		int numeroDeTareas = 0;
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Selecione escenario deseado\n"
	    					+ "1.Escenario 1\n"
	    					+ "2.Escenario 2\n"
	    					+ "3.Escenario 3\n"
	    					+ "4.Escenario 4 \n");
	    
		int escenario = Integer.parseInt(myObj.nextLine());
		switch(escenario){
		
		case 1: 
			System.out.println("He escogido el escenario 1");
			numeroDeTareas = 1;
			break;
			
		case 2:
			System.out.println("He escogido el escenario 2");
			numeroDeTareas = 2;
			break;
		case 3: 
			System.out.println("He escogido el escenario 3");
			numeroDeTareas = 4;
			break;
		case 4:
			System.out.println("He escogido el escenario 4");
			numeroDeTareas = 8;
			break;

		}
		
		
		int servidor= 0;
		Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Selecione forma de calculo deseada\n"
	    					+ "1.Servidor concurrente\n"
	    					+ "2.Master-Worker\n");
	    
	    int server_selec = Integer.parseInt(myObj.nextLine());
	    switch(server_selec){
		
		case 1: 
			System.out.println("He escogido el Servidor Concurrente");
			servidor = 0;
			break;
			
		case 2:
			System.out.println("He escogido el Master-Worker");
			servidor = 25;
			break;
	    }
	    
		long delay = 5000; 
		
		int ini = Integer.parseInt(args[0]);
		int fin = Integer.parseInt(args[1]); 
		MessageSystem [] vector_ms = new MessageSystem [numeroDeTareas];	
		boolean firstime = true;
		MessageSystem _ms = null;
		
		while(true){
			System.out.println(numeroDeTareas);
			long t_ini = System.currentTimeMillis();
		
			if(firstime) {	
				for (int i = 0; i < numeroDeTareas; i++) {
					vector_ms[i] = new MessageSystem(i+1,_usersFile);
				}
			}
				
			for(int i = 0; i < numeroDeTareas; i++) {
			
				 
				try {
				
						Clientes esc = new Clientes(vector_ms[i],ini,fin,servidor);
						Thread mi_esc = new Thread(esc);
						mi_esc.start();
						Thread.sleep(100);
						mi_esc.join();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			}

			
			ini = ini+5;
			fin = fin+10;
			firstime = false;
			
		
			long t_end = System.currentTimeMillis();
			System.out.println("Tiempo de ejecucion de la(s) tarea(s): " + (t_end - t_ini));
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
}