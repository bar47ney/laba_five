package com.example.demo;

/**
 * Created by Сергей on 27.04.2019.
 */

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



@RestController
public class DemoApplicationController {

   @RequestMapping("/laba_five")
    public ArrayList<Information> main_one(@RequestBody ArrayList<Information> list) {

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

    @RequestMapping("/laba_six_one")
    public Double main_two(@RequestBody ArrayList<Point> list) {

        double max_distance = 0;
        double buffer = 0;
        ArrayList<Double> distance_list = new ArrayList<Double>();
        Point point_one = new Point();
        Point point_two = new Point();
        Iterator<Point> iter_one = list.iterator();
        while (iter_one.hasNext()){
            point_one = iter_one.next();
            Iterator<Point> iter_two = list.iterator();
            while (iter_two.hasNext()){
                point_two = iter_two.next();
                buffer = point_one.calculate(point_one,point_two);
                distance_list.add(buffer);
            }
        }
        max_distance = distance_list.stream()
                .max(Double::compare).get();

        return max_distance;
    }

    @RequestMapping("/laba_six_two")
    public  Optional<Integer> main_three(@RequestBody ArrayList<Integer> list) {

        Optional<Integer> max =list.stream()
                .reduce((acc,x)->acc+x);

        return max ;
    }
    @RequestMapping("/laba_six_three")
    public IntStream main_four(@RequestBody ArrayList<Integer> list) {

        int max = list.stream()
                .reduce(12,(acc,x)->acc+x);


        return list.stream()
                .map(x->x+max)
                .flatMapToInt(x->IntStream.range(3,x-5))
                .filter(x->x>0)
                .distinct();

    }
}







