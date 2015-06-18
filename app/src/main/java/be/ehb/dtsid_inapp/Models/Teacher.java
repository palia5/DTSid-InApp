package be.ehb.dtsid_inapp.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 *
 * @author Dorothée
 * @version 1.0
 *
 *
 */

public class Teacher implements Serializable
{
    //Variables
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
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
