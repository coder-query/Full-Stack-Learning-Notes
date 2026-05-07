package org.shuai.boot.shirocodestudy.listener;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
@Slf4j
public class DatabaseConnectionListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        log.info("开始检查数据库连接...");

        try (Connection connection = dataSource.getConnection()) {

            // 执行简单查询验证连接
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                if (resultSet.next()) {
                    log.info("✅ 数据库连接正常");
                }
            }

            // 获取数据库信息
            DatabaseMetaData metaData = connection.getMetaData();
            log.info("数据库产品: {}", metaData.getDatabaseProductName());
            log.info("数据库版本: {}", metaData.getDatabaseProductVersion());
            log.info("连接URL: {}", metaData.getURL());

        } catch (SQLException e) {
            log.error("❌ 数据库连接失败: {}", e.getMessage());
            // 可以选择抛出异常终止启动
            throw new RuntimeException("数据库连接失败，应用启动终止", e);
        }
    }
}
