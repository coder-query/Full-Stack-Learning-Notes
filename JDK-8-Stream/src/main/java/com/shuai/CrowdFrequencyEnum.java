/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.shuai;

/**
 * 用户频次
 */
public enum CrowdFrequencyEnum {

    DAILY(1, "每天"),
    ONE(2, "1次"),
    HOUR(3, "每小时"),
    ;

    private int code;

    private String desc;

    CrowdFrequencyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
