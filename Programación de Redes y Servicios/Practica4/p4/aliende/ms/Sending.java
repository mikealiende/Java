package alumnos.p4.aliende.ms;
/*
 * AUTOR: Miguel Aliende e Ivan Latre
 * NIA: 742965 y 761264
 * FICHERO: Sending
 * TIEMPO: 
 * DESCRIPCION: Contiene las funciones para serializar en binario los objetos envelope y enviarlos mediante TCP a una direccion y puerto concretos.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Vector;


public class Sending {
	private Socket _socket;
	protected Vector<PeerAddress> _peers;	// lista de usuarios del sistema (id, IP/name-puerto de su MailBox)
	protected int _src;						// id del usuario local
	private ObjectOutputStream out;
	
	
	
	public InetSocketAddress findPeer(int user) {
		
		
		InetAddress Addr = _peers.get(user).getAddress();
		int port  = _peers.get(user).getPort();
		
		InetSocketAddress sockAddr = new InetSocketAddress (Addr,port);
		
//		System.out.println(sockAddr);
		

		return sockAddr;
	}

	Sending(int user, Vector<PeerAddress> peers){
		_src = user;
		_peers = peers;
	}


	public void send(int dst, Serializable message) {
		
		//System.out.println("Destination : "+dst);
		//System.out.println("message: "+message);
		InetSocketAddress sockAddr = findPeer(dst);
		
	
		InetAddress addr = sockAddr.getAddress();
		int port = sockAddr.getPort();
		
		
		try {
			_socket = new Socket(addr,port);
			Envelope sobre = new Envelope(_src,dst,message);	
			out = new ObjectOutputStream(_socket.getOutputStream());//Metemos mensaj ene el sobre
 			out.writeObject(sobre);   //Enviamos sobre
			//System.out.println("Enviando mensaje");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
