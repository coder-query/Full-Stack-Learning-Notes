# Java 全栈学习代码库

Java 后端全栈技术学习代码集合，涵盖 Java 核心基础、并发编程、JVM、Web 开发、Spring 生态、ORM 框架、中间件、微服务等全方位技术栈。每个模块均为独立的 Maven 项目，可单独运行学习。

## 项目总览

| 类别 | 模块 |
|------|------|
| Java 核心 | `jdk1.8新特性` `JDK-8-Stream` `Java_Io流` `Java_SPI_Study` `Java_Design_Patterns` |
| 并发编程 | `JUC` `CompletableFutrue_1.8` `ConcurrentHashMap_1.7` `ConcurrentHashMap_1.8` |
| JVM | `JVM` |
| Web 基础 | `Servlet` `Javaweb` `SpringMVC` |
| Spring 核心 | `Spring5` `SSM` `Study_Test` |
| Spring Boot | `Springboot2` `SpringBoot3` `SpringBoot_log4j2` `SpringBoot_Logback` `Springboot2_Sa_Token` `自定义starter` |
| Spring Cloud | `SpringCloud` |
| 安全框架 | `SpringSecurity` |
| ORM 框架 | `Mybatis` `Mybatis_Plus` `Mybatis_Flex_Study` `Mybatis-Batch-Test` `Mybatis-Spring-Merge` `DynamicDataSourcePro` `Sharding-Jdbc-Study` |
| 中间件 | `Redis` `ElasticSearch-7` `MongoDB` `Zookeeper` |
| 工具库 | `Boot-EasyExcel` `Boot-MapStruct` |

---

## 模块详解

### Java 核心

#### `jdk1.8新特性`
JDK 8 新特性全面学习：Lambda 表达式、四大函数式接口（Consumer/Function/Predicate/Supplier）、方法引用、Stream 流操作、接口默认/静态方法、Optional 类。

#### `JDK-8-Stream`
系统学习 JDK 8 Stream API，按功能模块化组织：创建流、遍历/匹配/过滤、map 映射、聚合、规约、收集、统计、分组分区。

#### `Java_Io流`
Java IO 体系学习，覆盖字节流/字符流/缓冲流/转换流等。

#### `Java_SPI_Study`
Java SPI（Service Provider Interface）机制学习，通过 `ServiceLoader` 加载 Oracle/MySQL 数据库驱动实现，演示 SPI 解耦机制。

#### `Java_Design_Patterns`
设计模式学习，包含工厂模式（Factory Pattern）和单例模式（饿汉式/懒汉式/双重检查锁定）。

---

### 并发编程

#### `JUC`
Java 并发编程（JUC）深度学习，内容极其丰富（95+ Java 文件）：
- **线程基础**：Thread/Runnable/Callable、生命周期、守护线程、优先级、Join/Yield
- **线程安全**：synchronized、Lock/ReentrantLock、volatile
- **线程通信**：wait/notify、Condition、定制有序通信
- **并发工具**：CountDownLatch、CyclicBarrier、Semaphore
- **原子类**：AtomicInteger 等
- **线程池**：各种 Executor、ThreadPoolExecutor 自定义、拒绝策略
- **AQS**：AbstractQueuedSynchronizer 及自定义同步器
- **阻塞队列**与死锁分析
- **集合线程安全**：ArrayList/HashMap 并发问题及解决方案

#### `CompletableFutrue_1.8`
JDK 8 CompletableFuture 异步编程：异步任务创建、异常处理、线程池配置、TransmittableThreadLocal 上下文传递、异步任务编排。

#### `ConcurrentHashMap_1.7` & `ConcurrentHashMap_1.8`
对比学习 ConcurrentHashMap 在 JDK 1.7（分段锁 Segment）和 JDK 1.8（CAS + synchronized）中的实现差异。

---

### JVM

#### `JVM`
JVM 底层原理学习：四大引用（强/软/弱/虚）、对象内存布局（JOL）、GC 循环引用、Minor GC 触发、栈溢出、堆空间参数、finalize 机制。

---

### Web 基础

#### `Servlet`
Servlet 基础：生命周期、请求/响应处理。

#### `Javaweb`
JavaWeb 渐进式学习（7 个子模块）：Servlet 基础 → ServletContext → Properties 配置 → 登录功能 → Cookie → Session → JSP。

#### `SpringMVC`
SpringMVC 学习（3 个子模块）：
- 传统 SpringMVC（servlet → 注解开发）
- Java 配置方式初始化
- Spring Boot 整合（参数接收、RESTful、JSON、异常处理、拦截器、CORS）

---

### Spring 核心

#### `Spring5`
Spring 5 核心学习：
- **IOC**：XML 配置、注解配置
- **AOP**：静态代理/动态代理/JDK代理/CGLIB代理/切面编程
- **事务**：自定义 `@MyTransactional` 注解 + AOP 实现、事务传播机制

#### `SSM`
SSM（Spring + SpringMVC + MyBatis）整合，完整 MVC 分层架构。

#### `Study_Test`
综合测试项目（9 个子模块）：Spring IOC（XML/注解/纯注解）、AOP、事务、循环依赖、SSM、Spring Boot。

---

### Spring Boot

#### `Springboot2`
Spring Boot 2 基础：CORS 跨域配置、整合 Hutool/OkHttp/HttpClient/Fastjson。

#### `SpringBoot3`
Spring Boot 3 学习（2 个子模块）：
- 基础 Demo（含 GraalVM Native Image 支持）
- HTTP Interface 新特性（`@HttpExchange` 声明式 HTTP 客户端）

#### `SpringBoot_log4j2` & `SpringBoot_Logback`
日志框架学习：Log4j2 和 Logback 的配置与使用。

#### `Springboot2_Sa_Token`
Sa-Token 权限认证框架学习：登录认证、权限验证、会话管理。

#### `自定义starter`
自定义 Spring Boot Starter 学习：加密工具 Starter，演示自动装配、`@ConditionalOnProperty` 条件装配、`spring.factories` 机制。

---

### Spring Cloud

#### `SpringCloud`
Spring Cloud 微服务架构全面学习（6 大子项目）：
- **Eureka**：服务注册发现 + 商品/静态页面服务
- **实战 Demo**：Gateway 网关 + OpenFeign 远程调用 + 订单/商品微服务
- **Dubbo-Spring**：Dubbo 原生 XML 配置方式
- **Dubbo-SpringBoot**：Dubbo + Spring Boot 整合
- **Spring Cloud Alibaba**：Nacos 服务注册/配置中心、Consul、OpenFeign、Gateway 自定义谓词工厂
- **Dubbo-SpringCloudAlibaba**：Dubbo + Spring Cloud Alibaba 整合

---

### 安全框架

#### `SpringSecurity`
Spring Security 深度学习（4 个子项目）：
- **Spring Security 5**：内存/数据库用户认证、会话并发控制
- **Spring Security 6**：JWT 认证、RBAC 权限模型（Spring Boot 3）
- **JWT 基础**：Session vs JWT 对比
- **实战 Demo**：JWT + Redis Token 管理

---

### ORM 框架

#### `Mybatis`
原生 MyBatis 学习：基础 CRUD、一对多/多对一映射、动态 SQL、PageHelper 分页。

#### `Mybatis_Plus`
MyBatis-Plus 学习：QueryWrapper/LambdaQueryWrapper 条件构造器、分页查询、通用 Mapper/Service CRUD。

#### `Mybatis_Flex_Study`
MyBatis-Flex 框架学习：登录功能实现 + Sa-Token + Redis。

#### `Mybatis-Batch-Test`
MyBatis-Plus 批量操作性能测试。

#### `Mybatis-Spring-Merge`
MyBatis 与 Spring 原生整合（非 Spring Boot），纯 Spring 环境下整合方式。

#### `DynamicDataSourcePro`
动态数据源 5 种实现方式渐进式演进：
1. 手动实现 DataSource 接口
2. 继承 AbstractRoutingDataSource
3. AOP + 自定义注解 `@DBsource`
4. 多数据源独立配置（Master/Slave）
5. 第三方 `dynamic-datasource-spring-boot-starter`

#### `Sharding-Jdbc-Study`
Sharding-JDBC 分库分表：水平分表、垂直分库。

---

### 中间件

#### `Redis`
Redis 学习（3 种 Java 客户端）：
- **Jedis**：各数据类型操作、连接池、集群、哨兵
- **Lettuce**：同步/响应式/发布订阅/集群
- **RedisTemplate**：封装 `RedisUtils` 工具类 + 分布式锁（Lua 脚本释放）

#### `ElasticSearch-7`
ElasticSearch 7 Java 客户端操作：RestHighLevelClient 索引管理。

#### `MongoDB`
MongoDB 学习（3 种访问方式）：
- 原生 Java 驱动
- Spring Data MongoDB（完整 CRUD）
- MongoPlus

#### `Zookeeper`
Zookeeper 客户端操作：Apache Curator Framework 节点 CRUD、Watch 机制、分布式锁。

---

### 工具库

#### `Boot-EasyExcel`
Spring Boot 3 + EasyExcel 4 的 Excel 读写学习。

#### `Boot-MapStruct`
MapStruct 对象映射学习，与 `BeanUtils.copyProperties` 对比。

---

## 技术栈

| 类别 | 技术 |
|------|------|
| Java 核心 | JDK 8/17/21, Lambda, Stream, SPI, IO, 设计模式 |
| 并发编程 | JUC, CompletableFuture, ConcurrentHashMap, 线程池, AQS, 原子类 |
| JVM | JOL, 四大引用, GC, 类加载 |
| Web | Servlet, JSP, SpringMVC |
| Spring | Spring5 IOC/AOP/事务, Spring Boot 2/3, Spring Cloud |
| 微服务 | Eureka, Nacos, Consul, OpenFeign, Gateway, Dubbo |
| 安全 | Spring Security 5/6, Sa-Token, JWT |
| ORM | MyBatis, MyBatis-Plus, MyBatis-Flex, 动态数据源, Sharding-JDBC |
| 中间件 | Redis, ElasticSearch, MongoDB, Zookeeper |
| 数据库 | MySQL, MongoDB |
| 日志 | Log4j2, Logback |
| 工具 | MapStruct, EasyExcel, Hutool, Knife4j, Lombok, GraalVM |

## 环境要求

- **JDK**：8 / 17 / 21（各模块要求不同）
- **Maven**：3.6+
- **MySQL**：8.0+
- **Redis**：6.0+
- **其他中间件**：根据具体模块需求安装

## 使用方式

每个子目录都是独立的 Maven 项目，可单独导入 IDE 运行学习：

```bash
# 进入任意模块目录
cd 模块目录

# 编译
mvn clean compile

# 运行（Spring Boot 项目）
mvn spring-boot:run
```

## 许可证

本项目基于 [LICENSE](LICENSE) 协议开源。
