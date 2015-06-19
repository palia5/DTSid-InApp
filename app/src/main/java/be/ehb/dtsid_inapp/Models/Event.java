package be.ehb.dtsid_inapp.Models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Event implements Serializable
{
    //Variables
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private int acadyear;

    //Constructors

    /**
     * Constructor
     * @param id
     * @param name
     * @param acadyear
     */
    public Event(Long id, String name, int acadyear)
    {
        super();
        this.id = id;
        this.name = name;
        this.acadyear = acadyear;
    }

    /**
     * Constructor
     * @param name
     * @param acadyear
     */
    public Event(String name, int acadyear)
    {
        super();
        this.name = name;
        this.acadyear = acadyear;
    }

    /**
     * Constructor
     */
    public Event()
    {
        super();
    }

    //Getters and setters

    /**
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return int acadyear
     */
    public int getAcadyear() {
        return acadyear;
    }

    /**
     * @param acadyear
     */
    public void setAcadyear(int acadyear) {
        this.acadyear = acadyear;
    }
}
