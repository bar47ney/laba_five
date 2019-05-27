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
    public double main(@RequestBody ArrayList<Point> list) {

        double max_length = 0;
        double buffer = 0;
        Point point_one = new Point();
        Point point_two = new Point();
        Iterator<Point> iter_one = list.iterator();

        while (iter_one.hasNext()){
            point_one = iter_one.next();
            Iterator<Point> iter_two = list.iterator();
            while (iter_two.hasNext()){
                point_two = iter_two.next();
                buffer = point_one.calculate(point_one,point_two);
                if(max_length<buffer){
                    max_length = buffer;
                }
            }
        }

        return max_length;
    }
}

/*@RestController
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
}*/
