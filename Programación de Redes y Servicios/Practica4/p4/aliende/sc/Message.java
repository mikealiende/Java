/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package alumnos.p4.aliende.sc;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 2L;
	
	private int _c;
	private String _t;
	Message(int c, String t){
		_c=c;
		_t =t;
	}
	public int getInt() {return _c;}
	public String getString() {return _t;}
	public void setValues(int c, String t){_c=c;_t =t;}
}