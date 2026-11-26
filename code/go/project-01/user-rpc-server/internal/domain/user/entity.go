package user

import (
	"project-01/user-rpc-server/internal/domain"
)

// User 用户表实体
type User struct {
	// 公共字段：ID、创建时间、更新时间、删除标志
	domain.BaseModel
	// 用户名
	Username string `gorm:"column:username;type:varchar(64);not null;uniqueIndex;comment:用户名"`
	// 密码(存放加密后的密文)
	Password string `gorm:"column:password;type:varchar(128);not null;comment:密码(md5密文)"`
	// 昵称
	Nickname string `gorm:"column:nickname;type:varchar(64);not null;default:'';comment:昵称"`
	// 手机号
	Mobile string `gorm:"column:mobile;type:varchar(20);not null;default:'';index;comment:手机号"`
	// 邮箱
	Email string `gorm:"column:email;type:varchar(128);not null;default:'';index;comment:邮箱"`
	// 头像地址
	Avatar string `gorm:"column:avatar;type:varchar(255);not null;default:'';comment:头像地址"`
	// 用户状态
	Status int8 `gorm:"column:status;type:tinyint;not null;default:1;comment:用户状态(0:禁用,1:正常)"`
	// 角色
	Role int8 `gorm:"column:role;type:tinyint;not null;default:0;comment:角色(0:普通用户,1:管理员)"`
}
