package srcs.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMultiThread {
	private final int port;
	private final Class<? extends Service> sc;
	private Service s =null; // c'est pas notre travail de l'initialiser!!!
	
	public ServeurMultiThread(int port,Class<? extends Service> sc) {
		super();
		this.port = port;
		this.sc =sc;
		
		
	} 
	
	public void listen() {
		try(ServerSocket soc = new ServerSocket(port)) {
			while ( true ) {
				Socket client = soc.accept();
				Service v = getService();	
				Thread t = new Thread(() -> { v.execute(client); } ); // client a fermer dans le thread
				t.start();
			}
		}catch (IOException e) {

			e.printStackTrace();
		
		
		}

	}
	
	private Service getService() {
		if(sc.isAnnotationPresent(EtatGlobal.class)) {
			if(s==null)
				try {
					s=sc.getConstructor().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
		}
		else if (sc.isAnnotationPresent(SansEtat.class)) {
			try {
				return  sc.getConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			throw new IllegalStateException(); 
			
		}
		
		return s;
	}
}
