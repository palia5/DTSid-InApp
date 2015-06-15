package be.ehb.dtsid_inapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Subscription implements Serializable {
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
    public Subscription()
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
    public School getSchool()
    {
        return school;
    }
    public void setSchool(School school)
    {
        this.school = school;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getStreet()
    {
        return street;
    }
    public void setStreet(String street)
    {
        this.street = street;
    }
    public String getStreetNumber()
    {
        return streetNumber;
    }
    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }
    public String getZip()
    {
        return zip;
    }
    public void setZip(String zip)
    {
        this.zip = zip;
    }
    public String getCity()
    {
        return city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public Boolean getDigx() {
        return digx;
    }
    public void setDigx(Boolean digx)
    {
        this.digx = digx;
    }
    public Boolean getMultec()
    {
        return multec;
    }
    public void setMultec(Boolean multec)
    {
        this.multec = multec;
    }
    public Boolean getWerkstudent()
    {
        return werkstudent;
    }
    public void setWerkstudent(Boolean werkstudent)
    {
        this.werkstudent = werkstudent;
    }
    public Date getTimestamp()
    {
        return timestamp;
    }
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }
    public Teacher getTeacher()
    {
        return teacher;
    }
    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }
    public Event getEvent()
    {
        return event;
    }
    public void setEvent(Event event)
    {
        this.event = event;
    }
    public boolean getNew()
    {
        return isNew;
    }
    public void setNew(boolean isNew)
    {
        this.isNew = isNew;
    }
    public HashMap<String, String> getInterests()
    {
        return interests;
    }
    public void setInterests (){
        this.interests = new HashMap<String, String>();
        interests.put("digx", Boolean.toString(digx));
        interests.put("werkstudent", Boolean.toString(werkstudent));
        interests.put("multec", Boolean.toString(multec));
    }
    public long getTimestampLong()
    {
        return timestampLong;
    }
    public void setTimestampLong() {
        this.timestampLong = this.timestamp.getTime();
    }
}
