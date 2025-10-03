package com.it.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/12 星期六 2:00
 */
public class JdkDynamicAssistantProxy {
	public static Object getProxyInstance(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				(proxy, method, args) -> {
					String methodName = method.getName();
					System.out.println("jdk动态代理-----[日志]:" + "[" + methodName + "]" + "开始执行, 参数->>" + Arrays.toString(args));
					System.out.println("我是老板秘书,我们老板有事晚点到,各位大佬请喝茶....");
					Object res = method.invoke(target, args);
					System.out.println("jdk动态代理-----[日志]:" + "[" + methodName + "]" + "结束执行, 参数->>" + Arrays.toString(args));
					System.out.println("我是老板秘书,现在没我事了,我先走了哈,你们开会...");

					return res;
				});
	}
}
