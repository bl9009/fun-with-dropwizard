package fwd.common.main;

import redis.clients.jedis.Jedis;

public class RedisStore implements KvStore {

    private Jedis jedis;

    @Override
    public void connect(String host, int port) throws ConnectionException {
        jedis = new Jedis(host, port);

        while (!jedis.isConnected()) {
            System.out.println("connecting to jedis...");
        }

        jedis.set("test", "hallo");

        if (!jedis.isConnected()) {
            throw new ConnectionException();
        }
    }

    public void connect(String host) throws ConnectionException {
        connect(host, 6379);
    }

    @Override
    public KvTransaction initTransaction() {
        return new RedisTransaction(jedis);
    }
}
