package com;

import com.it.proxy.JdkDynamicAssistantProxy;
import com.it.proxy.StaticAssistantProxy;
import com.it.service.Boss;
import com.it.service.Impl.BossImpl;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/12 星期六 1:49
 */
public class AOP_Test {
	public static void main(String[] args) {
		/**
		 * 静态代理测试
		 */
		StaticAssistantProxy staticAssistantProxy = new StaticAssistantProxy(new BossImpl());
		staticAssistantProxy.startMeeting();

		System.out.println("================================================================================");

		/**
		 * JDK动态代理测试
		 */
		Boss proxyInstance = (Boss) JdkDynamicAssistantProxy.getProxyInstance(new BossImpl());
		proxyInstance.startMeeting();

	}
}