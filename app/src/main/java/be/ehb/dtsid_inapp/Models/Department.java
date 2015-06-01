package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;

public class Department implements Serializable
{
    //Variables
    private Long id;
    private String name;
    private String code;
    private String url;

    //Constructors
    public Department(Long id, String name, String code, String url)
    {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.url = url;
    }
    public Department(String name, String code, String url)
    {
        super();
        this.name = name;
        this.code = code;
        this.url = url;
    }
    public Department()
    {
        super();
    }

    //Getters and setters
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
