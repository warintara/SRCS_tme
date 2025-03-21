package srcs.rmi.service;

import java.rmi.RemoteException;

public abstract class AbstractFucntionService<P, R> implements FunctionService<P, R> {
	private String nom ; 
	
	@Override
	public String getName() throws RemoteException {
		return nom;
	}

	@Override
	public R invoke(P p) throws RemoteException {
		R r = perfrom(p);
		return r;
	}

	protected abstract R perfrom(P p);

	@Override
	public FunctionService<P, R> migrateTo(Host h) throws RemoteException {
		Class<? extends FunctionService<P,R>> cl =fnc.getClass();
		h.deployNewService(nom, cl);
		return null;
	}

}
