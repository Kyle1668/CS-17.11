// Kyle O'Brien
// 10-5-17
// CS 17.11 - Assignment 4
// File Summary: The Person class stores attributes and behavior common
// to all data types describing people int he context of the program.
// The class allows storage of important data such as name, weight,
// age, and height. The class contains an empty default
// constructor to allow inheritance.

package edu.srjc.OBrien.Kyle.sports_team_class_system;

public class Person
{
    private String firstName = "";
    private String lastName = "";
    private double weight = 0;
    private double height = 0;
    private int age = 0;

    public Person()
    {
        // Necessary for inheritance.
    }

    public Person(String firstName, String lastName, double weight, double height, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}
