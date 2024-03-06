package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

@SpringBootApplication
@RestController
public class YuyiApplication {

    @RequestMapping("/hello")
    public String test() {

        List<String> arrayList = new ArrayList<>();

        arrayList.stream().map(x -> "".equals(x)).findAny().orElse(null);

        LinkedList linkedList = new LinkedList();

        return "hello world!";
    }


}
