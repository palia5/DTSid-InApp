package be.ehb.dtsid_inapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Dorothée
 * @version 1.0
 */

public class School
{
    //Variables
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    @SerializedName("gemeente")
    private String city;
    @Expose
    @SerializedName("postcode")
    private int zip;

    //Constructors

    /**
     * Constructor
     * @param id
     * @param name
     * @param city
     * @param zip
     */
    public School(Long id, String name, String city, int zip)
    {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.zip = zip;
    }

    /**
     * Constructor
     * @param name
     * @param city
     * @param zip
     */
    public School(String name, String city, int zip)
    {
        super();
        this.name = name;
        this.city = city;
        this.zip = zip;
    }

    /**
     * Constructor
     */
    public School()
    {
        super();
    }

    //Getters and setters

    /**
     *
     * @return Long id
     */
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
     * @return String city
     */
    public String getCity()
    {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     *
     * @return int zip
     */
    public int getZip()
    {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(int zip)
    {
        this.zip = zip;
    }
}
