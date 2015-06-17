package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class Lists extends Fragment implements AdapterView.OnItemSelectedListener
{
    TeacherActivity activity;

    private TextView evenementTV;
    private Spinner evenementSP;
    private ListView studentLV;
    private EventAdapter evenementAdapter;
    private DatabaseContract dbc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listscreen, null);

        activity = (TeacherActivity) this.getActivity();
        dbc = new DatabaseContract(activity.getApplicationContext());

        //evenement spinner
        final List<Event> events = new ArrayList<>();
        for(int i = 0 ; i < dbc.getAllEvents().size() ; i++)
            if(dbc.getAllEvents().get(i).getAcadyear() == activity.getCurrentYear())
                events.add(dbc.getAllEvents().get(i));

        evenementSP = (Spinner) v.findViewById(R.id.sp_evenementen_listscreen);
        evenementAdapter = new EventAdapter(activity, events);
        evenementSP.setAdapter(evenementAdapter);
        evenementSP.setOnItemSelectedListener(this);

        evenementTV = (TextView) v.findViewById(R.id.tv_label_evenementen_listscreen);
        studentLV = (ListView) v.findViewById(R.id.lv_studenten_opgekozenevenement_listscreen);

        Typeface myCustomFont = Typeface.createFromAsset(activity.getAssets()
                , "fonts/ehb_font.ttf");

        evenementTV.setTypeface(myCustomFont);

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Event selectedEvent = (Event) evenementSP.getSelectedItem();
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        for(Subscription sub : dbc.getAllSubscriptions())
        {
            if(sub.getEvent().getId().equals(selectedEvent.getId()))
            {
                subscriptions.add(sub);
            }
        }

        SubscriptionAdapter adapter = new SubscriptionAdapter(activity, subscriptions);
        studentLV.setAdapter(adapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}