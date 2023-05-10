package com.hydra;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LeakyBucketRateLimiter {
    // 桶的容量
    private int capacity;
    // 桶中现存水量
    private AtomicInteger water=new AtomicInteger(0);
    // 开始漏水时间
    private long leakTimeStamp;
    // 水流出的速率，即每秒允许通过的请求数
    private int leakRate;

    public LeakyBucketRateLimiter(int capacity,int leakRate){
        this.capacity=capacity;
        this.leakRate=leakRate;
    }

    public synchronized boolean tryAcquire(){
        // 桶中没有水，重新开始计算
        if (water.get()==0){
            log.info("start leaking");
            leakTimeStamp = System.currentTimeMillis();
            water.incrementAndGet();
            return water.get() < capacity;
        }

        // 先漏水，计算剩余水量
        long currentTime = System.currentTimeMillis();
        int leakedWater= (int) ((currentTime-leakTimeStamp)/1000 * leakRate);
        log.info("lastTime:{}, currentTime:{}. LeakedWater:{}",leakTimeStamp,currentTime,leakedWater);

        // 可能时间不足,则先不漏水
        if (leakedWater != 0){
            int leftWater = water.get() - leakedWater;
            // 可能水已漏光，设为0
            water.set(Math.max(0,leftWater));
            leakTimeStamp=System.currentTimeMillis();
        }
        log.info("剩余容量:{}",capacity-water.get());

        if (water.get() < capacity){
            log.info("tryAcquire success");
            water.incrementAndGet();
            return true;
        }else {
            log.info("tryAcquire fail");
            return false;
        }
    }

}
