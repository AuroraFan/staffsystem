package com.fzq.staffsystem.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@Service
public class JedisAdapter implements InitializingBean {
    private JedisPool pool;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/1");
    }
    public void getJedis(){
        pool.getResource();
    }
    public long hset(String key, String field, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.hset(key, field, value);
        }catch(Exception e){

        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public String hget(String key, String field){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.hget(key, field);
        }catch(Exception e){

        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }
    public Map<String,String> hgetAll(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.hgetAll(key);
        }catch(Exception e){

        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }
    public long hdel(String key,String field){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.hdel(key, field);
        }catch(Exception e){

        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public long hlen(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.hdel(key);
        }catch(Exception e){

        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }
}
