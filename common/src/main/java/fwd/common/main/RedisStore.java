package fwd.common.main;

import redis.clients.jedis.Jedis;

public class RedisStore implements KvStore {

    private Jedis jedis;

    @Override
    public void connect(String host, int port) throws ConnectionException {
        jedis = new Jedis(host, port);

        if (!jedis.isConnected()) {
            throw new ConnectionException();
        }
    }

    public void connect(String host) throws ConnectionException {
        connect(host, 6379);
    }

    @Override
    public int getInt(String key) throws KeyException {
        if (!jedis.exists(key)) {
            throw new KeyException();
        }

        return Integer.parseInt(jedis.get(key));
    }

    @Override
    public void setInt(String key, int value) {
        jedis.set(key, Integer.toString(value));
    }

    @Override
    public double getDouble(String key) throws KeyException {
        if (!jedis.exists(key)) {
            throw new KeyException();
        }

        return Double.parseDouble(jedis.get(key));
    }

    @Override
    public void setDouble(String key, double value) {
        jedis.set(key, Double.toString(value));
    }

    @Override
    public boolean getBoolean(String key) throws KeyException {
        if (!jedis.exists(key)) {
            throw new KeyException();
        }

        return Boolean.parseBoolean(jedis.get(key));
    }

    @Override
    public void setBoolean(String key, boolean value) {
        jedis.set(key, Boolean.toString(value));
    }

    @Override
    public String getString(String key) throws KeyException {
        if (!jedis.exists(key)) {
            throw new KeyException();
        }

        return jedis.get(key);
    }

    @Override
    public void setString(String key, String value) {
        jedis.set(key, value);
    }
}
