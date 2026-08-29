package org.com.drive.mysql;

import org.com.drive.DatabaseDrive;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/20 星期四 11:32
 */
public class MySQL_Drive implements DatabaseDrive {
	@Override
	public String driveConnectionHost(String host) {
		return " MySQL实现的驱动 ---> 正在连接数据库主机 : " + host;
	}
}
