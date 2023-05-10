package com.hydra.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
@Service
public class QueryService {
    public static final String KEY="query";

    @SentinelResource(value = KEY,
            blockHandler ="blockHandlerMethod")
    public String query(String name){
        return "begin query,name="+name;
    }

    public String blockHandlerMethod(String name, BlockException e){
        e.printStackTrace();
        return "blockHandlerMethod for Query : " + name;
    }
}
