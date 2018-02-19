package fwd.common.kv;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisStore implements KvStore {

    private JedisPool jedisPool;

    public RedisStore(String host, int port) throws ConnectionException {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxWaitMillis(120000);

        jedisPool = new JedisPool(config, host, port);

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
        }
        catch (JedisConnectionException e) {
            throw new ConnectionException();
        }
        finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public RedisStore(String host) throws ConnectionException {
        this(host, 6379);
    }

    @Override
    public KvTransaction initTransaction() {
        return new RedisTransaction(jedisPool.getResource());
    }
}
