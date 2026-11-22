package main

import (
	"net/http"
	"reflect"
	"strings"

	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
	"github.com/go-playground/locales/zh"
	ut "github.com/go-playground/universal-translator"
	"github.com/go-playground/validator/v10"
	zh_translations "github.com/go-playground/validator/v10/translations/zh"
)

// trans 全局翻译器，把校验错误翻译成中文
var trans ut.Translator

// InitTrans 初始化校验错误的中文翻译器
func InitTrans() error {
	// 拿到 gin 内置的校验器（底层就是 go-playground/validator）
	v, ok := binding.Validator.Engine().(*validator.Validate)
	if !ok {
		return nil
	}

	// 让错误提示里显示 json 标签名（如 username），而不是字段名（如 Username）
	// 原理：取 json 标签的第一个值（去掉 ",omitempty" 之类的后缀）
	v.RegisterTagNameFunc(func(fld reflect.StructField) string {
		name, _, _ := strings.Cut(fld.Tag.Get("json"), ",")
		if name == "-" { // "-" 表示该字段不序列化，返回空串走默认字段名
			return ""
		}
		return name
	})

	// 创建中文翻译器
	zhT := zh.New()
	uni := ut.New(zhT, zhT)
	trans, _ = uni.GetTranslator("zh")

	// 注册 validator 默认规则的中文翻译
	return zh_translations.RegisterDefaultTranslations(v, trans)
}

// LoginParam 登录参数
type LoginParam struct {
	Username string `json:"username" binding:"required"`              // 必填
	Password string `json:"password" binding:"required,min=6,max=20"` // 必填，长度 6~20
}

// RegisterParam 注册参数
type RegisterParam struct {
	Username        string `json:"username" binding:"required,min=3,max=20"`             // 必填，长度 3~20
	Password        string `json:"password" binding:"required,min=6,max=20"`             // 必填，长度 6~20
	ConfirmPassword string `json:"confirm_password" binding:"required,eqfield=Password"` // 必填，必须和 Password 一致
	Email           string `json:"email" binding:"required,email"`                       // 必填，邮箱格式
	Phone           string `json:"phone" binding:"required,len=11"`                      // 必填，11 位手机号
	Age             int    `json:"age" binding:"required,gte=1,lte=120"`                 // 必填，1~120 岁
}

// firstValidErr 从校验错误中提取第一条中文提示
func firstValidErr(err error) string {
	// 类型断言成 validator 的错误类型，才能调用 Translate 翻译
	if errs, ok := err.(validator.ValidationErrors); ok {
		for _, e := range errs {
			return e.Translate(trans)
		}
	}
	// 非校验错误（比如 JSON 格式非法），返回统一提示
	return "参数格式错误"
}

// login POST /login
func login(c *gin.Context) {
	var p LoginParam
	// ShouldBindJSON 绑定 JSON 参数，并自动按 binding 标签校验
	if err := c.ShouldBindJSON(&p); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"code": http.StatusBadRequest, "msg": firstValidErr(err)})
		return
	}
	c.JSON(http.StatusOK, gin.H{
		"code": http.StatusOK,
		"msg":  "登录成功",
		"data": gin.H{"username": p.Username},
	})
}

// register POST /register
func register(c *gin.Context) {
	var p RegisterParam
	if err := c.ShouldBindJSON(&p); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"code": http.StatusBadRequest, "msg": firstValidErr(err)})
		return
	}
	// 校验通过后才会走到这里，可以放心使用 p 里的字段
	c.JSON(http.StatusOK, gin.H{
		"code": http.StatusOK,
		"msg":  "注册成功",
		"data": gin.H{"username": p.Username, "email": p.Email},
	})
}

func main() {
	// 初始化中文翻译器
	if err := InitTrans(); err != nil {
		panic("初始化翻译器失败: " + err.Error())
	}

	r := gin.Default()
	r.POST("/login", login)
	r.POST("/register", register)
	_ = r.Run(":7656")
}
