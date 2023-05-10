package com.hydra;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
@Slf4j
public class RateLimiterTest {
    public static void main(String[] args) {
//        new RateLimiterTest().acquireTest();
//        new RateLimiterTest().acquireMultiTest();
        new RateLimiterTest().acquireSmoothly();
    }

    void acquireTest(){
        RateLimiter rateLimiter=RateLimiter.create(5);
        for (int i = 0; i < 10; i++) {
            double time = rateLimiter.acquire();
            log.info("等待时间：{}s",time);
        }
    }

    void acquireMultiTest(){
        RateLimiter rateLimiter=RateLimiter.create(1);

        for (int i = 0; i <3; i++) {
            int num = 2 * i + 1;
            log.info("获取{}个令牌", num);
            double cost = rateLimiter.acquire(num);
            log.info("获取{}个令牌结束，耗时{}ms",num,cost);
        }
    }

    void acquireSmoothly(){
        RateLimiter rateLimiter =RateLimiter.create(5,3, TimeUnit.SECONDS);
        long startTimeStamp = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            double time = rateLimiter.acquire();
            log.info("等待时间:{}s, 总时间:{}ms"
                    ,time,System.currentTimeMillis()-startTimeStamp);
        }
    }

}
