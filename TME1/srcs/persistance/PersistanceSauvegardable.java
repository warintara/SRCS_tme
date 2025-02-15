package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PersistanceSauvegardable {
	public static void save(String fichier, Sauvegardable s) throws IOException {
	    try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fichier))) {
	    	out.writeUTF(s.getClass().getCanonicalName()); //enregistre le type exacte de l'obj
	        s.save(out);
	    }
	}

	public static Sauvegardable load(String f) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try(DataInputStream in = new DataInputStream(new FileInputStream(f))) {
			String className = in.readUTF();
			Class<? extends Sauvegardable> c = Class.forName(className).asSubclass(Sauvegardable.class);
			Constructor<? extends Sauvegardable> b = c.getConstructor(InputStream.class);
			return b.newInstance(in);
		}
	}


}
