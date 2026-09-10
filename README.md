# Full-Stack-Learning-Notes 全栈学习仓库

## 📌 Git 提交信息规范（Conventional Commits）

本仓库提交代码时，请在提交信息前加类型前缀，格式：`<type>: <描述>`（也可写作 `【<type>: 描述】`）。

| 类型 | 说明 |
|------|------|
| `feat` | 新增功能（feature） |
| `fix` | 修复 Bug |
| `docs` | 只改动文档（笔记、README 等） |
| `style` | 代码格式调整（不影响逻辑：空格、缩进、分号等） |
| `refactor` | 重构（既不是新增功能，也不是修复 Bug） |
| `perf` | 性能优化 |
| `test` | 新增 / 修改测试 |
| `build` | 构建系统或外部依赖变更（webpack、vite、maven 依赖等） |
| `ci` | CI 配置文件、脚本变更 |
| `chore` | 其他杂项（不修改 src 或测试代码的改动） |
| `revert` | 回滚某次提交 |

**示例：**

```bash
git commit -m "feat: 新增Vue Router学习模块"
git commit -m "fix: 修复MyBatis批量插入空指针问题"
git commit -m "docs: 更新后端Linux笔记"
```

---

## 项目简介

个人全栈技术学习仓库，采用 **「代码 + 笔记」** 双目录组织方式：`code/` 存放可运行的学习代码，`notes/` 存放对应的学习笔记。

## 目录结构总览

```text
Full-Stack-Learning-Notes
├── code/           # 学习代码库（前端 + Java 后端 + Go）
│   ├── frontend/   # 前端代码
│   ├── java/       # Java 后端代码
│   └── go/         # Go 语言代码（规划中）
├── notes/          # 学习笔记
│   ├── java/       # Java 方向笔记（整理中）
│   ├── frontend/   # 前端方向笔记（整理中）
│   ├── go/         # Go 方向笔记（整理中）
│   └── linux/      # Linux 运维笔记
└── LICENSE
```

---

## 📁 code —— 学习代码库

存放各类技术的**可运行示例代码**，每个子目录都是独立项目，可单独导入 IDE 运行学习。

### `code/frontend/` —— 前端代码

> 前端知识体系主要跟随 Codewhy 老师的课程搭建，详见 [code/frontend/readme.md](code/frontend/readme.md)。

| 目录 | 内容 |
|------|------|
| `vue/` | Vue 全系列：基础 → 项目实战 → 组件 → 组合式 API → 房源 demo → Router → Vuex → Pinia → Axios → 进阶/响应式（01~11 渐进式编号） |
| `react/` | React 全系列：基础 → 脚手架 → 组件 → CSS → Redux → React-Redux → Redux Toolkit → Router → Hooks（01~09） |
| `ts/` | TypeScript 全系列：语法 → 数据类型 → 函数类型 → 面向对象 → 泛型 → webpack/axios 封装 → 类型体操（01~10） |
| `nodejs/` | Node 基础：Path/fs/Stream/events/Buffer/http 模块（01~07） |
| `nodejs 服务端框架/` | Express、Koa、MySQL 驱动等服务端开发 |
| `打包工具/` | Webpack、Rollup、Vite 构建工具学习 |
| `模块化/` | 前端模块化开发（CommonJS/ES Module 等） |

### `code/java/` —— Java 后端代码

Java 后端全栈技术学习代码集合，涵盖 Java 核心、并发编程、JVM、Web 开发、Spring 生态、ORM 框架、中间件、微服务等，每个模块均为独立的 Maven 项目，历史模块统一存放在 `code/java/old/` 下（40+ 个模块）。

**技术栈速览：**

| 类别 | 技术 |
|------|------|
| Java 核心 | JDK 8 新特性, Stream, SPI, IO 流, 设计模式 |
| 并发编程 | JUC, CompletableFuture, ConcurrentHashMap(1.7/1.8) |
| JVM | JVM 虚拟机、本地 JVM 缓存 |
| Spring 生态 | Spring IOC/AOP, SpringMVC, Spring Boot 2/3, Spring Cloud, Spring Security, Sa-Token, 自定义 Starter |
| ORM | MyBatis, MyBatis-Plus, MyBatis-Flex, 动态数据源, Sharding-JDBC, MapStruct |
| 中间件 | Redis, RabbitMQ, ElasticSearch-7, MongoDB, Zookeeper, Dubbo |
| 其他 | Shiro, Servlet/Javaweb, thymeleaf, 定时任务, Excel 处理 |

**环境要求：** JDK 8/17/21（各模块不同）、Maven 3.6+、MySQL 8.0+、Redis 6.0+

### `code/go/` —— Go 代码

Go 语言学习代码（规划中，将逐步补充）。

---

## 📝 notes —— 学习笔记

存放与 `code/` 中代码配套的**文字版学习笔记**（Typora 格式，含截图），用于沉淀知识点、整理原理分析与踩坑记录。

| 目录 | 说明 |
|------|------|
| `notes/linux/` | Linux 运维笔记：安装与远程连接 → 目录结构与核心命令 → 配置文件 → 文件属性 → 用户/权限/软件/进程管理 → 三剑客（01~13 章节式编号） |
| `notes/java/` | Java 后端方向笔记（与 `code/java/` 对应，整理中） |
| `notes/frontend/` | 前端方向笔记（与 `code/frontend/` 对应，整理中） |
| `notes/go/` | Go 方向笔记（与 `code/go/` 对应，整理中） |

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
