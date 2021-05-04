package Costureras;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import ms.Envelope;
import ms.MessageSystem;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Costurera Manga
 * TIEMPO: 
 * DESCRIPCION:  Se encarga de generar mangas y guardarlos en un buffer cunado pueda
 */
public class CostureraManga implements Runnable {

	private static MessageSystem _ms;
	private int _me;
	private static String _usersFile;
	private LinkedList <String>  _cesta_mangas;
	
	final int mutexMangas = 3;
	final int NotEmpyMangas =4;
	final int NotFullMangas = 5;
	
	String mangas  = "manga";
	String comando;
	
	public CostureraManga(int me, MemoriaCompartida m) {
		_me = me;
		_usersFile = m.GetUsersFile();
		_cesta_mangas = m.GetCestaMangas();
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
				
				_ms.send(NotFullMangas, "WAIT");
				sobre = _ms.receive();
				comando = (String)sobre.getPayload();
				if(comando.equals("NOTFULL")) { //Hay hueco en la cesta de mangas
				
					_ms.send(mutexMangas, "WAIT");  //Mandamos el wait a mutexCuerpo	
					sobre = _ms.receive(); 
					comando = (String)sobre.getPayload();
				
					if(comando.equals("OK")) {   //Entramso en SC
						_cesta_mangas.add(mangas);
						System.out.println("Manga creada");
					
					
						try {
							Thread.sleep(600);    //Pasamos un tiempo en la seccion critica
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
						_ms.send(mutexMangas, "SIGNAL");    //Salimos de la SC

					
						_ms.send(NotEmpyMangas, "SIGNAL");     //Decimos que la cesta no esta vacia
					}
				}
			}		
		}
	}

