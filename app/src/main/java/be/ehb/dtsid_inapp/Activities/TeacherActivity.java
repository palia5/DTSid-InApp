package be.ehb.dtsid_inapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import be.ehb.dtsid_inapp.Models.Department;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

public class TeacherActivity extends AppCompatActivity
{
    private Department department;
    private Teacher teacher;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        department = new Department();
        teacher = new Teacher();
        event = new Event();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //Start first fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.teacherContainer, new DepartmentLogin())
                .commit();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
