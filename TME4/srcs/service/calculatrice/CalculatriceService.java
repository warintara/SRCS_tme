package srcs.service.calculatrice;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import srcs.service.*;

public class CalculatriceService implements Service, Calculatrice {

	@Override
	public int add(int i1, int i2) {
		return i1 + i2;
	}

	@Override
	public int sous(int i1, int i2) {
		// TODO Auto-generated method stub
		return i1 + i2;
	}

	@Override
	public int mult(int i1, int i2) {
		// TODO Auto-generated method stub
		return i1 * i2;
	}

	@Override
	public  ResDiv div(int i1, int i2) {
		return new ResDiv(i1,i2);
	}

	@Override
	public synchronized void execute(Socket connextion) {
		
		try{
			ObjectOutputStream o = new ObjectOutputStream(connextion.getOutputStream());
			ObjectInputStream i = new ObjectInputStream(connextion.getInputStream());
			
			Object ret = i.readUTF();
			Integer x = i.readInt();
			Integer y = i.readInt();
			
			switch((String) ret) {
				case "add":
					o.writeObject(add(x, y));
					break;
				case "sous":
					o.writeObject(sous(x, y));
					break;
				case "mult":
					o.writeObject(mult(x, y));
					break;
				case "div":
					o.writeObject(div(x, y));
					break;
				default: 
					o.writeObject(new MyProtocolException(null));
			}
			
		}catch(Exception e ) {
			
		}

	}

}
