package srcs.securite;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Util {
	
 public static KeyPair generateNewKeyPair(String algorithm, int sizekey)throws NoSuchAlgorithmException{
	 KeyPairGenerator key = KeyPairGenerator.getInstance(algorithm);
	 key.initialize(sizekey);
	 
	return key.generateKeyPair();
	 
 }

}