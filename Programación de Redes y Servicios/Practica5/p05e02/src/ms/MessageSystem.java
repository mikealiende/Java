/* **********************************
 * ******** NO MODIFICAR ************
 * **********************************
 */
package ms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Vector;


public class MessageSystem {
	private int user;
	private Vector<PeerAddress> peers= new Vector<PeerAddress>();
	private MailBox mailbox;
	private Sending sending;
	boolean tcp=true;
	
	public MessageSystem(int whoIam, String usersFile) throws FileNotFoundException {
		
		//System.out.println(usersFile+whoIam);
		
		user = whoIam;
		InetSocketAddress localSocketAddr = loadPeerAddresses(usersFile);
		
		sending = new Sending(user,peers);

		mailbox = new MailBox(localSocketAddr); 
		new Thread(mailbox).start();
	}
	
	
	public void SetId(int id) {
		user = id;
	}
	
	public int getMe() {return user;}
	
	public void send(int dst, Serializable message) {
		sending.send(dst, message);
	}
	
	public void forwardPeers(Envelope e){
		Serializable message = e.getPayload();
		int src = e.getSource();
		for(int pid=0;pid<peers.size();pid++){
			if((src!=pid)&&(user!=pid))
				send(pid,message);
		}
	}
	

	public boolean isMe(int whois) {
		return user==whois;	}
	
	public Envelope receive() {
		Envelope e = mailbox.getNextMessage();
		
		return e;
	}
	
	public void stopMailbox() {
		mailbox.closeSocket(); 
		// cerrando el socket provocaremos la excepción que parará el run del hilo
	}
	
	public void printPeers(){
		/*
		 * Para depuración de código: visualizar la lista de usuarios (peers)
		 */
		for(PeerAddress peer:peers){
			System.err.println(peer.getUser()+" "+peer.getAddress().toString()+":"+peer.getPort());
		}
	}

	private InetSocketAddress loadPeerAddresses(String usersFile) throws FileNotFoundException {
		
		
		BufferedReader in = new BufferedReader(new FileReader(usersFile));
		
		String line;
		int index=0;
		InetSocketAddress addr=null;
		try {
			while ((line = in.readLine()) != null) {
				String[] splitline = line.split(":");
				peers.add(new PeerAddress(index,splitline[0],Integer.parseInt(splitline[1])));
				if(index==user)
					addr = peers.lastElement().getSockAddress();	
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {}
		}
		return addr;
	}
	
}
