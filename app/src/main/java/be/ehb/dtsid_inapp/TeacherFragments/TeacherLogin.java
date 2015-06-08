package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.Models.TeacherAdapter;
import be.ehb.dtsid_inapp.R;

public class TeacherLogin extends Fragment
{
    TeacherActivity activity;

    private Spinner teacherSP, eventSP;
    private Button loginBTN;

    private DatabaseContract dbc;
    private Animation buttonAnim;
    private TeacherAdapter teacherAdapter;
    private ArrayAdapter<String> eventAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_teacher_login, null);

        activity = (TeacherActivity) this.getActivity();
        dbc = new DatabaseContract(activity.getApplicationContext());

        //Teacher spinner
        teacherSP = (Spinner) v.findViewById(R.id.sp_docent_loginscreen);
        teacherAdapter = new TeacherAdapter(activity, dbc.getAllTeachers());
        teacherSP.setAdapter(teacherAdapter);

        //Event spinner
        final List<String> events = new ArrayList<>();
        for(int i = 0 ; i < dbc.getAllEvents().size() ; i++)
            events.add(dbc.getAllEvents().get(i).getName() + " (" + dbc.getAllEvents().get(i).getAcadyear() + ")");

        eventSP = (Spinner) v.findViewById(R.id.sp_event_loginscreen);
        eventAdapter = new ArrayAdapter<String>(activity.getApplicationContext(), R.layout.event_list_item, events)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(R.id.TV_name_eventSpinnerItem);
                text.setText(getItem(position));
                text.setTextColor(Color.BLACK);
                return view;
            }
        };
        eventSP.setAdapter(eventAdapter);

        //Log in button
        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext()
                , R.anim.button_animation_basic);
        loginBTN = (Button) v.findViewById(R.id.btn_login_loginscreen);
        loginBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                activity.setTeacher(dbc.getAllTeachers().get(teacherSP.getSelectedItemPosition()));
                activity.setEvent(dbc.getAllEvents().get(eventSP.getSelectedItemPosition()));

                dbc.close();

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
                        activity.getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.teacherContainer, new Options())
                                .commit();
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
}
