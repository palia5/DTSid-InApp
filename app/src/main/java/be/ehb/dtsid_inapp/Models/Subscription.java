package be.ehb.dtsid_inapp.Models;

import java.util.Date;
import java.util.HashMap;

public class Subscription
{
    //Variables
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String streetNumber;
    private String zip;
    private String city;
    private HashMap<String,String> interests;
    private Boolean digx;
    private Boolean multec;
    private Boolean werkstudent;
    private Date timestamp;
    private Teacher teacher;
    private Event event;
    private boolean isNew;
    private School school;

    //Constructors
    public Subscription(Long id, String firstName, String lastName,
                        String email, String street, String streetNumber, String zip,
                        String city, HashMap<String, String> interests, Date timestamp,
                        Teacher teacher, Event event, boolean isNew,School school) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zip = zip;
        this.city = city;
        this.interests = interests;
        this.timestamp = timestamp;
        this.teacher = teacher;
        this.event = event;
        this.isNew = isNew;
        this.school = school;
    }
    public Subscription(String firstName, String lastName, String email,
                        String street, String streetNumber, String zip, String city,
                        HashMap<String, String> interests, Date timestamp, Teacher teacher,
                        Event event, boolean isNew,School school) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zip = zip;
        this.city = city;
        this.interests = interests;
        this.timestamp = timestamp;
        this.teacher = teacher;
        this.event = event;
        this.isNew = isNew;
        this.school = school;
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
    public HashMap<String, String> getInterests()
    {
        return interests;
    }
    public void setInterests(HashMap<String, String> interests)
    {
        this.interests = interests;
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
    public boolean isNew()
    {
        return isNew;
    }
    public void setNew(boolean isNew)
    {
        this.isNew = isNew;
    }

    public Long getId() {
        return id;
    }

    public Boolean getDigx() {
        return digx;
    }

    public void setDigx(Boolean digx) {
        this.digx = digx;
    }

    public Boolean getMultec() {
        return multec;
    }

    public void setMultec(Boolean multec) {
        this.multec = multec;
    }

    public Boolean getWerkstudent() {
        return werkstudent;
    }

    public void setWerkstudent(Boolean werkstudent) {
        this.werkstudent = werkstudent;
    }
}
