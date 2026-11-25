# GORM 教程案例

对照 [GORM 中文文档](https://gorm.io/zh_CN/docs/index.html)，使用 **SQLite**（[glebarez/sqlite](https://github.com/glebarez/sqlite) 纯 Go 驱动，Windows 无需 CGO）跑通。MySQL 见 `gorm-quickstart`。

在 `gorm` 目录下：

```bash
go mod tidy
go run ./01-connect
go run ./03-create
go run ./09-preload
```

| 目录 | 文档章节 | 要点 |
| --- | --- | --- |
| `01-connect` | [连接到数据库](https://gorm.io/zh_CN/docs/connecting_to_the_database.html) | `Open`、连接池、`Ping` |
| `02-migration` | [迁移](https://gorm.io/zh_CN/docs/migration.html) | `AutoMigrate`、软删除字段 |
| `03-create` | [创建](https://gorm.io/zh_CN/docs/create.html) | `Create`、批量、`Select`/`Omit` |
| `04-query` | [查询](https://gorm.io/zh_CN/docs/query.html) | `First`/`Find`、`Where`、`Count` |
| `05-update` | [更新](https://gorm.io/zh_CN/docs/update.html) | `Update`/`Updates`、map、`Select` |
| `06-delete` | [删除](https://gorm.io/zh_CN/docs/delete.html) | 软删、`Unscoped` |
| `07-transaction` | [事务](https://gorm.io/zh_CN/docs/transactions.html) | `Transaction`、回滚 |
| `08-hooks` | [Hook](https://gorm.io/zh_CN/docs/hooks.html) | `BeforeCreate` / `AfterCreate` |
| `09-preload` | [预加载](https://gorm.io/zh_CN/docs/preload.html) | `Has Many` + `Preload` |
| `10-session` | [会话](https://gorm.io/zh_CN/docs/session.html) | `DryRun`、`SkipHooks` |
| `11-generic-api` | [快速入门 泛型 API](https://gorm.io/zh_CN/docs/index.html) | `gorm.G[T]` |
| `12-belongs-to` | [关联](https://gorm.io/zh_CN/docs/belongs_to.html) | `Belongs To`、外键 |

## 已有 MySQL 示例

| 目录 | 说明 |
| --- | --- |
| `gorm-quickstart/gorm_init_mysql` | DSN + Logger |
| `gorm-quickstart/gorm_create` | 泛型 `Create` + `WithResult` |
| `gorm-quickstart/gorm-demo` | 泛型查询 Product |

MySQL 需本地库 `gorm_test`，账号见 `db.go` 中 DSN。