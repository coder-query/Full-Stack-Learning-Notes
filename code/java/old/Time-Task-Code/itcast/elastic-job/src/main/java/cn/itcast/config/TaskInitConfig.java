package cn.itcast.config;

import cn.itcast.task.MyDataFlowJob;
import cn.itcast.task.MySimpleJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskInitConfig {

    @Bean
    public MySimpleJob task() {
        return new MySimpleJob();
    }

    @Bean
    public MyDataFlowJob task1() {
        return new MyDataFlowJob();
    }
}
