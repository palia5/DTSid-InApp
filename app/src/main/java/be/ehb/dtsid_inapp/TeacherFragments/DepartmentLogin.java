package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import be.ehb.dtsid_inapp.R;

public class DepartmentLogin extends Fragment
{
    private Spinner departmentSP;
    private EditText codeET;
    private Button loginBTN;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_department_login_launchscreen, null);

        departmentSP = (Spinner) v.findViewById(R.id.sp_department_list);
        codeET = (EditText) v.findViewById(R.id.et_code_launchscreen);
        loginBTN = (Button) v.findViewById(R.id.btn_department_login);

        return v;
    }
}
