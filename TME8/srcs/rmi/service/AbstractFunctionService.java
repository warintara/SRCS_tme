package srcs.rmi.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractFunctionService<P extends Serializable, R extends Serializable> 
        implements FunctionService<P, R> {

    private transient boolean migrated = false;
    private transient FunctionService<P, R> migratedTo = null;
    private final transient ReentrantLock lock = new ReentrantLock();

    @Override
    public R invoke(P param) throws RemoteException {
        lock.lock();
        try {
            if (migratedTo != null) {
                return migratedTo.invoke(param); 
            }
            R result = perform(param);
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void migrateTo(Host host) throws RemoteException {
        lock.lock();
        try {
            if (migrated) {
                throw new IllegalStateException("Service already migrated");
            }

            // Déployer ce service sur l'autre hôte
            FunctionService<P, R> newService = host.deployExistingService(this);
            this.migrated = true;
            this.migratedTo = newService;

        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isMigrated() throws RemoteException {
        return migrated;
    }

    protected abstract R perform(P param) throws RemoteException;

}
