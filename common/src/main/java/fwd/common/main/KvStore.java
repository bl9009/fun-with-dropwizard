package fwd.common.main;

public interface KvStore
{
    void connect(String host) throws ConnectionException;
    void connect(String host, int port) throws ConnectionException;

    KvTransaction initTransaction();
}
