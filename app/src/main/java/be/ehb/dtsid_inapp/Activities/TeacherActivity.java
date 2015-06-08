package be.ehb.dtsid_inapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.GetJSONTask;
import be.ehb.dtsid_inapp.Models.Department;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class TeacherActivity extends AppCompatActivity
{
    private DatabaseContract dbc;
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

        //Contract opvrage
        dbc = new DatabaseContract(getApplicationContext());

        //Start JSONS
        if(dbc.getAllTeachers().isEmpty())
        {
            String urlTeachers = BASEURL + ALL_TEACHERS;
            GetJSONTask jsonTask1 = new GetJSONTask(getApplicationContext());
            jsonTask1.execute(urlTeachers);

            String urlEvents = BASEURL + ALL_EVENTS;
            GetJSONTask jsonTask2 = new GetJSONTask(getApplicationContext());
            jsonTask2.execute(urlEvents);

            String urlSchools = BASEURL + ALL_SCHOOLS;
            GetJSONTask jsonTask3 = new GetJSONTask(getApplicationContext());
            jsonTask3.execute(urlSchools);

            String urlSubscriptions = BASEURL + ALL_SUBSCRIPTIONS;
            GetJSONTask jsonTask4 = new GetJSONTask(getApplicationContext());
            jsonTask4.execute(urlSubscriptions);

            String urlImages = BASEURL + ALL_IMAGES;
            GetJSONTask jsonTask5 = new GetJSONTask(getApplicationContext());
            jsonTask5.execute(urlImages);
        }
        dbc.close();
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
