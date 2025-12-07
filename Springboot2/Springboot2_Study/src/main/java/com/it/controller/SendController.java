package com.it.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class SendController {

    @RequestMapping("/send")
    public String send() {
        String urlString = "https://httpbin.org/get";
        String key = "7e8870e4802f8307";
        String city = "北京市朝阳区";

        Map<String, Object> params = new HashMap<>();
        params.put("city", city);
        params.put("key", key);

        long startTime = System.currentTimeMillis();

        try {
            log.info("=== 使用 Hutool 发送 HTTP 请求 ===");

            // 使用 Hutool 的 HttpRequest（底层自动使用 HttpClient）
            String result = executeWithHutool(urlString, params);
            long endTime = System.currentTimeMillis();

            log.info("=== HTTP响应详情 ===");
            log.info("耗时: {}ms", (endTime - startTime));
            log.info("响应内容长度: {} 字符", result.length());
            log.info("响应内容: {}", result);

            return result;

        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            log.error("请求失败: {}", e.getMessage());
            log.error("耗时: {}ms", (endTime - startTime));
            log.error("异常堆栈:", e);
            return "请求失败: " + e.getMessage();
        }
    }

    private String executeWithHutool(String url, Map<String, Object> params) {
        // 使用 Hutool 的 HttpRequest，底层会自动使用 HttpClient
        log.info("请求URL: {}?city={}&key={}", url, params.get("city"), params.get("key"));
        log.info("使用 Hutool HttpRequest 发送请求");

        try (HttpResponse response = HttpRequest.get(url)
                .form(params) // 自动处理参数编码
                .timeout(30000) // 设置超时时间
                .execute()) {

            log.info("响应状态: {}", response.getStatus());
            String result = response.body();
            return result;
        }
    }

    // 可选：使用 Hutool 的其他方式
    @RequestMapping("/send2")
    public String send2() {
        String urlString = "https://httpbin.org/get";
        String key = "7e8870e4802f8307";
        String city = "北京市朝阳区";

        long startTime = System.currentTimeMillis();

        try {
            log.info("=== 使用 Hutool HttpUtil 发送请求 ===");

            // 方式二：使用 HttpUtil（更简洁）
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("city", city);
            paramMap.put("key", key);

            String result = cn.hutool.http.HttpUtil.get(urlString, paramMap);
            long endTime = System.currentTimeMillis();

            log.info("=== HTTP响应详情 ===");
            log.info("耗时: {}ms", (endTime - startTime));
            log.info("响应内容长度: {} 字符", result.length());

            return result;

        } catch (Exception e) {
            log.error("请求失败: {}", e.getMessage());
            return "请求失败: " + e.getMessage();
        }
    }

    // 可选：带自定义配置的 Hutool 请求
    @RequestMapping("/send3")
    public String send3() {
        String urlString = "https://httpbin.org/get";
        String key = "7e8870e4802f8307";
        String city = "北京市朝阳区";

        long startTime = System.currentTimeMillis();

        try {
            log.info("=== 使用 Hutool 自定义配置发送请求 ===");

            // 创建自定义请求
            HttpRequest request = HttpRequest.get(urlString)
                    .form("city", city)
                    .form("key", key)
                    .timeout(30000)
                    .header("User-Agent", "Hutool-HTTP-Client/1.0")
                    .header("Accept", "application/json");

            log.info("请求URL: {}", request.getUrl());

            try (HttpResponse response = request.execute()) {
                int status = response.getStatus();
                String result = response.body();

                log.info("响应状态: {}", status);
                log.info("响应头: {}", response.headers());

                long endTime = System.currentTimeMillis();
                log.info("耗时: {}ms", (endTime - startTime));

                return result;
            }

        } catch (Exception e) {
            log.error("请求失败: {}", e.getMessage());
            return "请求失败: " + e.getMessage();
        }
    }
}