package com.lucien.db;

import com.lucien.utils.AlertUtils;

import java.util.List;
import redis.clients.jedis.Jedis;

import static java.lang.System.out;

/**
 * <pre>
 *     author : Lucien Z
 *     e-mail : 825038797@qq.com
 *     time   : 2019/04/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RedisDBHelper {
    private static volatile RedisDBHelper redisDBHelper;

    public static RedisDBHelper getRedisDBHelper() {
        if (redisDBHelper == null) {
            synchronized (RedisDBHelper.class) {
                if (redisDBHelper == null) {
                    redisDBHelper = new RedisDBHelper();
                }
            }
        }
        return redisDBHelper;
    }

    private Jedis jedis;

    public RedisDBHelper() {
        jedis = new Jedis("localhost");
        if (jedis.isConnected()) {
            AlertUtils.popMessage("","数据库连接成功！");
        } else {
            AlertUtils.popMessage("","数据库连接失败，将无法存储数据!\n" +
                    "请查看是否开启数据库服务。");
        }
    }

    public void setProperty(String key, String value) {
        if (isConnected()) {
            jedis.set(key, value);
        }
    }

    public String getProperty(String key) {
        String res = "";
        if (isConnected()) {
            res = jedis.get(key);
        }
        return res;
    }

    public void setList(String key, List<String> ls) {
        if (isConnected()) {
            for (String l : ls) {
                jedis.lpush(key, l);
            }
        }
    }

    public List<String> getList(String key,long start,long end){
        List<String> res = null;
        if (isConnected()) {
            res = jedis.lrange(key,start,end);
        }
        return res;
    }

    private boolean isConnected() {
        return jedis != null && jedis.isConnected();
    }

    public static void init() {
        RedisDBHelper dbHelper = RedisDBHelper.getRedisDBHelper();
        dbHelper.setProperty("name", "lucien");
        out.println(":" + dbHelper.getProperty("name"));
    }
}
