package fwd.common.main;

public interface KvStore
{
    void connect(String host) throws ConnectionException;
    void connect(String host, int port) throws ConnectionException;

    int getInt(String key) throws KeyException;
    void setInt(String key, int value);

    double getDouble(String key) throws KeyException;
    void setDouble(String key, double value);

    boolean getBoolean(String key) throws KeyException;
    void setBoolean(String key, boolean value);

    String getString(String key) throws KeyException;
    void setString(String key, String value);
}
