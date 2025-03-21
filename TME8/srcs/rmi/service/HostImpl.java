package srcs.rmi.service;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostImpl implements Host {
	
	private Map<String, FunctionService<?, ?> > map = new HashMap<>();
	
	@Override
	public <P, R> FunctionService<P, R> deployNewService(String nom, Class<? extends FunctionService<P, R>> cl)
			throws RemoteException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(! map.containsKey(nom) ) {
			FunctionService<P, R> f = cl.getConstructor().newInstance(nom);
			map.put(nom, f);
			return f;
		}
		throw new RemoteException();
		
	}

	@Override
	public <P, R> FunctionService<P, R> deployExistingService(FunctionService<P, R> f) throws RemoteException {
		UnicastRemoteObject.exportObject(f,0);
		map.put(f.getName(),f);
		return f ;
	}

	@Override
	public boolean undeployService(String nomService) throws RemoteException {
		FunctionService<?, ?> f =  map.remove(nomService);
		boolean res = UnicastRemoteObject.unexportObject(f,true);
		return res;
	}

	@Override
	public List<String> getServices() throws RemoteException {

		return (List<String>) map.keySet();
	}

}
