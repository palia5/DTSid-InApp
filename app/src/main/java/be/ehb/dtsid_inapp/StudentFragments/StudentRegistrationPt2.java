package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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
import android.widget.Switch;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.JSONTasks.PostJSONTask;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.TeacherFragments.Lists;
import be.ehb.dtsid_inapp.TeacherFragments.OptionsPreferences;

/**
 * Created by tomnahooy on 11/06/15.
 */
public class StudentRegistrationPt2 extends Fragment implements View.OnClickListener {

    private Switch digxSW, multecSW, werkstudentSW, studeerNogSW;
    private EditText schoolZipET;
    private Spinner schoolNameSPIN;
    private Button confirmBTN, backBTN, cancelBTN;
    private Animation buttonAnim;
    private Subscription currentSubscription;
    private StudentActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_registration2_2, container, false);

        activity = (StudentActivity) this.getActivity();
        digxSW = (Switch) v.findViewById(R.id.sw_stud_reg_digx);
        multecSW = (Switch) v.findViewById(R.id.sw_stud_reg_multec);
        werkstudentSW = (Switch) v.findViewById(R.id.sw_stud_reg_werkstudent);
        studeerNogSW = (Switch) v.findViewById(R.id.sw_ik_studeer_nog);
        schoolZipET = (EditText) v.findViewById(R.id.et_stud_reg_2_zip);
        schoolNameSPIN = (Spinner) v.findViewById(R.id.spin_stud_reg_2_name_school);
        confirmBTN = (Button) v.findViewById(R.id.btn_bevestigen_subscription2);
        backBTN = (Button) v.findViewById(R.id.btn_stud_reg_2_back);
        cancelBTN = (Button) v.findViewById(R.id.btn_annuleren_subscription2);
        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.button_animation_large);

        backBTN.setOnClickListener(this);

        currentSubscription = activity.getCurrentSubscription();


        return v;
    }

    private void nagivateAfterClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_stud_reg_2_back:

                activity.onBackPressed();

                break;
        }
    }

    @Override
    public void onClick(View v)
    {
        final View vf = v;
        vf.startAnimation(buttonAnim);
        buttonAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                vf.setVisibility(View.INVISIBLE);
                nagivateAfterClick(vf);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }
}
