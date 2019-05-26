package com.example.demo;

/**
 * Created by Сергей on 27.04.2019.
 */
public class Information
{
    private double distance;
    private double speed;
    private double time;

    public Information() {
        distance =0;
        speed =0;
        time =0;
    }

    public void setSpeed(double speed)
{
    this.speed = speed;
}

   public void setDistance(double distance)
    {
        this.distance = distance;
    }

   public  double getSpeed()
    {
        return speed;
    }

    public double getDistance()
    {
       return distance;
    }

    public double getTime()
    {
        return time;
    }


    public void calculate()
    {
        time = distance / speed;
    }


}
