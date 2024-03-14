package io.github.shinyruo.hellocucumber.agent;

import lombok.Getter;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.params.SetParams;

import java.util.Map;
import java.util.Properties;

public class RedisAgent extends AbstractAgent {
    public static final String DEFAULT_HOST = Protocol.DEFAULT_HOST;
    public static final int DEFAULT_PORT = Protocol.DEFAULT_PORT;
    public static final int DEFAULT_MAX_TOTAL = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;
    public static final int DEFAULT_MAX_IDLE = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
    public static final int DEFAULT_MIN_IDLE = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;
    @Getter
    private final String host;
    @Getter
    private final int port;
    private final String user;
    private final String password;
    private final ConnectionPoolConfig connectionPoolConfig;
    private final JedisPooled jedisPooled;

    public RedisAgent(String agentName, Properties properties) {
        super(agentName, properties);
        this.host = properties.getProperty("host", DEFAULT_HOST);
        this.port = Integer.parseInt(properties.getProperty("port", String.valueOf(DEFAULT_PORT)));
        this.user = properties.getProperty("user", "");
        this.password = properties.getProperty("password", "");
        this.connectionPoolConfig = setPoolConfig(properties);
        this.jedisPooled = new JedisPooled(connectionPoolConfig, host, port, user, password);
    }

    private ConnectionPoolConfig setPoolConfig(Properties properties) {
        final ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
        poolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal", String.valueOf(DEFAULT_MAX_TOTAL))));
        poolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle", String.valueOf(DEFAULT_MAX_IDLE))));
        poolConfig.setMinIdle(Integer.parseInt(properties.getProperty("minIdle", String.valueOf(DEFAULT_MIN_IDLE))));
        return poolConfig;
    }

    public void delKey(String key) {
        this.jedisPooled.del(key);
    }

    public void setKey(String key, String value) {
        this.jedisPooled.set(key, value);
    }

    public void setKey(String key, String value, SetParams setParams) {
        this.jedisPooled.set(key, value, setParams);
    }

    public String getKey(String key) {
        return this.jedisPooled.get(key);
    }

    public void checkIfKeyExists(String key) {
        this.jedisPooled.exists(key);
    }

    public void pexpireKey(String key, long milliseconds) {
        this.jedisPooled.pexpire(key, milliseconds);
    }

    public void expireKey(String key, int seconds) {
        this.jedisPooled.expire(key, seconds);
    }

    public void pttlKey(String key) {
        this.jedisPooled.pttl(key);
    }

    public void ttlKey(String key) {
        this.jedisPooled.ttl(key);
    }

    public void persistKey(String key) {
        this.jedisPooled.persist(key);
    }

    public void hdelKey(String key, String... fields) {
        this.jedisPooled.hdel(key, fields);
    }

    public void hsetKey(String key, String field, String value) {
        this.jedisPooled.hset(key, field, value);
    }

    public void hsetKey(String key, Map<String, String> hash) {
        this.jedisPooled.hset(key, hash);
    }

}
