package srcs.securite;

import java.security.GeneralSecurityException;
import java.security.PublicKey;

public class Certif {
	private final String  id;
	private PublicKey pk;
	private byte[] sig; 
	private final String algo;
	
	public Certif(String id, PublicKey pk, byte[] sig, String algo) {
		super();
		this.id = id;
		this.pk = pk;
		this.sig = sig;
		this.algo = algo;
	}

	public PublicKey getPk() {
		return pk;
	}


	public byte[] getSig() {
		return sig;
	}


	public String getId() {
		return id;
	}
	
	public  boolean verify(PublicKey publickeyauthority)throws GeneralSecurityException{
		
		return false;
		
		
	}
	
}
