package com.job.fail;

// 失败任务记录类
public class FailedJob {
        private final Runnable job;
        private final Throwable throwable;

        public FailedJob(Runnable job, Throwable throwable) {
            this.job = job;
            this.throwable = throwable;
        }

        public Runnable getJob() {
            return job;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }