package ms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MailBox implements Runnable {
	final static int SIZEMBMAX=100000;
	protected PCQueue _queue;	// cola de objetos Envelope (buzón)
	protected int _port;		// puerto TCP de escucha
	
	private ServerSocket _server_socket;
	private InetSocketAddress _server;
	
	

	public Envelope getNextMessage(){
		Object o = _queue.get() ;
		
		Envelope sobre = (Envelope)o;
		return sobre;
		
		}
	public MailBox(InetSocketAddress server){
		_queue = new PCQueue(SIZEMBMAX);
		_server = server;
		
	
	}

	public void closeSocket() {
		try {
			
			_server_socket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}


	public void run() {
		//System.err.println("Executing run MailBox in thread "+Thread.currentThread().getId());
		_port = _server.getPort();
		Envelope sobre = null;
		//System.out.println("Esta funcionando");
		try {
			_server_socket = new ServerSocket(_port);
			while(true) {
				Socket socketTCP = _server_socket.accept();
				ObjectInputStream in = new ObjectInputStream(socketTCP.getInputStream());
				try {
					 sobre = (Envelope)in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				_queue.put(sobre);
				socketTCP.close();
				
			}
			
				
		} catch (IOException e) {
			System.out.println("Cerrando hilo");
		}
		
	
	}

}
