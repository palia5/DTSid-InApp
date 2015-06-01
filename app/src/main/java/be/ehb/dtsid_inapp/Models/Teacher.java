package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;

/**
 * Created by doortje on 1/06/15.
 */
public class Teacher implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6426267251685790899L;


    public Long getId() {
        return id;
    }
    private Long id;
    private String name;
    private int acadyear;

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


    public Teacher(String name, int acadyear) {
        super();
        this.name = name;
        this.acadyear = acadyear;
    }


    public Teacher(Long id, String name, int acadyear) {
        super();
        this.id = id;
        this.name = name;
        this.acadyear = acadyear;
    }
    public Teacher() {
        super();
    }
    public void setId(Long id) {
        this.id = id;
    }
}
