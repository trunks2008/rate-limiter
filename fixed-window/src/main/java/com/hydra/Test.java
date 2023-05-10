package com.hydra;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
@Slf4j
public class Test {
    public static void main(String[] args) throws InterruptedException {
        new Test().test();
    }

    void test() throws InterruptedException {
        FixedWindowRateLimiter fixedWindowRateLimiter
                = new FixedWindowRateLimiter(1000, 5);

        for (int i = 0; i < 10; i++) {
            if (fixedWindowRateLimiter.tryAcquire()) {
                System.out.println("执行任务");
            }else{
                System.out.println("被限流");
                TimeUnit.MILLISECONDS.sleep(300);
            }
        }
    }

}
