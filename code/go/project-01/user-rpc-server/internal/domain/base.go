package domain

import "time"

type BaseModel struct {
	// 主键id
	ID int64 `gorm:"primaryKey;autoIncrement;column:id;type:bigint;not null comment: '主键id(自增)'"`
	// 创建时间
	CreatedTime *time.Time `gorm:"column:created_time;type:datetime;not null comment: '创建时间'"`
	// 更新时间
	UpdateATime *time.Time `gorm:"column:update_time;type:datetime;not null comment: '更新时间'"`
	// 删除标志
	IsDelete int8 `gorm:"column:is_delete;type:tinyint;not null comment: '删除标志(0:未删除,1:已删除)'"`
}
