package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;

/**
 * Created by doortje on 1/06/15.
 */
public class Event implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2383629800536599520L;

    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Event(Long id, String name, int acadyear) {
        super();
        this.id = id;
        this.name = name;
        this.acadyear = acadyear;
    }
    private String name;
    private int acadyear;

    public Event() {
        super();
    }

    public Event(String name, int acadyear) {
        super();
        this.name = name;
        this.acadyear = acadyear;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAcadyear() {
        return acadyear;
    }

    public void setAcadyear(int acadyear) {
        this.acadyear = acadyear;
    }
}
