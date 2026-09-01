package com.it.service.Impl;

import com.it.service.Boss;
import org.springframework.stereotype.Service;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/12 星期六 1:52
 */
@Service
public class BossImpl implements Boss {
	@Override
	public void startMeeting() {
		System.out.println("我是boss老板,我来了,各位久等了,开始会议...");
	}
}
