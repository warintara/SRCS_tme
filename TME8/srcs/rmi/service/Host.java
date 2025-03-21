package srcs.rmi.service;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Host extends Remote {
	
	<P, R> FunctionService<P,R>  deployNewService(String nom, Class<? extends FunctionService<P,R> > cl ) throws RemoteException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	<P, R> FunctionService<P,R>   deployExistingService(FunctionService<P,R>  f)throws RemoteException;
	boolean undeployService(String nomService)throws RemoteException;
	List<String> getServices() throws RemoteException;
}
