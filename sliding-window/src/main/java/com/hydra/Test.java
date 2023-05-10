package com.hydra;

import java.util.concurrent.TimeUnit;

/**
 * @program: rate-limiter
 * @author: Hydra
 * @create: 2023-05-08 16:23
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Test().test();
    }

    void test() throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter
                = new SlidingWindowRateLimiter(1000, 10, 10);
        TimeUnit.MILLISECONDS.sleep(800);

        for (int i = 0; i < 15; i++) {
            boolean acquire = slidingWindowRateLimiter.tryAcquire();
            if (acquire){
                System.out.println("执行任务");
            }else{
                System.out.println("被限流");
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

}
