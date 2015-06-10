package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.PostJSONTask;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;

public class Options extends Fragment implements View.OnClickListener
{
    TeacherActivity activity;
    private DatabaseContract dbc;
    private ProgressDialog loadingDatabaseDialog;

    private Button studentRegistrerenBTN;
    private Button lijstBTN;
    private Button regioBTN;
    private Button optiesBTN;
    private Button syncBTN;
    private TextView departementTV;
    private TextView medewerkerTV;
    private TextView evenementTV;
    private TextView aantalStudentenTV;
    private TextView laatsteSyncTV;
    private Animation buttonAnim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_options_dashboardscreen, null);

        activity = (TeacherActivity) this.getActivity();
        //Contract opvrage
        dbc = new DatabaseContract(activity.getApplicationContext());

        loadingDatabaseDialog = new ProgressDialog(activity);
        loadingDatabaseDialog.setTitle("Syncing database");
        loadingDatabaseDialog.setMessage("Syncing.. pls halp..");

        studentRegistrerenBTN = (Button) v.findViewById(R.id.btn_student_registreren);
        lijstBTN = (Button) v.findViewById(R.id.btn_lijst);
        regioBTN = (Button) v.findViewById(R.id.btn_regios);
        optiesBTN = (Button) v.findViewById(R.id.btn_opties);
        syncBTN = (Button) v.findViewById(R.id.btn_sync_dashboard);
        departementTV = (TextView) v.findViewById(R.id.tv_gekozen_departement_dashboard);
        medewerkerTV = (TextView) v.findViewById(R.id.tv_gekozen_medewerker_dashboard);
        evenementTV = (TextView) v.findViewById(R.id.tv_gekozen_evenement_dashboard);
        aantalStudentenTV = (TextView) v.findViewById(R.id.tv_aantalstudenten);
        laatsteSyncTV = (TextView) v.findViewById(R.id.tv_datum_laatste_synchronisatie);
        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.button_animation_large);


        studentRegistrerenBTN.setOnClickListener(this);
        optiesBTN.setOnClickListener(this);
        syncBTN.setOnClickListener(this);

        medewerkerTV.setText(activity.getTeacher().getName());
        evenementTV.setText(activity.getEvent().getName());

        int newSubs = 0;
        for(Subscription sub : dbc.getAllSubscriptions())
            if(sub.getNew())
                newSubs++;
        aantalStudentenTV.setText("" + newSubs);

        dbc.close();

        return v;
    }

    public void allIsSynced()
    {
        loadingDatabaseDialog.dismiss();
        Toast.makeText(getActivity(), "Synced it!", Toast.LENGTH_LONG).show();
    }

    private void nagivateAfterClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_student_registreren:
                Intent studentIntent = new Intent(activity.getApplicationContext(), StudentActivity.class);
                studentIntent.putExtra("Teacher_id", activity.getTeacher().getId());
                studentIntent.putExtra("Event_id", activity.getEvent().getId());
                startActivity(studentIntent);
                break;
            case R.id.btn_opties:
                activity.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.teacherContainer, new OptionsPreferences(), "OPTIONS_PREFERENCES")
                        .commit();
                break;
            case R.id.btn_sync_dashboard:
                loadingDatabaseDialog.show();
                PostJSONTask jsonTask = new PostJSONTask(Options.this);
                jsonTask.execute();
                break;
        }
    }

    @Override
    public void onClick(View v)
        {
        final View vf = v;
        vf.startAnimation(buttonAnim);
        buttonAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                nagivateAfterClick(vf);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }
}
