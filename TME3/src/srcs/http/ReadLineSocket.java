package src.srcs.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ReadLineSocket implements RequestProcessor {
	
	public void bind(InputStream in, OutputStream out) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(in));
		String b; 
		while((b = buf.readLine()) != null ) {
			
		    System.out.println(b);
			
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
