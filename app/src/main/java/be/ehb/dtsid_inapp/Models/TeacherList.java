package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;
import java.util.List;

public class TeacherList implements Serializable
{
    //Variables
    private List<Teacher> teachers;

    //Constructors
    public TeacherList(List<Teacher> teachers)
    {
        this.teachers = teachers;
    }

    //Getters and setters
    public List<Teacher> getTeachers()
    {
        return teachers;
    }
    public void setTeachers(List<Teacher> teachers)
    {
        this.teachers = teachers;
    }
}
