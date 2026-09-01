package org.example.boot_redis_template_code.utils;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

/** Redis 工具类 */
@Component
public class RedisUtils {

  @Resource private RedisTemplate<String, Object> redisTemplate;

  private static final Long RELEASE_SUCCESS = 1L;
  private static final String RELEASE_SCRIPT =
      "if redis.call('get', KEYS[1]) == ARGV[1] then "
          + "return redis.call('del', KEYS[1]) "
          + "else "
          + "return 0 "
          + "end";

  // ============================== Common String ==============================

  // 设置键值对
  public void set(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  // 设置键值对并指定过期时间
  public void set(String key, Object value, long timeout, TimeUnit unit) {
    redisTemplate.opsForValue().set(key, value, timeout, unit);
  }

  // 设置键值对并指定过期时间
  public void set(String key, Object value, long seconds) {
    redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
  }

  // 获取值
  public Object get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  // 获取值
  public String getString(String key) {
    Object obj = redisTemplate.opsForValue().get(key);
    return obj == null ? null : obj.toString();
  }

  // 删除键
  public Boolean delete(String key) {
    return redisTemplate.delete(key);
  }

  // 批量删除键
  public Long delete(Collection<String> keys) {
    return redisTemplate.delete(keys);
  }

  // 判断键是否存在
  public Boolean hasKey(String key) {
    return redisTemplate.hasKey(key);
  }

  // 设置过期时间
  public Boolean expire(String key, long timeout, TimeUnit unit) {
    return redisTemplate.expire(key, timeout, unit);
  }

  // 获取过期时间
  public Long getExpire(String key, TimeUnit unit) {
    return redisTemplate.getExpire(key, unit);
  }

  // 如果不存在，则设置
  public Boolean setNx(String key, Object value) {
    return redisTemplate.opsForValue().setIfAbsent(key, value);
  }

  // 分布式锁
  public Boolean tryLock(String lockKey, String requestId, long seconds) {
    return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, seconds, TimeUnit.SECONDS);
  }

  // 分布式锁
  public Boolean tryLock(String lockKey, String requestId, long timeout, TimeUnit unit) {
    return redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, timeout, unit);
  }

  // 分布式锁释放
  public Boolean releaseLock(String lockKey, String requestId) {
    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
    redisScript.setScriptText(RELEASE_SCRIPT);
    redisScript.setResultType(Long.class);
    Long result =
        redisTemplate.execute(
            redisScript, Collections.singletonList(lockKey), Collections.singletonList(requestId));
    return RELEASE_SUCCESS.equals(result);
  }

  // ============================== List ==============================

  // 向列表尾部添加元素
  public Long rPush(String key, Object value) {
    return redisTemplate.opsForList().rightPush(key, value);
  }

  // 向列表头部添加元素
  public Long lPush(String key, Object value) {
    return redisTemplate.opsForList().leftPush(key, value);
  }

  // 向列表尾部批量添加元素
  public Long rPushAll(String key, Object... values) {
    return redisTemplate.opsForList().rightPushAll(key, values);
  }

  // 向列表头部批量添加元素
  public Long lPushAll(String key, Object... values) {
    return redisTemplate.opsForList().leftPushAll(key, values);
  }

  // 获取列表指定范围的元素
  public List<Object> lRange(String key, long start, long end) {
    return redisTemplate.opsForList().range(key, start, end);
  }

  // 获取列表长度
  public Long lSize(String key) {
    return redisTemplate.opsForList().size(key);
  }

  // 从列表头部弹出元素
  public Object lPop(String key) {
    return redisTemplate.opsForList().leftPop(key);
  }

  // 从列表尾部弹出元素
  public Object rPop(String key) {
    return redisTemplate.opsForList().rightPop(key);
  }

  // 获取列表指定索引的元素
  public Object lIndex(String key, long index) {
    return redisTemplate.opsForList().index(key, index);
  }

  // 设置列表指定索引的元素
  public void lSet(String key, long index, Object value) {
    redisTemplate.opsForList().set(key, index, value);
  }

  // 移除列表元素
  public Long lRemove(String key, long count, Object value) {
    return redisTemplate.opsForList().remove(key, count, value);
  }

  // 裁剪列表，只保留指定区间内的元素
  public void lTrim(String key, long start, long end) {
    redisTemplate.opsForList().trim(key, start, end);
  }

  public List<String> lRangeString(String key, long start, long end) {
    List<Object> objects = redisTemplate.opsForList().range(key, start, end);
    if (objects == null) {
      return new ArrayList<>();
    }
    return objects.stream()
        .filter(obj -> obj instanceof String)
        .map(obj -> (String) obj)
        .collect(Collectors.toList());
  }

  // ============================== Hash ==============================

  // 设置哈希字段的值
  public void hPut(String key, String hashKey, Object value) {
    redisTemplate.opsForHash().put(key, hashKey, value);
  }

  // 批量设置哈希字段的值
  public void hPutAll(String key, Map<String, Object> map) {
    redisTemplate.opsForHash().putAll(key, map);
  }

  // 获取哈希字段的值
  public Object hGet(String key, String hashKey) {
    return redisTemplate.opsForHash().get(key, hashKey);
  }

  // 获取哈希中多个字段的值
  public List<Object> hMultiGet(String key, Collection<Object> hashKeys) {
    return redisTemplate.opsForHash().multiGet(key, hashKeys);
  }

  // 获取哈希中的所有字段和值
  public Map<Object, Object> hGetAll(String key) {
    return redisTemplate.opsForHash().entries(key);
  }

  // 删除哈希中的一个或多个字段
  public Long hDelete(String key, Object... hashKeys) {
    return redisTemplate.opsForHash().delete(key, hashKeys);
  }

  // 判断哈希中是否存在某个字段
  public Boolean hExists(String key, String hashKey) {
    return redisTemplate.opsForHash().hasKey(key, hashKey);
  }

  // 获取哈希中的所有字段
  public Set<Object> hKeys(String key) {
    return redisTemplate.opsForHash().keys(key);
  }

  // 获取哈希中的所有值
  public List<Object> hValues(String key) {
    return redisTemplate.opsForHash().values(key);
  }

  // 获取哈希中字段的数量
  public Long hSize(String key) {
    return redisTemplate.opsForHash().size(key);
  }

  // 哈希字段值增加
  public Long hIncrement(String key, String hashKey, long delta) {
    return redisTemplate.opsForHash().increment(key, hashKey, delta);
  }

  // 哈希字段值增加（浮点数）
  public Double hIncrement(String key, String hashKey, double delta) {
    return redisTemplate.opsForHash().increment(key, hashKey, delta);
  }

  // ============================== Set ==============================

  // 向集合添加元素
  public Long sAdd(String key, Object... values) {
    return redisTemplate.opsForSet().add(key, values);
  }

  // 从集合中移除元素
  public Long sRemove(String key, Object... values) {
    return redisTemplate.opsForSet().remove(key, values);
  }

  // 从集合中随机移除并返回一个元素
  public Object sPop(String key) {
    return redisTemplate.opsForSet().pop(key);
  }

  // 从集合中随机返回一个或多个元素
  public List<Object> sRandomMembers(String key, long count) {
    return redisTemplate.opsForSet().randomMembers(key, count);
  }

  // 获取集合中的所有元素
  public Set<Object> sMembers(String key) {
    return redisTemplate.opsForSet().members(key);
  }

  // 判断元素是否是集合的成员
  public Boolean sIsMember(String key, Object value) {
    return redisTemplate.opsForSet().isMember(key, value);
  }

  // 获取集合的大小
  public Long sSize(String key) {
    return redisTemplate.opsForSet().size(key);
  }

  // 获取两个集合的差集
  public Set<Object> sDifference(String key1, String key2) {
    return redisTemplate.opsForSet().difference(key1, key2);
  }

  // 获取多个集合的差集
  public Set<Object> sDifference(Collection<String> keys) {
    return redisTemplate.opsForSet().difference(keys);
  }

  // 获取两个集合的交集
  public Set<Object> sIntersect(String key1, String key2) {
    return redisTemplate.opsForSet().intersect(key1, key2);
  }

  // 获取多个集合的交集
  public Set<Object> sIntersect(Collection<String> keys) {
    return redisTemplate.opsForSet().intersect(keys);
  }

  // 获取两个集合的并集
  public Set<Object> sUnion(String key1, String key2) {
    return redisTemplate.opsForSet().union(key1, key2);
  }

  // 获取多个集合的并集
  public Set<Object> sUnion(Collection<String> keys) {
    return redisTemplate.opsForSet().union(keys);
  }

  // ============================== ZSet ==============================

  // 向有序集合添加元素
  public Boolean zAdd(String key, Object value, double score) {
    return redisTemplate.opsForZSet().add(key, value, score);
  }

  // 批量向有序集合添加元素
  public Long zAdd(
      String key,
      Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<Object>> tuples) {
    return redisTemplate.opsForZSet().add(key, tuples);
  }

  // 从有序集合中移除元素
  public Long zRemove(String key, Object... values) {
    return redisTemplate.opsForZSet().remove(key, values);
  }

  // 增加有序集合中元素的分数
  public Double zIncrementScore(String key, Object value, double delta) {
    return redisTemplate.opsForZSet().incrementScore(key, value, delta);
  }

  // 获取有序集合中元素的排名(从小到大)
  public Long zRank(String key, Object value) {
    return redisTemplate.opsForZSet().rank(key, value);
  }

  // 获取有序集合中元素的排名(从大到小)
  public Long zReverseRank(String key, Object value) {
    return redisTemplate.opsForZSet().reverseRank(key, value);
  }

  // 获取有序集合中指定范围的元素(按分数从小到大)
  public Set<Object> zRange(String key, long start, long end) {
    return redisTemplate.opsForZSet().range(key, start, end);
  }

  // 获取有序集合中指定范围的元素(按分数从大到小)
  public Set<Object> zReverseRange(String key, long start, long end) {
    return redisTemplate.opsForZSet().reverseRange(key, start, end);
  }

  // 获取有序集合中指定分数范围的元素(按分数从小到大)
  public Set<Object> zRangeByScore(String key, double min, double max) {
    return redisTemplate.opsForZSet().rangeByScore(key, min, max);
  }

  // 获取有序集合中指定分数范围的元素(按分数从大到小)
  public Set<Object> zReverseRangeByScore(String key, double min, double max) {
    return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
  }

  // 获取有序集合中指定分数范围的元素(带分数)
  public Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<Object>>
      zRangeByScoreWithScores(String key, double min, double max) {
    return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
  }

  // 获取有序集合中指定索引范围的元素(带分数)
  public Set<org.springframework.data.redis.core.ZSetOperations.TypedTuple<Object>>
      zRangeWithScores(String key, long start, long end) {
    return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
  }

  // 获取有序集合的大小
  public Long zSize(String key) {
    return redisTemplate.opsForZSet().size(key);
  }

  // 获取有序集合中指定分数范围内的元素数量
  public Long zCount(String key, double min, double max) {
    return redisTemplate.opsForZSet().count(key, min, max);
  }

  // 获取有序集合中元素的分数
  public Double zScore(String key, Object value) {
    return redisTemplate.opsForZSet().score(key, value);
  }

  // 移除有序集合中指定排名范围的元素
  public Long zRemoveRange(String key, long start, long end) {
    return redisTemplate.opsForZSet().removeRange(key, start, end);
  }

  // 移除有序集合中指定分数范围的元素
  public Long zRemoveRangeByScore(String key, double min, double max) {
    return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
  }

  // ============================== HyperLogLog ==============================

  // 添加元素到HyperLogLog
  public Long pfAdd(String key, Object... values) {
    return redisTemplate.opsForHyperLogLog().add(key, values);
  }

  // 获取HyperLogLog的基数估计值
  public Long pfCount(String key) {
    return redisTemplate.opsForHyperLogLog().size(key);
  }

  // 合并多个HyperLogLog
  public Long pfMerge(String destKey, String... sourceKeys) {
    return redisTemplate.opsForHyperLogLog().union(destKey, sourceKeys);
  }

  // ============================== Stream ==============================

  // 添加消息到Stream
  public org.springframework.data.redis.connection.stream.RecordId xAdd(
      String key, Map<Object, Object> body) {
    return redisTemplate.opsForStream().add(key, body);
  }

  // 创建消费者组
  public String xGroupCreate(String key, String groupName) {
    return redisTemplate.opsForStream().createGroup(key, groupName);
  }

  // 读取消息
  public List<org.springframework.data.redis.connection.stream.MapRecord<String, Object, Object>>
      xRead(
          String key,
          org.springframework.data.redis.connection.stream.StreamOffset<String> offset) {
    return redisTemplate.opsForStream().read(offset);
  }

  // 消费者组读取消息
  public List<org.springframework.data.redis.connection.stream.MapRecord<String, Object, Object>>
      xReadGroup(
          String groupName,
          String consumerName,
          org.springframework.data.redis.connection.stream.StreamOffset<String> offset) {
    return redisTemplate
        .opsForStream()
        .read(
            org.springframework.data.redis.connection.stream.Consumer.from(groupName, consumerName),
            offset);
  }

  // 确认消息
  public Long xAck(String key, String groupName, String... recordIds) {
    return redisTemplate.opsForStream().acknowledge(key, groupName, recordIds);
  }
}
