package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
    private Boolean loadingSubscriptions = false;
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
                        loginBTN.setVisibility(View.INVISIBLE);

                        if (!dbc.getAllSubscriptions().isEmpty())
                        {
                            everythingIsLoaded(true);
                            return;
                        }
                        else
                            loadingDatabaseDialog.show();

                        //Start JSONS
                        if (dbc.getAllTeachers().isEmpty())
                        {
                            String urlTeachers = BASEURL + ALL_TEACHERS;
                            startMyTask(urlTeachers);
                        }
                        if (dbc.getAllEvents().isEmpty())
                        {
                            String urlEvents = BASEURL + ALL_EVENTS;
                            startMyTask(urlEvents);
                        }
                        if (dbc.getAllSchools().isEmpty())
                        {
                            String urlSchools = BASEURL + ALL_SCHOOLS;
                            startMyTask(urlSchools);
                        }/*
                        if (dbc.getAllImages().isEmpty())
                        {
                            String urlImages = BASEURL + ALL_IMAGES;
                            startMyTask(urlImages);
                        }*/

                        everythingIsLoaded(false);
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

    public void everythingIsLoaded(Boolean subscriptionLoaded)
    {
        if(subscriptionLoaded)
        {
            dbc.close();

            loadingDatabaseDialog.dismiss();

            activity.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new TeacherLogin(), "TEACHER_LOGIN")
                    .commit();
        }

        else if(!dbc.getAllTeachers().isEmpty() && !dbc.getAllEvents().isEmpty() && !dbc.getAllSchools().isEmpty() && loadingSubscriptions == false)
        {
            String urlSubscriptions = BASEURL + ALL_SUBSCRIPTIONS;
            startMyTask(urlSubscriptions);
            loadingSubscriptions = true;
        }
    }

    // PARALLEL ASYNCS
    void startMyTask(String url)
    {
        GetJSONTask jsonTask = new GetJSONTask(DepartmentLogin.this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            jsonTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        else
            jsonTask.execute(url);
    }
}