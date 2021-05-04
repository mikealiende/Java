package Master_Woker;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Costurera Manga
 * TIEMPO: 
 * DESCRIPCION: La clase master estara escuchando peticiones y cunado le llege una peticion llamara a un proceso worker para que gestione la peticion y luego el master
 * 				se la envia a el cliente
 */
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import ms.Envelope;
import ms.MessageSystem;

public class Master implements Runnable{
	int _numeroDeTareas;
	static String _usersFile = "peers.txt";
	MessageSystem _ms;
	int _source;
	Envelope _sobre;
	public Master(MessageSystem ms, String userFile,Envelope sobre, int numeroDeTareas) {
		_usersFile = userFile;
		_ms = ms;
		_sobre = sobre;
		_numeroDeTareas = numeroDeTareas;
	
	}
	
	
	
	public static void main(String args []) throws FileNotFoundException {
		int numeroDeTareas = 0;
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Selecione escenario deseado\n"
	    					+ "1.Escenario 1\n"
	    					+ "2.Escenario 2\n"
	    					+ "3.Escenario 3\n"
	    					+ "4.Escenario 4\n");
	    
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
		
		int j = 0;
		MessageSystem master = new MessageSystem(25, _usersFile);
		MessageSystem vector_ms[] = new MessageSystem [numeroDeTareas];
		Envelope sobre;
		int id = 24;
		for(int i = 0; i < numeroDeTareas ;i++) {
			vector_ms[i] = new MessageSystem(id, _usersFile);
			id--;
		}
		while(true) {
		
		sobre = master.receive();
		Master master_thread = new Master(vector_ms[j], _usersFile, sobre,numeroDeTareas);
		Thread mi_masterThread = new Thread(master_thread);
		mi_masterThread.start();
		j++;
		if(j ==numeroDeTareas) j= 0;
		
		}
		
	}
	
	
	public void run() {
		
		
		
		
		//Interval intervalo = (Interval)_sobre.getPayload();
		int cliente = _sobre.getSource();
		int worker = _ms.getMe()-_numeroDeTareas;
		System.out.println("Worker: "+worker);
		_ms.send(worker, _sobre.getPayload());
		_sobre = _ms.receive();
		System.out.println("Algo nos ha llegado");
		List <Long> perfectos = (List<Long>) _sobre.getPayload();
		
		_ms.send(cliente, (Serializable) perfectos);
		
		 
		
	
		
		
		
		
		
		
		
	}
	
	

}
