# Colly 使用案例

依据 [Colly 文档](https://go-colly.org/docs/) 整理，对应本目录 `go.mod` 里的 `github.com/gocolly/colly v1.2.0`。

在 `go-colly` 目录下运行：

```bash
go run ./01-basic
go run ./02-callbacks
go run ./03-configuration
go run ./04-error-handling
go run ./05-max-depth
go run ./06-url-filter
go run ./07-request-context
go run ./08-rate-limit
go run ./09-parallel
go run ./10-queue
go run ./11-structured-data
go run ./12-form-post
go run ./13-xpath
```

| 目录 | 对应文档 | 演示内容 |
| --- | --- | --- |
| `01-basic` | Getting started / basic | `NewCollector`、`OnHTML`、`OnRequest`、`Visit` |
| `02-callbacks` | Getting started | 回调执行顺序：Request → Response → HTML → XML → Scraped |
| `03-configuration` | Configuration | `AllowedDomains`、`UserAgent`、请求头、自定义 `Transport` |
| `04-error-handling` | error handling | `OnError` |
| `05-max-depth` | max depth | `MaxDepth`，只跟一层链接 |
| `06-url-filter` | url filter | `URLFilters` 正则过滤 |
| `07-request-context` | request context | `Request.Ctx` 在请求和响应之间传值 |
| `08-rate-limit` | rate limit / random delay | `Async`、`Limit`、`Delay`、`RandomDelay`、`Wait` |
| `09-parallel` | parallel | 异步抓取并限制并发 |
| `10-queue` | queue | `queue.New` 消费队列 |
| `11-structured-data` | OnHTML / coursera 示例思路 | 用选择器抽出结构化字段并翻页 |
| `12-form-post` | login 示例的 `Post` | 向 httpbin 提交表单并读回响应 |
| `13-xpath` | Getting started 的 `OnXML` | HTML / XML 上用 XPath：`ChildText`、`Attr`、`//note` 等 |

`first-demo` 是原有示例，未改动。

**OnHTML（CSS）与 OnXML（XPath）**：`01-basic`、`11-structured-data` 走 CSS；`02-callbacks` 仅演示回调里的 `//title`；完整 XPath 见 `13-xpath`。

当前 v1.2.0 没有文档里较新的 `OnResponseHeaders`。回调顺序以本版本实际提供的方法为准。
