package srcs.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FunctionService<P,R> extends Remote{
	String getName() throws RemoteException;
	R invoke(P p ) throws RemoteException;
	void migrateTo(Host h) throws RemoteException;
	boolean isMigrated() throws RemoteException;
}
