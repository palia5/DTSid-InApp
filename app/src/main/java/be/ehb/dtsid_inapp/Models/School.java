package be.ehb.dtsid_inapp.Models;

public class School
{
    //Variables
    private Long id;
    private String name;
    private String city;
    private int zip;

    //Constructors
    public School(Long id, String name, String city, int zip)
    {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.zip = zip;
    }
    public School(String name, String city, int zip)
    {
        super();
        this.name = name;
        this.city = city;
        this.zip = zip;
    }
    public School()
    {
        super();
    }

    //Getters and setters
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
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public int getZip()
    {
        return zip;
    }
    public void setZip(int zip)
    {
        this.zip = zip;
    }
}
