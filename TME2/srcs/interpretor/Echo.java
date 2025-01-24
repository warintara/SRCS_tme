package srcs.interpretor;

import java.io.PrintStream;

public class Echo implements Command{
	
	private String[] commends; 
	public Echo(String[] commends) throws IllegalArgumentException{
		if(commends[0] != "echo " ) {
			throw new IllegalArgumentException("Commend echo manquant");
		}
		this.commends = commends;
		
	}
	@Override
	public void execute(PrintStream out) {
		for(String s : commends) {
			out.print(s);
		}

	}

}
