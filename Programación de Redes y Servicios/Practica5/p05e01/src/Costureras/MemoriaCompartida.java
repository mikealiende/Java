package Costureras;

import java.util.LinkedList;

public class MemoriaCompartida {
	
	
	private LinkedList <String> _cesta_cuerpos;
	private LinkedList <String> _cesta_mangas;
	String _usersFile;
	
	public MemoriaCompartida(LinkedList <String> cesta_cuerpos, LinkedList <String> cesta_mangas, String usersFile) {
		_cesta_cuerpos = cesta_cuerpos;
		_cesta_mangas = cesta_mangas;
		_usersFile = usersFile;
	}
	
	//GETs
	
	public LinkedList <String> GetCestaCuerpos(){
		return _cesta_cuerpos;
	}
	public LinkedList <String> GetCestaMangas(){
		return _cesta_mangas;
	}	
	public String GetUsersFile() {
		return _usersFile;
	}
	
	//SETs
	public void SetCestaCuerpos(LinkedList<String> cesta_cuerpos){
		_cesta_cuerpos = cesta_cuerpos;
	}
	public void SetCestaMangas (LinkedList<String> cesta_mangas){
		_cesta_mangas = cesta_mangas;
	}
	public void SetUsersFile( String usersFile) {
		_usersFile = usersFile;
	}
	

}
