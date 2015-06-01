package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;

/**
 * Created by doortje on 1/06/15.
 */
public class School implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6113317881903371549L;

    private Long id;
    private String name;
    private String gemeente;
    private int postcode;


    public School() {
        super();
    }
    public School(Long id, String name, String gemeente, int postcode) {
        super();
        this.id = id;
        this.name = name;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }
    public School(String name, String gemeente, int postcode) {
        super();
        this.name = name;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGemeente() {
        return gemeente;
    }
    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }
    public int getPostcode() {
        return postcode;
    }
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
