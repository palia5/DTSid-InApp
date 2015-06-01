package be.ehb.dtsid_inapp.Models;

import java.util.List;

public class SchoolList
{
    //Variables
    List<School> schools;

    //Constructor
    public SchoolList(List<School> schools)
    {
        this.schools = schools;
    }

    //Getters and setters
    public List<School> getSchools()
    {
        return schools;
    }
    public void setSchools(List<School> schools)
    {
        this.schools = schools;
    }
}
