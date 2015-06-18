package be.ehb.dtsid_inapp.Activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import be.ehb.dtsid_inapp.Models.Department;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;
import be.ehb.dtsid_inapp.TeacherFragments.Options;
import be.ehb.dtsid_inapp.TeacherFragments.TeacherLogin;

/**
 *
 * @author Dries
 * @version 1.0
 *
 *
 */

public class TeacherActivity extends AppCompatActivity
{
    private Department department;
    private Teacher teacher;
    private Event event;
    private int currentYear;

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
                .replace(R.id.teacherContainer, new DepartmentLogin(), "DEPARTMENT_LOGIN")
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

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    @Override
    public void onBackPressed()
    {
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.teacherContainer);
        switch(currentFragment.getTag())
        {
            case "DEPARTMENT_LOGIN":
                super.onBackPressed();
                break;
            case "TEACHER_LOGIN":
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.teacherContainer, new DepartmentLogin(), "DEPARTMENT_LOGIN")
                        .commit();
                break;
            case "OPTIONS_DASHBOARD":
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.teacherContainer, new TeacherLogin(), "TEACHER_LOGIN")
                        .commit();
                break;
            case "OPTIONS_LIST":
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.teacherContainer, new Options(), "OPTIONS_DASHBOARD")
                        .commit();
                break;
            case "OPTIONS_PREFERENCES":
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new Options(), "OPTIONS_DASHBOARD")
                    .commit();
            break;
        }
    }
}
