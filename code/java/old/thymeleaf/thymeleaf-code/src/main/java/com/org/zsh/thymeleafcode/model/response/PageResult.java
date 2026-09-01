package com.org.zsh.thymeleafcode.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 当前页码 */
    private int pageNo;

    /** 每页条数 */
    private int pageSize;

    /** 数据列表 */
    private List<T> list;

    public PageResult() {}

    public PageResult(long total, int pageNo, int pageSize, List<T> list) {
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.list = list;
    }

    public static <T> PageResult<T> page(long total, int pageNo, int pageSize, List<T> list) {
        return new PageResult<>(total, pageNo, pageSize, list);
    }
}
