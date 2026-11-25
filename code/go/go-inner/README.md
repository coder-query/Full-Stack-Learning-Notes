# Go 标准库（内置包）案例

学习文档以 [Go 语言中文网标准库文档](https://studygolang.com/pkgdoc) 为主（中文版，目录与官方 `go doc` / [pkg.go.dev](https://pkg.go.dev/std) 一致），每个子目录对应一个常用标准库包的最小可运行示例。

### 原包 vs util 子包（你要的对照关系）

| 原包 / 基础能力 | util 或配套子包 | 本仓库示例 |
| --- | --- | --- |
| `io` 流接口 | `io/ioutil`（老工具，已迁到 `io`/`os`） | `08-io` → `45-io-ioutil` |
| `net/http` 客户端/服务端 | `net/http/httputil`（Dump、反向代理） | `17-net-http` → `46-net-http-httputil` |
| | `net/http/httptest`（单测用 Server） | `47-net-http-httptest` |
| | `net/http/cookiejar`（Cookie 自动管理） | `48-net-http-cookiejar` |
| | `mime/multipart`（表单上传，常配 http） | `49-mime-multipart` |

在 `go-inner` 目录下执行：

```bash
go run ./01-fmt
go run ./07-os
go run ./17-net-http
```

带命令行参数的示例：

```bash
go run ./14-flag -name=Go -verbose
```

| 目录 | 标准库包 | 中文网文档 |
| --- | --- | --- |
| `01-fmt` | `fmt` | [fmt](https://studygolang.com/pkgdoc/pkg/fmt/) |
| `02-strings` | `strings` | [strings](https://studygolang.com/pkgdoc/pkg/strings/) |
| `03-strconv` | `strconv` | [strconv](https://studygolang.com/pkgdoc/pkg/strconv/) |
| `04-bytes` | `bytes` | [bytes](https://studygolang.com/pkgdoc/pkg/bytes/) |
| `05-bufio` | `bufio` | [bufio](https://studygolang.com/pkgdoc/pkg/bufio/) |
| `06-encoding-json` | `encoding/json` | [encoding/json](https://studygolang.com/pkgdoc/pkg/encoding/json/) |
| `07-os` | `os` | [os](https://studygolang.com/pkgdoc/pkg/os/) |
| `08-io` | `io` | [io](https://studygolang.com/pkgdoc/pkg/io/) |
| `09-path-filepath` | `path/filepath` | [path/filepath](https://studygolang.com/pkgdoc/pkg/path/filepath/) |
| `10-time` | `time` | [time](https://studygolang.com/pkgdoc/pkg/time/) |
| `11-errors` | `errors` | [errors](https://studygolang.com/pkgdoc/pkg/errors/) |
| `12-sort` | `sort` | [sort](https://studygolang.com/pkgdoc/pkg/sort/) |
| `13-regexp` | `regexp` | [regexp](https://studygolang.com/pkgdoc/pkg/regexp/) |
| `14-flag` | `flag` | [flag](https://studygolang.com/pkgdoc/pkg/flag/) |
| `15-context` | `context` | [context](https://studygolang.com/pkgdoc/pkg/context/) |
| `16-sync` | `sync` | [sync](https://studygolang.com/pkgdoc/pkg/sync/) |
| `17-net-http` | `net/http` | [net/http](https://studygolang.com/pkgdoc/pkg/net/http/) |
| `18-net-url` | `net/url` | [net/url](https://studygolang.com/pkgdoc/pkg/net/url/) |
| `19-log` | `log` | [log](https://studygolang.com/pkgdoc/pkg/log/) |
| `20-crypto-sha256` | `crypto/sha256` | [crypto/sha256](https://studygolang.com/pkgdoc/pkg/crypto/sha256/) |
| `21-html-template` | `html/template` | [html/template](https://studygolang.com/pkgdoc/pkg/html/template/) |
| `22-encoding-base64` | `encoding/base64` | [encoding/base64](https://studygolang.com/pkgdoc/pkg/encoding/base64/) |

### 工具 / 算法 / 编解码 / 数据结构（`23`–`44`）

| 目录 | 标准库包 | 中文网文档 |
| --- | --- | --- |
| `23-math` | `math` | [math](https://studygolang.com/pkgdoc/pkg/math/) |
| `24-math-rand` | `math/rand/v2` | [math/rand](https://studygolang.com/pkgdoc/pkg/math/rand/) |
| `25-reflect` | `reflect` | [reflect](https://studygolang.com/pkgdoc/pkg/reflect/) |
| `26-encoding-hex` | `encoding/hex` | [encoding/hex](https://studygolang.com/pkgdoc/pkg/encoding/hex/) |
| `27-encoding-csv` | `encoding/csv` | [encoding/csv](https://studygolang.com/pkgdoc/pkg/encoding/csv/) |
| `28-encoding-xml` | `encoding/xml` | [encoding/xml](https://studygolang.com/pkgdoc/pkg/encoding/xml/) |
| `29-encoding-gob` | `encoding/gob` | [encoding/gob](https://studygolang.com/pkgdoc/pkg/encoding/gob/) |
| `30-compress-gzip` | `compress/gzip` | [compress/gzip](https://studygolang.com/pkgdoc/pkg/compress/gzip/) |
| `31-archive-zip` | `archive/zip` | [archive/zip](https://studygolang.com/pkgdoc/pkg/archive/zip/) |
| `32-crypto-rand` | `crypto/rand` | [crypto/rand](https://studygolang.com/pkgdoc/pkg/crypto/rand/) |
| `33-crypto-md5` | `crypto/md5` | [crypto/md5](https://studygolang.com/pkgdoc/pkg/crypto/md5/) |
| `34-hash-fnv` | `hash/fnv` | [hash/fnv](https://studygolang.com/pkgdoc/pkg/hash/fnv/) |
| `35-container-heap` | `container/heap` | [container/heap](https://studygolang.com/pkgdoc/pkg/container/heap/) |
| `36-slices` | `slices` | [slices](https://studygolang.com/pkgdoc/pkg/slices/) |
| `37-maps` | `maps` | [maps](https://studygolang.com/pkgdoc/pkg/maps/) |
| `38-cmp` | `cmp` | [cmp](https://studygolang.com/pkgdoc/pkg/cmp/) |
| `39-unicode-utf8` | `unicode/utf8` | [unicode/utf8](https://studygolang.com/pkgdoc/pkg/unicode/utf8/) |
| `40-runtime` | `runtime` | [runtime](https://studygolang.com/pkgdoc/pkg/runtime/) |
| `41-text-template` | `text/template` | [text/template](https://studygolang.com/pkgdoc/pkg/text/template/) |
| `42-embed` | `embed` | [embed](https://studygolang.com/pkgdoc/pkg/embed/) |
| `43-net` | `net` | [net](https://studygolang.com/pkgdoc/pkg/net/) |
| `44-sync-pool` | `sync.Pool` | [sync#Pool](https://studygolang.com/pkgdoc/pkg/sync/#Pool) |

### 原包的 util 子包（`45`–`49`）

| 目录 | 标准库包 | 对照 |
| --- | --- | --- |
| `45-io-ioutil` | `io/ioutil` | 配合 `08-io`、`07-os` |
| `46-net-http-httputil` | `net/http/httputil` | 配合 `17-net-http` |
| `47-net-http-httptest` | `net/http/httptest` | 配合 `17-net-http` |
| `48-net-http-cookiejar` | `net/http/cookiejar` | 配合 `17-net-http` |
| `49-mime-multipart` | `mime/multipart` | HTTP 上传场景 |

```bash
go run ./45-io-ioutil
go run ./46-net-http-httputil
go run ./47-net-http-httptest
```

仓库里 `02-语法进阶/io_demo`、`file_demo`、`net_*` 等也有部分重叠。`01`–`22` 偏 **I/O、网络、基础类型**；`23`–`44` 偏 **其它常用工具包**；`45`–`49` 专讲 **和 `io`、`net/http` 配套的 util 子包**。