package org.example;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.net.http.HttpClient;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /**
         * redisson
         */
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://124.222.233.74:6379");
        singleServerConfig.setPassword("123456");
        singleServerConfig.setDatabase(0);
        singleServerConfig.setTimeout(3000);
        RedissonClient redissonClient = org.redisson.Redisson.create(config);

        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("test-bloom");
        //初始化布隆过滤器，预计插入100w数据，误率0.01
        bloomFilter.tryInit(100_0000, 0.01);

        //添加元素
        bloomFilter.add("test");
        bloomFilter.add("test2");
        bloomFilter.add("test3");
        bloomFilter.add("test4");
        bloomFilter.add("test5");
        bloomFilter.add("test6");
        bloomFilter.add("1313");

        // 判断元素是否在布隆过滤器中
        System.out.println(bloomFilter.contains("test"));
        System.out.println(bloomFilter.contains("test2"));
        System.out.println(bloomFilter.contains("test3"));
        System.out.println(bloomFilter.contains("test4456"));


        /**
         * 本地过滤器 guava
         */

        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                500,
                0.01);

        filter.put(1);
        filter.put(2);
        filter.put(3);

        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2345));

    }
}