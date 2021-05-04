/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package alumnos.p4.aliende.ms;

import java.io.Serializable;

public class Envelope implements Serializable {
	private static final long serialVersionUID = 1L;
	private int source;
	private int destination;
	private Serializable payload;

	public Envelope(int s, int d, Serializable p) {
		source = s;
		destination = d;
		payload = p;  //El mensaje
	}
	
	public int getSource() { return source; }
	public int getDestination() { return destination; }
	public Serializable getPayload() { return payload; }
}
