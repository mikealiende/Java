package p05e03;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: SharedDataBase
 * TIEMPO: 
 * DESCRIPCION: 
 */
import java.io.Serializable;

public class SharedDataBase implements Serializable{

	
	private int _N;   //Numero de Nodos de la Red
	private int _Our_Sequence_Number;  //El número de secuencia elegido por una solicitud que se origina en este nodo
	private int _HSN= 0;  //El numero de secuencia mas alto visto en una solicitud de mensaje enviado o recibido
	private int _Outstandign_Reply_Count;  //El numero de mensaje de respuesta esperado
	private boolean _Requesting_CriticalSection = false;  //Se pone a TRUE cuando este nodo esta pidiendo acceder a la SC
	private boolean[] _Reply_Deferred = new boolean[_N];  //[j] es TRUE cuando este nodo difiere a una respuesta al mensaje de solicitud de j

	public SharedDataBase(int N,boolean Requesting_CriticalSection, boolean[ ] Reply_Deferred) {
		
		_N = N;
		_Requesting_CriticalSection = Requesting_CriticalSection;
		_Reply_Deferred = Reply_Deferred;
	
	}
	
	//SETs
	
	public void Set_N (int N) {
		_N = N;
	}
	public void Set_Our_Sequence_Number (int Our_Sequence_Number ) {
		_Our_Sequence_Number = Our_Sequence_Number;
	}
	public void Set_HSN (int HSN) {
		_HSN = HSN;
	}
	public void Set_Outstandign_Reply_Count( int Outstandign_Reply_Count) {
		 _Outstandign_Reply_Count =  Outstandign_Reply_Count;
	}
	public void Set_Requesting_CriticalSection (boolean Requesting_CriticalSection) {
		_Requesting_CriticalSection = Requesting_CriticalSection;
	}
	public void Set_Reply_Deferred (int j, boolean bool) {
		_Reply_Deferred[j] = bool;

	}  
	
	//Gets
	public int Get_N () {
		return _N;
	}
	public int Get_Our_Sequence_Number() {
		return _Our_Sequence_Number;
	}
	public int Get_HSN() {
		return _HSN;
	}
	public int Get_Outstandign_Reply_Count() {
		return _Outstandign_Reply_Count;
	}
	public boolean Get_Requesting_CriticalSection() {
		return _Requesting_CriticalSection;
	}
	public boolean [] Get_Reply_Deferred() {
		return _Reply_Deferred;
	}
	
	
	

}