package com.brushmyskills.security.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rest/resource")
public class ResourceServerController{


    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
    
    @GetMapping("/hello")
    public String getNameFromResourceServer() {
        return "Hello World";
    }

}