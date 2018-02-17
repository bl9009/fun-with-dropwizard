package fwd.common.main;

public interface KvTransaction {

    void watch(String... keys);
    void multi();
    void exec() throws TransactionFailedException;
    void discard();

    void close();

    int getInt(String key) throws KeyException;
    void setInt(String key, int value);

    double getDouble(String key) throws KeyException;
    void setDouble(String key, double value);

    boolean getBoolean(String key) throws KeyException;
    void setBoolean(String key, boolean value);

    String getString(String key) throws KeyException;
    void setString(String key, String value);

    void incrBy(String key, int value);
    void decrBy(String key, int value);
}
