package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class YuyiApplication {

    @RequestMapping("/hello")
    public String test() {

        List<String> arrayList = new ArrayList<>();

        arrayList.stream().map(x -> "".equals(x)).findAny().orElse(null);

        return "hello world!";
    }


}
