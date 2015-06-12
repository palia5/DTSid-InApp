package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.EventAdapter;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.SubscriptionAdapter;
import be.ehb.dtsid_inapp.R;

public class Lists extends Fragment implements AdapterView.OnItemSelectedListener
{
    TeacherActivity activity;

    private TextView evenementTV;
    private Spinner evenementSP;
    private ListView studentLV;
    private SubscriptionAdapter subscriptionAdapter;
    private EventAdapter evenementAdapter;
    private DatabaseContract dbc;
    private ArrayList<Subscription> subscriptionArrayList;
    private Event selectedEvent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_listscreen, null);

        activity = (TeacherActivity) this.getActivity();
        dbc = new DatabaseContract(activity.getApplicationContext());

        evenementTV = (TextView) v.findViewById(R.id.tv_label_evenementen_listscreen);

        //evenement spinner
        List<Event> events = new ArrayList<>();
        List<Event> currentEvents = new ArrayList<>();
        events = dbc.getAllEvents();
        for (int i = 0; i < events.size(); i++)
            if (events.get(i).getAcadyear() == activity.getCurrentYear())
                currentEvents.add(events.get(i));

        evenementSP = (Spinner) v.findViewById(R.id.sp_evenementen_listscreen);
        evenementAdapter = new EventAdapter(activity, currentEvents);
        evenementSP.setAdapter(evenementAdapter);
        selectedEvent = (Event) evenementSP.getSelectedItem();

        //lijst van studenten
        final ArrayList<Subscription> subscriptionArrayList = new ArrayList<Subscription>(dbc.getAllSubscriptions());
        ArrayList<Subscription> requestedSubscriptions = new ArrayList<Subscription>();
        for (int i = 0; i < subscriptionArrayList.size(); i++)
            if (subscriptionArrayList.get(i).getEvent() == selectedEvent)
                requestedSubscriptions.add(subscriptionArrayList.get(i));

        studentLV = (ListView) v.findViewById(R.id.lv_studenten_opgekozenevenement_listscreen);
        subscriptionAdapter = new SubscriptionAdapter(activity, subscriptionArrayList);
        return v;
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //evenementSP.setSelection(position);
        //subscriptionArrayList = (ArrayList<Subscription>) evenementSP.getSelectedItem();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
