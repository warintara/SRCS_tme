package srcs.securite;

import java.io.IOException;
import java.net.InetAddress;

public class ChannelDecorator implements Channel {
	
	private Channel chan;
	
	
	public ChannelDecorator(Channel chan) {
		this.chan = chan;
	}

	@Override
	public void send(byte[] bytesArray) throws IOException {
		this.chan.send(bytesArray);

	}

	public byte[] recv() throws IOException {
		return this.chan.recv();
	}

	public InetAddress getRemoteHost() {
		return this.chan.getRemoteHost();
	}

	public int getRemotePort() {
		return chan.getRemotePort();
	}

	public InetAddress getLocalHost() {
		return chan.getLocalHost();
	}

	public int getLocalPort() {
		return chan.getLocalPort();
	}
	
}
