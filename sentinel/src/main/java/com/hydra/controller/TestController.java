package com.hydra.controller;

import com.hydra.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rate-limiter
 * @author: Hydra
 **/
@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
    private final QueryService queryService;

    @GetMapping("query/{name}")
    public String test(@PathVariable String name){
        return queryService.query(name);
    }

    @GetMapping("noLimit")
    public String noLimit(){
        System.out.println("no limit");
        return "noLimit";
    }

}
