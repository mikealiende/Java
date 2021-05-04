package Costureras;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: CostureraCuerpo
 * TIEMPO: 
 * DESCRIPCION: Se encarga de generar cuerpos y guardarlos en un buffer cunado pueda
 */
import java.io.FileNotFoundException;
import java.util.LinkedList;

import ms.Envelope;
import ms.MessageSystem;

public class CostureraCuerpo implements Runnable {
	
	private static MessageSystem _ms;
	private int _me;
	private static String _usersFile;
	private LinkedList <String>  _cesta_cuerpos;
	
	final int mutexCuerpo = 0;
	final int NotEmpyCuerpo =1;
	final int NotFullCuerpo = 2;
	
	String cuerpo  = "cuerpo";
	String comando;
	
	public CostureraCuerpo(int me, MemoriaCompartida m) {
		_me = me;
		_usersFile = m.GetUsersFile();
		_cesta_cuerpos = m.GetCestaCuerpos();
		}
	
	
	public void run() {
		
		try {
			_ms = new MessageSystem(_me,_usersFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Envelope sobre;
			
			while(true) {
				
				_ms.send(NotFullCuerpo, "WAIT");
				sobre = _ms.receive();
				comando = (String)sobre.getPayload();
				if(comando.equals("NOTFULL")) {
						
				
					_ms.send(mutexCuerpo, "WAIT");  //Mandamos el wait a mutexCuerpo
					sobre = _ms.receive(); 
					comando = (String)sobre.getPayload();
				
					if(comando.equals("OK")) {   //Entramso en SC
						_cesta_cuerpos.add(cuerpo);

						System.out.println("Cuerpo creado");
					
						_ms.send(mutexCuerpo, "SIGNAL");    //Salimos de la SC
					
						try {
							Thread.sleep(1000);    //Pasamos un tiempo en la seccion critica
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
					
						_ms.send(NotEmpyCuerpo, "SIGNAL");     //Decimos que la cesta no esta vacia
					}
			
				}
			
				
				
		 	}
		
		}
		
		
		
	}
	

