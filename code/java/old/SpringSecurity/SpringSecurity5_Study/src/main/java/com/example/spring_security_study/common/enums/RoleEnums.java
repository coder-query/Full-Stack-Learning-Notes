package com.example.spring_security_study.common.enums;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/12 0012
 */
public enum RoleEnums {

    // 定义枚举值
    ADMIN(1, "管理员"),
    NORMAL(0, "普通用户");

    // 定义属性
    private final int code;
    private final String desc;

    // 构造函数
    RoleEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // getCode()
    public int getCode() {
        return code;
    }

    // getDesc()
    public String getDesc() {
        return desc;
    }

    // 根据code获取枚举常量
    public static RoleEnums getValue(int code) {
        for (RoleEnums roleEnum : values()) {
            if (roleEnum.getCode() == code) {
                return roleEnum;
            }
        }
        return null;
    }
}
