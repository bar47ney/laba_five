package com.example.demo;

/**
 * Created by Сергей on 27.04.2019.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoApplicationController {

    @RequestMapping("/")
    public String helloWorld()
    {
        return "I`am Batman!";
    }
}