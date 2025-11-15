    package com.service;


    import com.util.ThreadLocalUtil;
    import com.util.TransmittableThreadUtil;
    import org.springframework.stereotype.Service;

    import javax.annotation.Resource;
    import java.util.concurrent.CompletableFuture;
    import java.util.concurrent.ExecutionException;

    @Service
    public class CompletableFutureService {
    //    @Resource
    //    private JobService1 jobService1;

        @Resource
        private JobService2 jobService2;

        @Resource
        private
        AsyncJobPoolService asyncJobPoolService;


        public String testCompletableFuture() {

//            ThreadLocalUtil.set("userInfo用户信息111");
//            TransmittableThreadUtil.set("userInfo用户信息111");
//            String runJobResult1 = asyncJobPoolService.runJob();
//            System.out.println("请注意，现在代码已经执行到这里了哦，看一下上面的任务有没有返回值哦---runJobResult1 ： "+runJobResult1);
//
//            ThreadLocalUtil.set("userInfo用户信息222");
//            TransmittableThreadUtil.set("userInfo用户信息222");
//            String runJobResult2 = asyncJobPoolService.runJob();
//            System.out.println("请注意，现在代码已经执行到这里了哦，看一下上面的任务有没有返回值哦---runJobResult2 ： "+runJobResult2);
//
//            System.out.println("我是CompletableFutureService服务层。。。。，现在在等上面任务执行完毕，我这里就开始打印了哦");
//            for (int i = 1; i <= 20; i++) {
//                TransmittableThreadUtil.set("userInfo用户信息" + i);
//                String result = asyncJobPoolService.runJob();
//                System.out.println("提交任务" + i + "，返回值: " + result);
//            }
//            System.out.println("所有任务提交完成");
//


//            return runJobResult1 + "   " + runJobResult2;
            CompletableFuture<String> future = jobService2.runJob();
            try {
                return future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
