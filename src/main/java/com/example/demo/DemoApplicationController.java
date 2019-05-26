package com.example.demo;

/**
 * Created by Сергей on 27.04.2019.
 */

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

@RestController
public class DemoApplicationController {

   @RequestMapping("/")
    public ArrayList<Information> main(@RequestBody ArrayList<Information> list) {

        list.parallelStream()
                .filter(x->x.getSpeed()>0)
                .filter(x->x.getDistance()>0)
                .forEach(x->x.calculate());

        Iterator<Information> iter = list.iterator();

        Stream.Builder<Double> streamBuider = Stream.<Double>builder();

        while(iter.hasNext()) {
            Information val = iter.next();
            streamBuider
                    .accept(val.getTime());
        }
        streamBuider
                .build()
                .forEach(System.out::println);

        return list;
    }
}
