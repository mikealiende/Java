package Costureras;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import ms.Envelope;
import ms.MessageSystem;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: CostureraEnsambladora
 * TIEMPO: 
 * DESCRIPCION: Coje 2 mangas y un cuerpos de sus respectivos buffer y los junta
 */

public class CostureraEnsambladora implements Runnable {
	
	final int mutexCuerpo = 0;
	final int NotEmpyCuerpo = 1;
	final int NotFullCuerpo = 2;
	final int mutexMangas = 3;
	final int NotEmpyMangas =4;
	final int NotFullMangas = 5;
	
	
	private MessageSystem _ms;
	private String comando;
	private LinkedList<String> _cesta_cuerpos;
	private LinkedList<String> _cesta_mangas;
	private int _me;
	private static String _usersFile;
	
	public CostureraEnsambladora(int me, MemoriaCompartida m) {
		_me = me;
		_usersFile = m.GetUsersFile();
		_cesta_cuerpos = m.GetCestaCuerpos();
		_cesta_mangas = m.GetCestaMangas();
		
	}
	
	
	public void run() {

		String manga1;
		String manga2;
		String cuerpo;
		String ensamblado;
		
		Envelope sobre;
		try {
			_ms = new MessageSystem(_me,_usersFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			
			
			_ms.send(NotEmpyCuerpo, "WAIT");
			sobre = _ms.receive();
			comando = (String)sobre.getPayload();
			
			
			if(comando.equals("NOT_EMPY")) {  //Hay algo en el la cesta de cuerpos
			
			
				_ms.send(mutexCuerpo, "WAIT");
				
				sobre = _ms.receive();
				comando = (String)sobre.getPayload();
				
				if(comando.equals("OK")) {  //Accedemos a la SC
					
					cuerpo = _cesta_cuerpos.remove();   //Cogemos cuerpo de la cesta
					
					_ms.send(NotFullCuerpo,"SIGNAL");   //Decimos que hay un hueco en la cesta
					_ms.send(mutexCuerpo, "SIGNAL");    //Salimos de la SC
				
		
					_ms.send(NotEmpyMangas, "WAIT");    
					sobre = _ms.receive();
					comando = (String)sobre.getPayload();
				
					if (comando.equals("NOT_EMPY")) {   //Hay al menos 1 en la lista de mangas
						_ms.send(NotEmpyMangas, "WAIT");
						sobre = _ms.receive();
						comando = (String)sobre.getPayload();
						
						if (comando.equals("NOT_EMPY")) {   //Hay dos mangas al menos
						
							_ms.send(mutexMangas, "WAIT");
							sobre = _ms.receive();
							comando = (String)sobre.getPayload();
							if(comando.equals("OK")) {        //Accedemos a la seccion critica
								manga1 = _cesta_mangas.remove();
								manga2 = _cesta_mangas.remove();
								
								_ms.send(NotFullMangas, "SIGNAL");
								_ms.send(NotFullMangas, "SIGNAL"); //Decimos que hay dos hueco en la cesta de mangas
							
								_ms.send(mutexMangas,"SIGNAL");   //Salimos de la seccion critica
							
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
								ensamblado = ("\n"+manga1+" "+cuerpo+" "+manga2+"\n");
								System.out.println( ensamblado);
							}
						}
					
					}
				}
			}
		}
	}
	
}
