package srcs.securite;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ChannelBasic implements Channel {
	
	private  Socket sock;
	private InputStream input;
	private OutputStream output;
	
	public ChannelBasic(Socket s) throws IOException {
		this.sock = s; 
		this.input = sock.getInputStream();
		this.output = sock.getOutputStream();
		
	}
	@Override
	public void send(byte[] bytesArray) throws IOException {
		DataOutputStream dos = new DataOutputStream(output);
		dos.writeInt(bytesArray.length);
		dos.write(bytesArray);
		System.out.println("send");
	}

	@Override
	public byte[] recv() throws IOException {
		DataInputStream dis = new DataInputStream(input);
		int size = dis.readInt();
		byte[] buff =  dis.readNBytes(size);
		return buff;


	}

	@Override
	public InetAddress getRemoteHost() {
		return sock.getInetAddress();
	}

	@Override
	public int getRemotePort() {
		return sock.getPort();
	}

	@Override
	public InetAddress getLocalHost() {
		return sock.getLocalAddress();
	}

	@Override
	public int getLocalPort() {
		return  sock.getLocalPort();
	}

}
