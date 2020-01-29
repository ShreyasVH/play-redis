package services.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import services.CacheService;

import java.time.Duration;

public class CacheServiceImpl implements CacheService
{
    final JedisPoolConfig poolConfig = buildPoolConfig();
    JedisPool jedisPool = new JedisPool(poolConfig, System.getenv("REDIS_IP"), Integer.valueOf(System.getenv("REDIS_PORT")));

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    public Boolean set(String key, String value, Integer expiry)
    {
        Boolean isSuccess = false;
        try(Jedis jedis = jedisPool.getResource())
        {
            jedis.set(key, value);
            jedis.expire(key, expiry);
            isSuccess = true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public String get(String key)
    {
        String value = "";
        try(Jedis jedis = jedisPool.getResource())
        {
            value = jedis.get(key);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return value;
    }

    public Boolean exists(String key)
    {
        Boolean exists = false;
        try(Jedis jedis = jedisPool.getResource())
        {
            exists = jedis.exists(key);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return exists;
    }

    public Boolean delete(String key)
    {
        Boolean success = false;
        try(Jedis jedis = jedisPool.getResource())
        {
            Long keysDeleted = jedis.del(key);
            success = (0 != keysDeleted);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return success;
    }
}
