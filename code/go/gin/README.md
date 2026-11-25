# Gin 教程代码

对照 [Gin 中文文档](https://gin-gonic.com/zh-cn/docs/)（框架版本 `github.com/gin-gonic/gin v1.12.0`）。在 `gin` 目录下运行，每个示例是独立进程，端口互不冲突。

```bash
go run ./gin-start
go run ./gin-bind
go run ./gin-render
```

| 目录 | 文档主题 | 端口 |
| --- | --- | --- |
| `gin-start` | 快速开始：`Default`、`GET`、`JSON` | 7678 |
| `gin-params` | 路径参数 `c.Param` | 7675 |
| `gin-query` | 查询参数 `c.Query` | 7677 |
| `gin-form-data` | `PostForm` | 7675 |
| `gin-group` | 路由分组与 REST 动词 | 7676 |
| `gin-json-response` | 统一 JSON 响应结构 | 5654 |
| `gin-validator` | `ShouldBindJSON` + `binding` 校验与中文翻译 | 7656 |
| `gin-middleware` | 全局中间件、`Next` 前后 | 7657 |
| `gin-midwiress` | 只挂在路由组上的中间件 | 7658 |
| `gin-bind` | JSON / Query / Uri / Header / Form 绑定 | 7680 |
| `gin-upload` | 单文件与多文件上传 | 7681 |
| `gin-render` | XML、YAML、JSONP、AsciiJSON、PureJSON、SecureJSON | 7682 |
| `gin-html` | HTML 模板与静态内容 | 7683 |
| `gin-cookie` | Cookie、重定向、`NoRoute` | 7684 |
| `gin-basic-auth` | `gin.BasicAuth` | 7685 |
| `gin-goroutine` | handler 里开 goroutine 必须 `c.Copy()` | 7686 |
| `gin-graceful` | `http.Server` + 信号优雅退出 | 7687 |
| `gin-protobuf-response` | Protobuf 响应 | 见该目录源码 |

`gin-form-data` 与 `gin-params` 都监听 `7675`，不要同时启动。
