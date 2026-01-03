package com.it.proxy;

import com.it.service.Boss;
import com.it.service.Impl.BossImpl;

import java.util.Arrays;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/12 星期六 1:51
 */
public class StaticAssistantProxy implements Boss {
	private Boss target;

	public StaticAssistantProxy(BossImpl boosImpl) {
		target = boosImpl;
	}

	@Override
	public void startMeeting() {
		System.out.println("静态代理-----[日志]");
		System.out.println("我是老板秘书,我们老板有事晚点到,各位大佬请喝茶....");
		target.startMeeting();
		System.out.println("静态代理-----[日志]");
		System.out.println("我是老板秘书,现在没我事了,我先走了哈,你们开会...");
	}

}
