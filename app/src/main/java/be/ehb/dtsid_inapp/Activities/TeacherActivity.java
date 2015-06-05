package be.ehb.dtsid_inapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.GetJSONTask;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.StudentFragments.StudentRegistration;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;
import be.ehb.dtsid_inapp.TeacherFragments.Options;
import be.ehb.dtsid_inapp.TeacherFragments.TeacherLogin;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class TeacherActivity extends AppCompatActivity
{
    private DatabaseContract dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

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

        dbc = new DatabaseContract(getApplicationContext());

        //Efkes logge
        //Sleep (tga te snel)
        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//*
        //TEST le Post avec le TEST Subscription
        Subscription testSub = new Subscription(null, "Karel", "Verzeypen", "karel1997@ggmail.com",
                "Doedoensstraat", "22", "2800", "Mechelen", false, true, false,
                Calendar.getInstance().getTime(), dbc.getTeacherByID(4683438497988608l),
                dbc.getEventByID(4814888656437248l), true, dbc.getSchoolByID(6312278001451008l));


        dbc.createSubscription(testSub);

//*/
        for(int i = 0 ; i < dbc.getAllSchools().size() ; i++)
            Log.d("SCHOOLS",dbc.getAllSchools().get(i).getName());

        for(int i = 0 ; i < dbc.getAllEvents().size() ; i++)
            Log.d("EVENTS",dbc.getAllEvents().get(i).getName());

        for(int i = 0 ; i < dbc.getAllTeachers().size() ; i++)
            Log.d("TEACHERS",dbc.getAllTeachers().get(i).getName());

        for(int i = 0 ; i < dbc.getAllSubscriptions().size() ; i++)
            Log.d("SUBSCRIPTIONS", dbc.getAllSubscriptions().get(i).getFirstName() + " " + dbc.getAllSubscriptions().get(i).getLastName() + " " + Boolean.toString(dbc.getAllSubscriptions().get(i).getNew()));

        Log.d("IMAGES SIZE", "" + dbc.getAllImages().size());

        //Close database
        dbc.close();

        //Start first fragment
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.teacherContainer, new DepartmentLogin())
                .commit();
    }

    public void goToOtherFragment(View v)
    {
        Button goToButton = (Button) v;

        if(goToButton.getId() == R.id.btn_bevestigen_launchscreen)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new TeacherLogin())
                    .commit();
        }

        else if(goToButton.getId() == R.id.btn_login_loginscreen)
        {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new Options())
                    .commit();
        }

        else if(goToButton.getId() == R.id.btn_student_registreren)
        {
            Intent studentIntent = new Intent(getApplicationContext(), StudentActivity.class);
            startActivity(studentIntent);
        }
    }
}
