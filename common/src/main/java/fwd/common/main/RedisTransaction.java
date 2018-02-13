package fwd.common.main;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisTransaction implements KvTransaction {

    private Jedis jedis;
    private Transaction transaction;

    public RedisTransaction(Jedis jedis) {
        this.jedis = jedis;

        transaction = null;
    }

    @Override
    public void watch(String... keys) {
        jedis.watch(keys);
    }

    @Override
    public void multi() {
        transaction = jedis.multi();
    }

    @Override
    public boolean exec() {
        boolean result = transaction.exec() != null;

        transaction = null;

        return result;
    }

    @Override
    public void discard() {
        transaction.discard();

        transaction = null;
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
        if (transaction == null) {
            jedis.set(key, Integer.toString(value));
        }
        else {
            transaction.set(key, Integer.toString(value));
        }
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
        if (transaction == null) {
            jedis.set(key, Double.toString(value));
        }
        else {
            transaction.set(key, Double.toString(value));
        }
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
        if (transaction == null) {
            jedis.set(key, Boolean.toString(value));
        }
        else {
            transaction.set(key, Boolean.toString(value));
        }
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
        if (transaction == null) {
            jedis.set(key, value);
        }
        else {
            transaction.set(key, value);
        }
    }
}
