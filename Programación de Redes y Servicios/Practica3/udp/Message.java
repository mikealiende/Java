package p3.udp;


import java.io.Serializable;
import java.net.InetSocketAddress;


public class Message implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	static public enum Type {REQUEST, RESPONSE};	
	private Type _typeMsg;	// tipo de mensaje. De momento solo 2: petici???n y respuesta
	
	//**************************************************************************
	// Campo _address, no forma parte del protocolo, uso local. PARA UDP	
		transient InetSocketAddress _address;  
		public void setAddress(InetSocketAddress add) {_address = add;}
		public InetSocketAddress getAddress() {return _address;}
	//**************************************************************************
		

	/*
	 * TO-DO: incluir miembros de la clase (campos del mensaje)
	 * 
	 */
	// 
  
	public Message(Type typeMsg, /*TO-DO. argumentos del constructor*/) throws IllegalArgumentException {
	    _typeMsg = typeMsg;
	    
	    /*
	     * TO-DO
	     */
	}


	public void setTypeMsg(Type typeMsg) {
		_typeMsg = typeMsg;
	}
	public Type getTypeMsg() {
		return _typeMsg;
	}

	
	
	/*
	 * TO-DO
	 * Métdoso Set()
	 * Métodos Get()
	 */

	
	   	
 }
