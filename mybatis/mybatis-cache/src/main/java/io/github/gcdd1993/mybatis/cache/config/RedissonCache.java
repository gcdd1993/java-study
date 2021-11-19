package io.github.gcdd1993.mybatis.cache.config;

import org.apache.ibatis.cache.Cache;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * MyBatis cache implementation
 *
 * @author Nikita Koksharov
 */
public class RedissonCache implements Cache {

    private final String id;
    private RMapCache<Object, Object> mapCache;
    private long timeToLive;
    private long maxIdleTime;
    private int maxSize;

    public RedissonCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        check();
        mapCache.put(o, o1, timeToLive, TimeUnit.MILLISECONDS, maxIdleTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object getObject(Object o) {
        check();
        if (maxIdleTime == 0 && maxSize == 0) {
            return mapCache.getWithTTLOnly(o);
        }

        return mapCache.get(o);
    }

    @Override
    public Object removeObject(Object o) {
        check();
        return mapCache.remove(o);
    }

    @Override
    public void clear() {
        check();
        mapCache.clear();
    }

    @Override
    public int getSize() {
        check();
        return mapCache.size();
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setMaxIdleTime(long maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    public void setRedissonConfig(String config) {
        Config cfg;
        try {
            // getClass()是相对路径，而getClassLoader是取Jar内
            InputStream is = getClass().getClassLoader().getResourceAsStream(config);
            cfg = Config.fromYAML(is);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't parse config", e);
        }

        RedissonClient redisson = Redisson.create(cfg);
        mapCache = getMapCache(id, redisson);
        if (maxSize > 0) {
            mapCache.setMaxSize(maxSize);
        }
    }

    protected RMapCache<Object, Object> getMapCache(String id, RedissonClient redisson) {
        return redisson.getMapCache(id);
    }

    private void check() {
        if (mapCache == null) {
            throw new IllegalStateException("Redisson config is not defined");
        }
    }

}
