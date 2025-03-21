package srcs.rmi.service.SystemDeployer;

import java.rmi.server.UnicastRemoteObject;

import org.junit.After;
import org.junit.Before;

public class SystemDeployer {
	
	@Before
	public void before() {
		
		Integer i = 0;
		UnicastRemoteObject.exportObject(i,0);
		
	}
	
	@After
	public void after() {
		
	}
	

}
