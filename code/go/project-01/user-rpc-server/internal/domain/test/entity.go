package test

type Test struct {
	ID   int64  `gorm:"column:id;type:bigint;not null;comment:主键id(自增)"`
	Name string `gorm:"column:name;type:varchar(64);not null;comment:用户名"`
}
