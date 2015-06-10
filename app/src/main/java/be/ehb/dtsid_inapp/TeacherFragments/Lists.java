package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.SubscriptionAdapter;
import be.ehb.dtsid_inapp.R;

/**
 * Created by doortje on 9/06/15.
 */
public class Lists extends Fragment {

    TeacherActivity activity;

    private TextView evenementTV;
    private Spinner evenementSP;
    private ListView studentLV;
    private SubscriptionAdapter subscriptionAdapter;
    private ArrayAdapter<String> evenementAdapter;
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
        final List<String> events = new ArrayList<>();
        for (int i = 0; i < dbc.getAllEvents().size(); i++)
            events.add(dbc.getAllEvents().get(i).getName() +
                    " (" + dbc.getAllEvents().get(i).getAcadyear() + ")");

        evenementSP = (Spinner) v.findViewById(R.id.sp_evenementen_listscreen);
        evenementAdapter = new ArrayAdapter<String>(activity.getApplicationContext(), R.layout.event_list_item, events) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(R.id.TV_name_eventSpinnerItem);
                text.setText(getItem(position));
                text.setTextColor(Color.BLACK);
                return view;
            }
        };
        evenementSP.setAdapter(evenementAdapter);

        //lijst van studenten

        ArrayList<Subscription> subscriptionArrayList = new ArrayList<Subscription>(dbc.getAllSubscriptions());

        studentLV = (ListView) v.findViewById(R.id.lv_studenten_opgekozenevenement_listscreen);

        subscriptionAdapter = new SubscriptionAdapter(activity, subscriptionArrayList);


        return v;
    }

}
