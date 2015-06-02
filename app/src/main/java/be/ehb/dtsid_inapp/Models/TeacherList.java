package be.ehb.dtsid_inapp.Models;

import java.util.List;

public class TeacherList
{
    //Variables
    private List<Teacher> teachers;

    //Constructors
    public TeacherList()
    {
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
