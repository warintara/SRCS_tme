package src.srcs.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class ReadSocket implements RequestProcessor {
	
	public void bind(InputStream in, OutputStream out) throws IOException {
		int b;
		while((b = in.read()) != -1) {
			out.write(b);
			out.flush();
		}
	}
	
	
	// on peut utiliser le décorateur - reader/input reader qui permet de lire la ligne  
	@Override
	public void process(Socket connexion) {
		try {
			bind(connexion.getInputStream(), System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
