package by.epam.web.connection;

import by.epam.web.exception.ConnectionException;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int POOL_SIZE = 10;
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private static final ReentrantLock LOCKER = new ReentrantLock();
    private static ConnectionPool instance;

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>(POOL_SIZE);
        connectionsInUse = new ArrayDeque<>();
        ProxyConnectionFactory proxyConnectionFactory = new ProxyConnectionFactory(this);
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection proxyConnection = proxyConnectionFactory.create();
            availableConnections.offer(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            LOCKER.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
            LOCKER.unlock();
        }
        return instance;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        LOCKER.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
            }
        } finally {
            LOCKER.unlock();
        }
    }
    public ProxyConnection getConnection() throws ConnectionException {
        LOCKER.lock();
        try {
            if (availableConnections.isEmpty()) {
                throw new ConnectionException("Not available connection!");
            }
            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.offer(connection);
            return connection;
        } finally {
            LOCKER.unlock();
        }
    }

    public void destroy() {
        for (ProxyConnection connection: connectionsInUse
             ) {
            returnConnection(connection);
        }
        for (ProxyConnection connection: availableConnections
             ) {
            try {
                connection.destroy();
            } catch (SQLException e) {
                throw new ConnectionException(e.getMessage(), e);
            }
        }
    }
}
