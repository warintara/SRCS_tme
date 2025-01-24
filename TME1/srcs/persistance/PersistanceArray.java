package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PersistanceArray {

	
	public static void saveArrayInt(String f, int[] tab) throws FileNotFoundException,IOException {
		int size = tab.length;
		OutputStream out = new FileOutputStream(f);
		DataOutputStream d = new DataOutputStream(out);
		d.writeInt(size);
		for (int i : tab) {
			d.writeInt(i);
			System.out.println(i);
			d.flush();
		}
		d.close();
	}
	public static  int[] loadArrayInt(String fichier) throws FileNotFoundException,IOException  {
		InputStream in = new FileInputStream(fichier);
		DataInputStream d = new DataInputStream(in);
		int size = d.readInt();
		int lu;
		int i;
		int[] tab = new int[size];
		
		for(i=0 ; i<size;i++){
			lu = d.readInt();
			System.out.println("i = "+i+" lu ="+lu);
			tab[i] = lu;
		}
		return tab;
		
	} 
}
