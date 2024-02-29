package com.dip.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {

    //HTTP GET
    //htttp:localhost:8080/hello
    @GetMapping("/hello")
    public  String helloWorld(){
        return "hello World !!";
    }
}
