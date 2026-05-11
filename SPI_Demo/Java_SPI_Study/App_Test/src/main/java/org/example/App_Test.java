package org.example;

import org.com.drive.DatabaseDrive;

import java.util.ServiceLoader;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/20 星期四 11:34
 */
public class App_Test {
	public static void main(String[] args) {
		ServiceLoader<DatabaseDrive> loader = ServiceLoader.load(DatabaseDrive.class);
		for (DatabaseDrive drive : loader) {
			System.out.println(drive.driveConnectionHost("192.168.211.166"));
		}
	}
}