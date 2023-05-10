package com.hydra;

import java.util.concurrent.TimeUnit;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Test().test();
    }

    void test() throws InterruptedException {
        LeakyBucketRateLimiter leakyBucketRateLimiter
                =new LeakyBucketRateLimiter(3,1);
        for (int i = 0; i < 15; i++) {
            if (leakyBucketRateLimiter.tryAcquire()) {
                System.out.println("执行任务");
            }else {
                System.out.println("被限流");
            }
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

}
