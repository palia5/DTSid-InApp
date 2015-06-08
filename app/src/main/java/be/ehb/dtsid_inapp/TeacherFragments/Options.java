package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.PostJSONTask;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;

public class Options extends Fragment
{
    TeacherActivity activity;
    private DatabaseContract dbc;
    private ProgressDialog loadingDatabaseDialog;

    Button studentRegistrerenBTN;
    Button lijstBTN;
    Button regioBTN;
    Button optiesBTN;
    Button syncBTN;
    TextView departementTV;
    TextView medewerkerTV;
    TextView evenementTV;
    TextView aantalStudentenTV;
    TextView laatsteSyncTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_options_dashboardscreen, null);

        activity = (TeacherActivity) this.getActivity();

        //Contract opvrage
        dbc = new DatabaseContract(activity.getApplicationContext());

        activity = (TeacherActivity) this.getActivity();

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

        studentRegistrerenBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent studentIntent = new Intent(activity.getApplicationContext(), StudentActivity.class);
                studentIntent.putExtra("Teacher_id", activity.getTeacher().getId());
                studentIntent.putExtra("Event_id", activity.getEvent().getId());
                startActivity(studentIntent);
            }
        });

        syncBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadingDatabaseDialog.show();

                PostJSONTask jsonTask = new PostJSONTask(Options.this);
                jsonTask.execute();
            }
        });

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
}
