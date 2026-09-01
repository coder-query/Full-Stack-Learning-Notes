package com.it.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

	@RequestMapping("/user/getInfo")
	public String getUserByName(HttpServletRequest httpServletRequest) {
		String method = httpServletRequest.getMethod();
		System.out.println("method = " + method);

		String remoteAddr = httpServletRequest.getRemoteAddr();
		System.out.println("remoteAddr = " + remoteAddr);

		// 获取真实客户端IP（优先级从高到低）
		String realClientIP = getClientRealIp(httpServletRequest);
		System.out.println("真实客户端IP: " + realClientIP);

		String realIP = httpServletRequest.getHeader("X-Real-IP");
		System.out.println("X-Real-IP = " + realIP);

		String forwardedIP = httpServletRequest.getHeader("X-Forwarded-For");
		System.out.println("X-Forwarded-For = " + forwardedIP);

		String forwardedProto = httpServletRequest.getHeader("X-Forwarded-Proto");
		System.out.println("X-Forwarded-Proto = " + forwardedProto);

		Map<String, String> ipInfo = new LinkedHashMap<>();
		ipInfo.put("RemoteAddr", remoteAddr);
		ipInfo.put("X-Real-IP", realIP);
		ipInfo.put("X-Forwarded-For", forwardedIP);
		ipInfo.put("RealClientIP", realClientIP);

		return "Nginx 反向代理成功! IP信息: " + ipInfo.toString();
	}

	/**
	 * 获取客户端真实IP
	 */
	private String getClientRealIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个IP才是真实IP
			if (ip.contains(",")) {
				ip = ip.split(",")[0].trim();
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}