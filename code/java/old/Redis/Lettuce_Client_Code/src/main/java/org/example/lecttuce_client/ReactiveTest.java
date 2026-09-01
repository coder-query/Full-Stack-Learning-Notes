package org.example.lecttuce_client;

import io.lettuce.core.api.reactive.RedisReactiveCommands;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveTest {
  public static void main(String[] args) throws InterruptedException {
    // 建立连接
    RedisReactiveCommands<String, String> commands = LettuceSyncClient.getReactiveConn();
    // 操作redis
    Mono<String> setc = commands.set("name", "xhz");
    System.out.println(setc.block());
    Mono<String> getc = commands.get("name");
    getc.subscribe(System.out::println);
    Flux<String> keys = commands.keys("*");
    keys.subscribe(System.out::println);

    // 开启一个事务，先把count设置为1，再将count自增1
    commands
        .multi()
        .doOnSuccess(
            r -> {
              commands
                  .set("count", "1")
                  .doOnNext(value -> System.out.println("count1：" + value))
                  .subscribe();
              commands
                  .incr("count")
                  .doOnNext(value -> System.out.println("count2：" + value))
                  .subscribe();
            })
        .flatMap(s -> commands.exec())
        .doOnNext(
            transactionResult ->
                System.out.println("transactionResult：" + transactionResult.wasDiscarded()))
        .subscribe();

    Thread.sleep(1000 * 5);
    // 关闭连接
    LettuceSyncClient.close();
  }
}
