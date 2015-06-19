package be.ehb.dtsid_inapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Dorothée
 * @version 1.0
 */

public class Subscription implements Serializable 
{
    //Variables
    @Expose
    private Long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String street;
    @Expose
    private String streetNumber;
    @Expose
    private String zip;
    @Expose
    private String city;
    @Expose(serialize = false, deserialize = false)
    private Boolean digx;
    @Expose(serialize = false, deserialize = false)
    private Boolean multec;
    @Expose(serialize = false, deserialize = false)
    private Boolean werkstudent;
    @Expose(serialize = false, deserialize = false)
    private Date timestamp;
    @Expose
    @SerializedName("timestamp")
    private long timestampLong;
    @Expose
    private Teacher teacher;
    @Expose
    private Event event;
    @Expose
    @SerializedName("new")
    private Boolean isNew;
    @Expose
    private School school;
    @Expose
    private HashMap<String, String> interests;

    //Constructors

    /**
     * Constructor
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param street
     * @param streetNumber
     * @param zip
     * @param city
     * @param digx
     * @param multec
     * @param werkstudent
     * @param timestamp
     * @param teacher
     * @param event
     * @param isNew
     * @param school
     */
    public Subscription(Long id, String firstName, String lastName,
                        String email, String street, String streetNumber, String zip,
                        String city, Boolean digx, Boolean multec, Boolean werkstudent, Date timestamp,
                        Teacher teacher, Event event, Boolean isNew,School school) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zip = zip;
        this.city = city;
        this.digx = digx;
        this.multec = multec;
        this.werkstudent = werkstudent;
        this.timestamp = timestamp;
        this.teacher = teacher;
        this.event = event;
        this.isNew = isNew;
        this.school = school;

        this.interests = new HashMap<>();
        interests.put("digx", Boolean.toString(digx));
        interests.put("werkstudent", Boolean.toString(werkstudent));
        interests.put("multec", Boolean.toString(multec));
        this.timestampLong = timestamp.getTime();
    }

    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param email
     * @param street
     * @param streetNumber
     * @param zip
     * @param city
     * @param digx
     * @param multec
     * @param werkstudent
     * @param timestamp
     * @param teacher
     * @param event
     * @param isNew
     * @param school
     */
    public Subscription(String firstName, String lastName, String email,
                        String street, String streetNumber, String zip, String city,
                        Boolean digx, Boolean multec, Boolean werkstudent, Date timestamp, Teacher teacher,
                        Event event, Boolean isNew,School school) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zip = zip;
        this.city = city;
        this.digx = digx;
        this.multec = multec;
        this.werkstudent = werkstudent;
        this.timestamp = timestamp;
        this.teacher = teacher;
        this.event = event;
        this.isNew = isNew;
        this.school = school;

        this.interests = new HashMap<>();
        interests.put("digx", Boolean.toString(digx));
        interests.put("werkstudent", Boolean.toString(werkstudent));
        interests.put("multec", Boolean.toString(multec));
        this.timestampLong = timestamp.getTime();
    }

    /**
     * Constructor
     */
    public Subscription()
    {
        super();
    }

    //Getters and setters

    /**
     *
     * @return Long id
     */
    public Long getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     *
     * @return School school
     */
    public School getSchool()
    {
        return school;
    }

    /**
     *
     * @param school
     */
    public void setSchool(School school)
    {
        this.school = school;
    }

    /**
     *
     * @return String firstname
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     *
     * @return String lastname
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     *
     * @return String email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     *
     * @return String street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     *
     * @return String streetnumber
     */
    public String getStreetNumber()
    {
        return streetNumber;
    }

    /**
     *
     * @param streetNumber
     */
    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    /**
     *
     * @return String zip
     */
    public String getZip()
    {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip)
    {
        this.zip = zip;
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
     * @return Boolean digx
     */
    public Boolean getDigx() {
        return digx;
    }

    /**
     *
     * @param digx
     */
    public void setDigx(Boolean digx)
    {
        this.digx = digx;
    }

    /**
     *
     * @return Boolean multec
     */
    public Boolean getMultec()
    {
        return multec;
    }

    /**
     *
     * @param multec
     */
    public void setMultec(Boolean multec)
    {
        this.multec = multec;
    }

    /**
     *
     * @return Boolean werkstudent
     */
    public Boolean getWerkstudent()
    {
        return werkstudent;
    }

    /**
     *
     * @param werkstudent
     */
    public void setWerkstudent(Boolean werkstudent)
    {
        this.werkstudent = werkstudent;
    }

    /**
     *
     * @return Date timestamp
     */
    public Date getTimestamp()
    {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return Teacher teacher
     */
    public Teacher getTeacher()
    {
        return teacher;
    }

    /**
     *
     * @param teacher
     */
    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }

    /**
     *
     * @return Event event
     */
    public Event getEvent()
    {
        return event;
    }

    /**
     *
     * @param event
     */
    public void setEvent(Event event)
    {
        this.event = event;
    }

    /**
     *
     * @return boolean isNew
     */
    public boolean getNew()
    {
        return isNew;
    }

    /**
     *
     * @param isNew
     */
    public void setNew(boolean isNew)
    {
        this.isNew = isNew;
    }

    /**
     *
     * @return HashMap<String, String> interests
     */
    public HashMap<String, String> getInterests()
    {
        return interests;
    }

    /**
     *  sets inrests
     */
    public void setInterests (){
        this.interests = new HashMap<String, String>();
        interests.put("digx", Boolean.toString(digx));
        interests.put("werkstudent", Boolean.toString(werkstudent));
        interests.put("multec", Boolean.toString(multec));
    }

    /**
     *
     * @return Long timestamp
     */
    public long getTimestampLong()
    {
        return timestampLong;
    }

    /**
     *  sets the timestamp
     */
    public void setTimestampLong() {
        this.timestampLong = this.timestamp.getTime();
    }
}
