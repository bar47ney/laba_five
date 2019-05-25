package com.example.demo;

/**
 * Created by Сергей on 27.04.2019.
 */

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

@RestController
public class DemoApplicationController {


   /* public String helloWorld()
   {
        return "I`am Batman!";
   }*/


   @RequestMapping("/")
    public String main(@RequestBody ArrayList<Information> list) {

        String time = new String();

        ArrayList<Double> list_one = new ArrayList<Double>();
       // list = patient ;
       // Information valee = new Information();
       // valee.setDistance(200);
        //valee.setSpeed(2500);
       // list.add(valee);

        list.parallelStream()
                .filter(x->x.getSpeed()>0)
                .filter(x->x.getDistance()>0)
                .forEach(x->x.calculate());

        Iterator<Information> iter = list.iterator();

        Stream.Builder<Double> streamBuider = Stream.<Double>builder();

        while(iter.hasNext()) {
            Information val = iter.next();
            streamBuider.accept(val.getTime());
        }
        streamBuider
                .build()
                .forEach(System.out::println);

        return "God Job,Man!";
    }
}
