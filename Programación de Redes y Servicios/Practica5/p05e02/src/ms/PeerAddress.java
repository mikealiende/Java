/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package ms;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class PeerAddress {
	public int user;
	public String host;
	private InetAddress address;
	public int port;
	

	public PeerAddress(int u,String host, int p) {
		user = u; 
		port = p;
		try {
			address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			System.err.println("Uknown host "+host);
		}
	}
	public String getHost(){return host;}
	public int getUser(){return user;}
	public InetSocketAddress getSockAddress(){return new InetSocketAddress(address,port);}
	public InetAddress getAddress(){return address;}
	public int getPort(){return port;}
}
