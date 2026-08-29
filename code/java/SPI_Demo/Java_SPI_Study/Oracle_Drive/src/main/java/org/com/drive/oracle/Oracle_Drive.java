package org.com.drive.oracle;

import org.com.drive.DatabaseDrive;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/20 星期四 11:44
 */
public class Oracle_Drive implements DatabaseDrive {
	@Override
	public String driveConnectionHost(String host) {
		return " Oracle实现的驱动 ---> 正在连接数据库主机 : " + host;
	}
}
