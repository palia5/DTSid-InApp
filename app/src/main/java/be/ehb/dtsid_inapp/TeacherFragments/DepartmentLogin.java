package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.JSONTasks.PostJSONTask;
import be.ehb.dtsid_inapp.R;

public class DepartmentLogin extends Fragment
{
    TeacherActivity activity;

    private Spinner departmentSP;
    private EditText codeET;
    private Button loginBTN;
    private Animation buttonAnim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_department_login_launchscreen, null);

        activity = (TeacherActivity) this.getActivity();

        departmentSP = (Spinner) v.findViewById(R.id.sp_department_list);
        codeET = (EditText) v.findViewById(R.id.et_code_launchscreen);
        loginBTN = (Button) v.findViewById(R.id.btn_bevestigen_launchscreen);
        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext()
                , R.anim.button_animation_basic);

        loginBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(buttonAnim);
                buttonAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        activity.getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.teacherContainer, new TeacherLogin())
                                .commit();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        return v;
    }
}
