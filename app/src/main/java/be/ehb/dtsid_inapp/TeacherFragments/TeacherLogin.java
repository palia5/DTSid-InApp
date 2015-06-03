package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import be.ehb.dtsid_inapp.R;

public class TeacherLogin extends Fragment
{
    private Spinner teacherSP, eventSP;
    private Button loginBTN;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_teacher_login, null);

        teacherSP = (Spinner) v.findViewById(R.id.sp_docent_list);
        eventSP = (Spinner) v.findViewById(R.id.sp_evenement_list);
        loginBTN = (Button) v.findViewById(R.id.btn_teacher_login);


        return v;
    }
}
