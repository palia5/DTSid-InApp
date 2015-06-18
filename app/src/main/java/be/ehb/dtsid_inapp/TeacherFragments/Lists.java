package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.EventAdapter;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.SubscriptionAdapter;
import be.ehb.dtsid_inapp.R;

/**
 *
 * @author Dorothée
 * @version 1.0
 *
 *
 */

public class Lists extends Fragment
{
    TeacherActivity activity;

    private TextView evenementTV;
    private Spinner evenementSP;
    private ListView studentLV;
    private SubscriptionAdapter subscriptionAdapter;
    private EventAdapter evenementAdapter;
    private DatabaseContract dbc;
    private ArrayList<Subscription> subscriptionArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listscreen, null);

        activity = (TeacherActivity) this.getActivity();
        dbc = new DatabaseContract(activity.getApplicationContext());

        evenementTV = (TextView) v.findViewById(R.id.tv_label_evenementen_listscreen);

        //evenement spinner
        final List<Event> events = new ArrayList<>();
        for(int i = 0 ; i < dbc.getAllEvents().size() ; i++)
            if(dbc.getAllEvents().get(i).getAcadyear() == activity.getCurrentYear())
                events.add(dbc.getAllEvents().get(i));

        evenementSP = (Spinner) v.findViewById(R.id.sp_evenementen_listscreen);
        evenementAdapter = new EventAdapter(activity, events);
        evenementSP.setAdapter(evenementAdapter);

        //lijst van studenten

        ArrayList<Subscription> subscriptionArrayList = new ArrayList<Subscription>(dbc.getAllSubscriptions());

        studentLV = (ListView) v.findViewById(R.id.lv_studenten_opgekozenevenement_listscreen);

        subscriptionAdapter = new SubscriptionAdapter(activity, subscriptionArrayList);


        return v;
    }

}
