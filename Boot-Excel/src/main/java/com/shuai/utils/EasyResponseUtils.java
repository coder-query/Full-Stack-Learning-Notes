package com.shuai.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ContentDisposition;

import java.nio.charset.StandardCharsets;

public class EasyResponseUtils {

    /**
     * 构建响应流的header设置
     */
    public static void buildResponseHeader(HttpServletResponse response, String fileName) {

        ContentDisposition contentDisposition = ContentDisposition
                .attachment()
                .filename(fileName, StandardCharsets.UTF_8).build();
        // 设置响应头
        response.setHeader("Content-Disposition", contentDisposition.toString());
    }
}
