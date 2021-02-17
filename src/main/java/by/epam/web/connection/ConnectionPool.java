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
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();
    private static final ReentrantLock CONNECTION_LOCKER = new ReentrantLock();
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
            INSTANCE_LOCKER.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
            INSTANCE_LOCKER.unlock();
        }
        return instance;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        CONNECTION_LOCKER.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
            }
        } finally {
            CONNECTION_LOCKER.unlock();
        }
    }
    public ProxyConnection getConnection() throws ConnectionException {
        CONNECTION_LOCKER.lock();
        try {
            if (availableConnections.isEmpty()) {
                throw new ConnectionException("Not available connection!");
            }
            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.offer(connection);
            return connection;
        } finally {
            CONNECTION_LOCKER.unlock();
        }
    }
    public void destroy() {
        for (ProxyConnection connection: connectionsInUse) {
            returnConnection(connection);
        }
        for (ProxyConnection connection: availableConnections) {
            try {
                connection.destroy();
            } catch (SQLException e) {
                throw new ConnectionException(e.getMessage(), e);
            }
        }
    }
}
