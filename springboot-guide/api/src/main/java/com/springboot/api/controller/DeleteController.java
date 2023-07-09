package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
    // http://localhost:8080/api/v1/delete-api/{String값}
    @DeleteMapping(value = "/{variable}")
    public String deleteVariable(@PathVariable("variable") String var){
        return var;
    }

    // http://localhost:8080/api/v1/delete-api/request1?email=value
    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String email){
        return "email : " + email;
    }
}
