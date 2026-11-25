# Go 标准库导读（对齐 [studygolang pkgdoc](https://studygolang.com/pkgdoc)）

pkgdoc 页面按 **官方 `go list std` 目录** 组织中文版说明，与 [pkg.go.dev/std](https://pkg.go.dev/std) 一一对应。下面按**日常开发常用度**整理，并标明本仓库 `go-inner` 是否已有示例（编号目录）。

**图例**：✅ 已有示例 · ⬜ 建议补看官方文档 · 🔧 internal 一般不直接 import

---

## 一、文本 / 格式化 / 命令行

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `fmt` | 格式化输入输出 | ✅ `01-fmt` |
| `strings` | UTF-8 字符串处理 | ✅ `02-strings` |
| `bytes` | `[]byte` 处理 | ✅ `04-bytes` |
| `strconv` | 字符串 ↔ 数字/布尔 | ✅ `03-strconv` |
| `unicode` / `unicode/utf8` | Unicode / UTF-8 | ✅ `39-unicode-utf8` |
| `regexp` | 正则 | ✅ `13-regexp` |
| `flag` | 命令行参数 | ✅ `14-flag` |
| `text/template` | 文本模板 | ✅ `41-text-template` |
| `html` | HTML 转义 | ✅ `59-html` |
| `html/template` | 安全 HTML 模板 | ✅ `21-html-template` |
| `text/scanner` / `text/tabwriter` | 词法扫描 / 列对齐输出 | ⬜ |

---

## 二、I/O 与文件系统（io → util → os）

| 包 | 关系 | 本仓库 |
| --- | --- | --- |
| `io` | Reader/Writer/COPY 等核心接口 | ✅ `08-io` |
| `io/ioutil` | 旧版便捷函数（已迁 `io`/`os`） | ✅ `45-io-ioutil` |
| `io/fs` | **虚拟文件系统**接口（WalkDir、Sub、Glob） | ✅ `50-io-fs` |
| `bufio` | 带缓冲的读写 | ✅ `05-bufio` |
| `os` | 文件、环境变量、进程 | ✅ `07-os` |
| `os/exec` | **执行外部命令** | ✅ `51-os-exec` |
| `os/signal` | **信号**（优雅退出） | ✅ `52-os-signal` |
| `path` | 斜杠路径（URL 风格） | ✅ `65-path` |
| `path/filepath` | OS 文件路径 | ✅ `09-path-filepath` |
| `embed` | 编译期嵌入文件 | ✅ `42-embed` |

---

## 三、网络（net 族）

| 包 | 关系 | 本仓库 |
| --- | --- | --- |
| `net` | IP、监听、解析 | ✅ `43-net` |
| `net/url` | URL 解析 | ✅ `18-net-url` |
| `net/netip` | **新 IP 类型**（Addr、Prefix） | ✅ `66-net-netip` |
| `net/http` | HTTP 客户端/服务端 | ✅ `17-net-http` |
| `net/http/httputil` | Dump、反向代理 | ✅ `46-net-http-httputil` |
| `net/http/httptest` | 测试 HTTP | ✅ `47-net-http-httptest` |
| `net/http/cookiejar` | Cookie | ✅ `48-net-http-cookiejar` |
| `net/http/httptrace` | 请求链路跟踪 | ✅ `64-net-http-httptrace` |
| `net/http/pprof` | HTTP 暴露性能剖析 | ⬜（见 `runtime/pprof`） |
| `mime` | MIME 类型 | ✅ `60-mime` |
| `mime/multipart` | 表单上传 | ✅ `49-mime-multipart` |
| `net/rpc` | 早期 RPC | ✅ `62-net-rpc` |
| `crypto/tls` | TLS（配 `net/http`） | ✅ `63-crypto-tls` |

---

## 四、编解码 encoding/*

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `encoding/json` | JSON | ✅ `06-encoding-json` |
| `encoding/xml` | XML | ✅ `28-encoding-xml` |
| `encoding/csv` | CSV | ✅ `27-encoding-csv` |
| `encoding/gob` | Go 二进制 | ✅ `29-encoding-gob` |
| `encoding/hex` | 十六进制 | ✅ `26-encoding-hex` |
| `encoding/base64` | Base64 | ✅ `22-encoding-base64` |
| `encoding/binary` | **大端/小端、binary.Write** | ✅ `54-encoding-binary` |
| `encoding/pem` | PEM 证书块 | ⬜ |

---

## 五、压缩与归档

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `compress/gzip` | gzip | ✅ `30-compress-gzip` |
| `archive/zip` | zip | ✅ `31-archive-zip` |
| `archive/tar` | tar | ✅ `61-archive-tar` |
| `compress/zlib` / `flate` | zlib、deflate | ⬜ |

---

## 六、并发 / 上下文 / 同步

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `context` | 取消、超时、传值 | ✅ `15-context` |
| `sync` | Mutex、WaitGroup、Once、**Pool** | ✅ `16-sync`、`44-sync-pool` |
| `sync/atomic` | 原子类型 | ✅ `57-sync-atomic` |
| `iter` | 迭代器（Go 1.23+） | ⬜ |

---

## 七、错误 / 排序 / 集合工具

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `errors` | errors.Is / As / Join | ✅ `11-errors` |
| `sort` | 排序 | ✅ `12-sort` |
| `slices` | 切片工具 | ✅ `36-slices` |
| `maps` | map 工具 | ✅ `37-maps` |
| `cmp` | 比较 | ✅ `38-cmp` |
| `container/heap` | 堆 / 优先队列 | ✅ `35-container-heap` |
| `container/list` | 双向链表 | ✅ `58-container-list` |

---

## 八、时间 / 日志 / 运行时

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `time` | 时间 | ✅ `10-time` |
| `log` | 传统日志 | ✅ `19-log` |
| `log/slog` | **结构化日志** | ✅ `53-log-slog` |
| `runtime` | 内存、GC、版本 | ✅ `40-runtime` |
| `expvar` | HTTP 暴露指标变量 | ✅ `67-expvar` |

---

## 九、数学 / 随机 / 反射

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `math` | 数学函数 | ✅ `23-math` |
| `math/rand/v2` | 伪随机 | ✅ `24-math-rand` |
| `math/big` | 大整数 / 高精度 | ✅ `55-math-big` |
| `math/bits` | 位运算 | ⬜ |
| `reflect` | 反射 | ✅ `25-reflect` |

---

## 十、密码学 crypto/*

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `crypto/rand` | 安全随机 | ✅ `32-crypto-rand` |
| `crypto/sha256` | SHA-256 | ✅ `20-crypto-sha256` |
| `crypto/md5` | MD5（校验用） | ✅ `33-crypto-md5` |
| `crypto/hmac` | HMAC 签名校验 | ✅ `56-crypto-hmac` |
| `crypto/tls` | TLS 配置 | ✅ `63-crypto-tls` |
| `hash/fnv` | 非加密哈希 | ✅ `34-hash-fnv` |
| `hash/crc32` | CRC32 | ✅ `68-hash-crc32` |

---

## 十一、数据库 / 工具链 go/*

| 包 | 用途 | 本仓库 |
| --- | --- | --- |
| `database/sql` | SQL 通用接口（**需三方驱动**） | ✅ `69-database-sql` |
| `go/ast` `go/parser` `go/format` | 解析/格式化 Go 源码 | ⬜ 进阶 |
| `testing` | 单元测试 | ⬜ 放 `*_test.go` 更合适 |

---

## 十二、建议学习顺序（常用）

1. `fmt` `strings` `strconv` `errors` `time`
2. `io` `bufio` `os` `path/filepath` `encoding/json`
3. `context` `sync` `net/http`
4. `flag` `log/slog` `database/sql`（加驱动后实战）
5. 按需：`reflect` `regexp` `crypto/*` `archive/*` `go/*`

完整包列表本地可执行：`go list std`。