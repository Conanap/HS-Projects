import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Throwables here.
 * 
 * @author Roland Li 
 * @version (a version number or a date)
 */
public abstract class Throwables extends Actor
{
    protected int maxSpeed, curSpeed;
    protected int maxTime, curTime; //Amount of time rope spends moving upwards

    GreenfootImage baseImg;
    protected int flightDist; //For the image length

}
