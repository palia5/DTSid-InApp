package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;

/**
 *
 * @author Dorothée
 * @version 1.0
 *
 *
 */

public class Department implements Serializable
{
    //Variables
    private Long id;
    private String name;
    private String code;
    private String url;

    //Constructors

    /**
     * Constructor
     * @param id
     * @param name
     * @param code
     * @param url
     */
    public Department(Long id, String name, String code, String url)
    {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.url = url;
    }

    /**
     * Constructor
     * @param name
     * @param code
     * @param url
     */
    public Department(String name, String code, String url)
    {
        super();
        this.name = name;
        this.code = code;
        this.url = url;
    }

    /**
     * Constructor
     */
    public Department()
    {
        super();
    }

    //Getters and setters

    /**
     * @return String code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
    public Long getId() {
        return id;
    }

    /**
     *
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
     * @return String url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
