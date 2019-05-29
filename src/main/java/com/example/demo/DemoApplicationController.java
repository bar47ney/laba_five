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

        Stream.Builder<Double> streamBuider = Stream.<Double>builder();//создаём стрим,в который буду добавлять только время

        while(iter.hasNext()) {
            Information val = iter.next();
            streamBuider
                    .accept(val.getTime());//добавляем в стрим время каждого элемента листа
        }
        streamBuider
                .build()
                .forEach(System.out::println);//выводим в консоль все элементы

        return list;//возвращаем в постман лист,но уже с посчитанным временем для каждого элемента листа класса Information
    }

    @RequestMapping("/laba_six_one")//адрес,по которому будем отправлять запрос в Postman`e,то есть localhost:8080/laba_six_one
    public Double main_two(@RequestBody ArrayList<Point> list) {//принимаем лист типа Поинт,который отправляем через Постман по адресу сверху

        double max_distance = 0;
        double buffer = 0;
        ArrayList<Double> distance_list = new ArrayList<Double>();//сюда буду добавлять абсолютно все расстояния мужду точками
        Point point_one = new Point();
        Point point_two = new Point();
        Iterator<Point> iter_one = list.iterator();             //это алгоритм поиска максимального расстояния мужду точками по моему заданию
        while (iter_one.hasNext()){
            point_one = iter_one.next();
            Iterator<Point> iter_two = list.iterator();
            while (iter_two.hasNext()){
                point_two = iter_two.next();
                buffer = point_one.calculate(point_one,point_two);//нашёл расстояние
                distance_list.add(buffer);//добавил его в distance_list
            }
        }
        max_distance = distance_list.stream()//здесь distance_list грубо говоря превращаем в стрим,у которого есть метод max,который и вернёт из всех расстояний самое больое и присвоит max_distance
                .max(Double::compare).get();

        return max_distance;//возвращаем в Постман ответ
    }

    @RequestMapping("/laba_six_two")////адрес,по которому будем отправлять запрос в Postman`e,то есть localhost:8080/laba_six_two
    public  Optional<Integer> main_three(@RequestBody ArrayList<Integer> list) { //принимаем из постмана лист интов

        Optional<Integer> max =list.stream()//здесь тоже лист превращаем в стрим,с методом reduce,который в данном случае поситает сумму всех элементов и вернёт её
                .reduce((acc,x)->acc+x);//

        return max ;//возвращаем сумму
    }
    @RequestMapping("/laba_six_three")
    public IntStream main_four(@RequestBody ArrayList<Integer> list) {

        int max = list.stream()
                .reduce(12,(acc,x)->acc+x);//снова лист в стрим,но в данном случае метод reduce вернёт в max сумму всех элементов +12


        return list.stream()//лист в стрим
                .map(x->x+max)//к каждому элементу добавляем max
                .flatMapToInt(x->IntStream.range(3,x-5))//функция,которая добавляет в лист числа,лежащие в диапозоне от 3 до х-5
                .filter(x->x>0)//оставляет в листе(стриме) только положительные элементы
                .distinct();//оставляет в листе только уникальные элементы(без повторок)

    }
}







