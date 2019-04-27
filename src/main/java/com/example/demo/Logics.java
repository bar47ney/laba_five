package com.example.demo;

/**
 * Created by Сергей on 27.04.2019.
 */
public class Logics
{
    private int time;
    public void calculate(int distance, int speed)
    {
        time = distance / speed;
    }

    public int getTime()
    {
        return time;
    }
}
