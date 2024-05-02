package com.dip.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {

    //HTTP GET
    //http:localhost:8080/hello
    @GetMapping("/hello")
    public  String helloWorld(){

        var res = "<h1>Hello World</h1>";
        return res;
    }
}
