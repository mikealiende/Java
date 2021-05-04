package p3.tcp;


import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	static public enum Type {REQUEST, RESPONSE};	
	private Type _typeMsg;	// tipo de mensaje. De momento solo 2: petici???n y respuesta
	

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

	
	/*
	 * TO-DO
	 * Métodos Set()
	 * Métodos Get()
	 */
	
	public void setTypeMsg(Type typeMsg) {
		_typeMsg = typeMsg;
	}
	public Type getTypeMsg() {
		return _typeMsg;
	}
   	
 }
