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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.R;

public class TeacherLogin extends Fragment
{
    private Spinner teacherSP, eventSP;
    private Button loginBTN;

    private DatabaseContract dbc;
    ArrayAdapter<Teacher> teacherAdapter;
    ArrayAdapter<Event> eventAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_teacher_login, null);

        dbc = new DatabaseContract(getActivity().getApplicationContext());

        //Teacher spinner
        teacherSP = (Spinner) v.findViewById(R.id.sp_docent_loginscreen);
        teacherAdapter = new ArrayAdapter<Teacher>(getActivity().getApplicationContext(), R.layout.teachers_list_item, dbc.getAllTeachers())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(R.id.TV_name_teacherSpinnerItem);
                text.setText(dbc.getAllTeachers().get(position).getName());
                text.setTextColor(Color.BLACK);
                return view;
            }
        };
        teacherSP.setAdapter(teacherAdapter);

        //Event spinner
        eventSP = (Spinner) v.findViewById(R.id.sp_event_loginscreen);
        eventAdapter = new ArrayAdapter<Event>(getActivity().getApplicationContext(), R.layout.event_list_item, dbc.getAllEvents())
        {
            @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = super.getView(position, convertView, parent);
            TextView text = (TextView) view.findViewById(R.id.TV_name_eventSpinnerItem);
            text.setText(dbc.getAllEvents().get(position).getName());
            text.setTextColor(Color.BLACK);
            return view;
        }
    };
        eventSP.setAdapter(eventAdapter);

        //Log in button
        loginBTN = (Button) v.findViewById(R.id.btn_teacher_login);

        dbc.close();

        return v;
    }
}
