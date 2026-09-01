# Full-Stack-Learning-Notes 全栈学习仓库

个人全栈技术学习仓库，采用 **「代码 + 笔记」** 双目录组织方式：`code` 存放可运行的学习代码，`notes` 存放对应的学习笔记。

## 目录结构总览

```text
Full-Stack-Learning-Notes
├── code/       # 学习代码库（前端 + Java 后端）
│   ├── frontend/   # 前端代码
│   └── java/       # Java 后端代码
├── notes/      # 学习笔记（规划中，逐步补充）
│   └── java/       # Java 方向笔记目录
└── LICENSE
```

---

## 📁 code —— 学习代码库

存放各类技术的**可运行示例代码**，每个子目录都是独立项目，可单独导入 IDE 运行学习。

### `code/frontend/` —— 前端代码

> 前端知识体系主要跟随 Codewhy 老师的课程搭建，详见 [code/frontend/readme.md](code/frontend/readme.md)。

| 目录 | 内容 |
|------|------|
| `vue/` | Vue 全系列：基础 → 组件 → 组合式 API → Router → Vuex → Pinia → Axios → 进阶/响应式（01~11 渐进式编号） |
| `react/` | React 全系列：基础 → 脚手架 → 组件 → CSS → Redux → React-Redux → Redux Toolkit → Router → Hooks（01~09 渐进式编号） |
| `nodejs/` | Node 基础：Path/fs/Stream/events/Buffer/http 模块（01~07） |
| `nodejs 服务端框架/` | Express、Koa 服务端框架学习 |
| `打包工具/` | Webpack、Rollup、Vite 构建工具学习 |
| `模块化/` | 前端模块化开发（CommonJS/ES Module 等） |

### `code/java/` —— Java 后端代码

Java 后端全栈技术学习代码集合，涵盖 Java 核心、并发编程、JVM、Web 开发、Spring 生态、ORM 框架、中间件、微服务等，每个模块均为独立的 Maven 项目。

- 完整模块清单与详解见 👉 [code/README.md](code/README.md)
- 历史模块统一存放在 `code/java/old/` 下

**技术栈速览：**

| 类别 | 技术 |
|------|------|
| Java 核心 | JDK 8/17/21, Lambda, Stream, SPI, IO, 设计模式 |
| 并发编程 | JUC, CompletableFuture, ConcurrentHashMap, 线程池, AQS |
| Spring 生态 | Spring5 IOC/AOP/事务, Spring Boot 2/3, Spring Cloud, Spring Security |
| ORM | MyBatis, MyBatis-Plus, MyBatis-Flex, 动态数据源, Sharding-JDBC |
| 中间件 | Redis, ElasticSearch, MongoDB, Zookeeper, RabbitMQ |

**环境要求：** JDK 8/17/21（各模块不同）、Maven 3.6+、MySQL 8.0+、Redis 6.0+

---

## 📝 notes —— 学习笔记

存放与 `code` 中代码配套的**文字版学习笔记**，用于沉淀知识点、整理原理分析与踩坑记录。

| 目录 | 说明 |
|------|------|
| `notes/java/` | Java 后端方向笔记（与 `code/java/` 对应，整理中） |
| `notes/前端/` | 前端方向笔记（与 `code/frontend/` 对应，规划中） |

> ⚠️ 当前状态：笔记目录处于**初始规划阶段**，内容将随学习进度持续补充，与代码目录保持一一对应关系。

---

## 使用方式

```bash
# 运行 Java 模块（以任意 Maven 模块为例）
cd code/java/old/模块目录
mvn clean compile       # 编译
mvn spring-boot:run     # 运行 Spring Boot 项目

# 前端代码
cd code/frontend/vue/01_Learn_Vue_Basic
npm install && npm run dev
```

## 许可证

本项目基于 [LICENSE](LICENSE) 协议开源。
