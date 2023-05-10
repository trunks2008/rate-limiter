package com.hydra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
@Slf4j
@Component
public class PathKeyResolver implements KeyResolver {
    public Mono<String> resolve(ServerWebExchange exchange) {
        RedisRateLimiter redisRateLimiter;
        String path = exchange.getRequest().getPath().toString();
        log.info("Request path: {}",path);
        return Mono.just(path);
    }
}
