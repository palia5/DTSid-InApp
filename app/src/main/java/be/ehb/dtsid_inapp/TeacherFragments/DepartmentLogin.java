package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.GetJSONTask;
import be.ehb.dtsid_inapp.R;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class DepartmentLogin extends Fragment
{
    TeacherActivity activity;
    private DatabaseContract dbc;
    private ProgressDialog loadingDatabaseDialog;

    private Spinner departmentSP;
    private EditText codeET;
    private Button loginBTN;
    private Boolean goToNext = false;
    private Animation buttonAnim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_department_login_launchscreen, null);

        activity = (TeacherActivity) this.getActivity();
        dbc = new DatabaseContract(activity.getApplicationContext());

        loadingDatabaseDialog = new ProgressDialog(activity);
        loadingDatabaseDialog.setTitle("Downloading database");
        loadingDatabaseDialog.setMessage("Loading.. pls stahp..");

        departmentSP = (Spinner) v.findViewById(R.id.sp_department_list);
        codeET = (EditText) v.findViewById(R.id.et_code_launchscreen);
        loginBTN = (Button) v.findViewById(R.id.btn_bevestigen_launchscreen);
        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext()
                , R.anim.button_animation_basic);

        loginBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(buttonAnim);
                buttonAnim.setAnimationListener(new Animation.AnimationListener()
                {
                    @Override
                    public void onAnimationStart(Animation animation)
                    {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        loadingDatabaseDialog.show();

                        //Start JSONS
                        if (dbc.getAllTeachers().isEmpty())
                        {
                            String urlTeachers = BASEURL + ALL_TEACHERS;
                            GetJSONTask jsonTask1 = new GetJSONTask(DepartmentLogin.this);
                            jsonTask1.execute(urlTeachers);
                        }
                        if (dbc.getAllEvents().isEmpty())
                        {
                            String urlEvents = BASEURL + ALL_EVENTS;
                            GetJSONTask jsonTask2 = new GetJSONTask(DepartmentLogin.this);
                            jsonTask2.execute(urlEvents);
                        }
                        if (dbc.getAllSchools().isEmpty())
                        {
                            String urlSchools = BASEURL + ALL_SCHOOLS;
                            GetJSONTask jsonTask3 = new GetJSONTask(DepartmentLogin.this);
                            jsonTask3.execute(urlSchools);
                        }/*
                        if (dbc.getAllImages().isEmpty())
                        {
                            String urlImages = BASEURL + ALL_IMAGES;
                            GetJSONTask jsonTask4 = new GetJSONTask(DepartmentLogin.this);
                            jsonTask4.execute(urlImages);
                        }*/

                        goToNext = true;

                        everythingIsLoaded();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }
                });

            }
        });

        return v;
    }

    public void everythingIsLoaded()
    {
        if(!dbc.getAllSubscriptions().isEmpty()/* && !dbc.getAllImages().isEmpty()*/ && goToNext)
        {
            dbc.close();

            loadingDatabaseDialog.dismiss();

            activity.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new TeacherLogin())
                    .commit();
        }

        else if(!dbc.getAllTeachers().isEmpty() && !dbc.getAllEvents().isEmpty() && !dbc.getAllSchools().isEmpty())
        {
            String urlSubscriptions = BASEURL + ALL_SUBSCRIPTIONS;
            GetJSONTask jsonTask5 = new GetJSONTask(DepartmentLogin.this);
            jsonTask5.execute(urlSubscriptions);
        }
    }
}
