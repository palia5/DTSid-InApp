package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;

public class Teacher implements Serializable
{
    //Variables
    private Long id;
    private String name;
    private int acadyear;

    //Constructors
    public Teacher(Long id, String name, int acadyear)
    {
        super();
        this.id = id;
        this.name = name;
        this.acadyear = acadyear;
    }
    public Teacher(String name, int acadyear)
    {
        super();
        this.name = name;
        this.acadyear = acadyear;
    }
    public Teacher()
    {
        super();
    }

    //Getters and setters
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAcadyear()
    {
        return acadyear;
    }
    public void setAcadyear(int acadyear)
    {
        this.acadyear = acadyear;
    }
}
