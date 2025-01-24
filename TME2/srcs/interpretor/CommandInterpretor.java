package srcs.interpretor;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandInterpretor {
	private Map<String,Class<? extends Command>> map; 
	
	public CommandInterpretor() {
		this.map = new HashMap<>();
		map.put("cat", Cat.class);
		map.put("echo", Echo.class);
	}
	public Class<? extends Command> getClassOf(String s){
		if(map.get(s) != null) {
			return map.get(s) ;
		}
		return null;
	}
	
	public void perform(String command, OutputStream o) throws Exception{
		String[] ss = command.split(" ");
		String com = ss[0];
		for(String s : ss) {
			System.out.println("s = "+s);
		}
		if (com == "cat") {
			Class<? extends Command> cl = getClassOf(com);
			Object ob =cl.getConstructor().newInstance(command);
			Method m = cl.getMethod("execute", cl);
			m.invoke(ob, o);
		}
		if (com == "echo") {
			Class<? extends Command> cl = getClassOf(com);
			Object ob =cl.getConstructor().newInstance(command);
			Method m = cl.getMethod("execute", cl);
			m.invoke(ob, o);
		}
		else {
			System.out.println("com = "+com);
			throw new CommandNotFoundException(" command non trouvé");
		}
	}
	
}
