package com.org.zsh.thymeleafcode.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    private Response() {}

    private Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success() {
        return new Response<>(200, "success", null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(200, "success", data);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(200, message, data);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(500, message, null);
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }

    public static <T> Response<T> unauthorized(String message) {
        return new Response<>(401, message, null);
    }

    public static <T> Response<T> forbidden(String message) {
        return new Response<>(403, message, null);
    }

    public static <T> Response<PageResult<T>> page(List<T> list, long total, int pageNo, int pageSize) {
        PageResult<T> pageResult = new PageResult<>(total, pageNo, pageSize, list);
        return new Response<>(200, "success", pageResult);
    }

}
