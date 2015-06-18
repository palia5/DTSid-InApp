package be.ehb.dtsid_inapp.StudentFragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.PostJSONTask;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.TeacherFragments.Lists;
import be.ehb.dtsid_inapp.TeacherFragments.OptionsPreferences;

public class StudentRegistrationPt2 extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Switch digxSW, multecSW, werkstudentSW, studeerNogSW;
    private TextView schoolZipTV, schoolNameTV,interestTV;
    private EditText schoolZipET;
    private Spinner schoolNameSPIN;
    private Button confirmBTN, backBTN, cancelBTN;
    private Animation buttonAnim;
    private Subscription currentSubscription;
    private StudentActivity activity;
    private DatabaseContract dbc;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_student_registration2_2, container, false);

        activity = (StudentActivity) this.getActivity();
        digxSW = (Switch) v.findViewById(R.id.sw_stud_reg_digx);
        multecSW = (Switch) v.findViewById(R.id.sw_stud_reg_multec);
        werkstudentSW = (Switch) v.findViewById(R.id.sw_stud_reg_werkstudent);
        studeerNogSW = (Switch) v.findViewById(R.id.sw_ik_studeer_nog);
        schoolNameTV = (TextView) v.findViewById(R.id.tv_stud_reg_2_name_school);
        schoolZipTV = (TextView) v.findViewById(R.id.tv_stud_reg_2_zip);
        schoolZipET = (EditText) v.findViewById(R.id.et_stud_reg_2_zip);
        schoolNameSPIN = (Spinner) v.findViewById(R.id.spin_stud_reg_2_name_school);
        confirmBTN = (Button) v.findViewById(R.id.btn_bevestigen_subscription2);
        backBTN = (Button) v.findViewById(R.id.btn_stud_reg_2_back);
        cancelBTN = (Button) v.findViewById(R.id.btn_annuleren_subscription2);
        interestTV = (TextView) v.findViewById(R.id.tv_stud_reg_2_interests);
        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.button_animation_large);

        Typeface myCustomFont = Typeface.createFromAsset(activity.getAssets()
                , "fonts/ehb_font.ttf");

        digxSW.setTypeface(myCustomFont);
        multecSW.setTypeface(myCustomFont);
        werkstudentSW.setTypeface(myCustomFont);
        studeerNogSW.setTypeface(myCustomFont);
        schoolNameTV.setTypeface(myCustomFont);
        schoolZipTV.setTypeface(myCustomFont);
        schoolZipET.setTypeface(myCustomFont);
        confirmBTN.setTypeface(myCustomFont);
        backBTN.setTypeface(myCustomFont);
        cancelBTN.setTypeface(myCustomFont);
        interestTV.setTypeface(myCustomFont);

        confirmBTN.setOnClickListener(this);
        backBTN.setOnClickListener(this);

        enableSchoolDetails(false);

        digxSW.setOnCheckedChangeListener(this);
        multecSW.setOnCheckedChangeListener(this);
        werkstudentSW.setOnCheckedChangeListener(this);
        studeerNogSW.setOnCheckedChangeListener(this);

        currentSubscription = activity.getCurrentSubscription();

        //Create the AlertDialogBuilder
        builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbc = new DatabaseContract(activity.getApplicationContext());
                dbc.createSubscription(currentSubscription);
                dbc.close();
                Log.d("StudReg2", "digx: " + currentSubscription.getDigx()
                        + "multec: " + currentSubscription.getMultec()
                        + "werkstud: " + currentSubscription.getWerkstudent()
                        + currentSubscription.getFirstName());
                dialog.dismiss();
                activity.finish();
                startActivity(activity.getIntent());
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        return v;
    }

    private void nagivateAfterClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_stud_reg_2_back:

                activity.onBackPressed();
                break;

            case R.id.btn_bevestigen_subscription2:
                builder .setMessage("Signing in - Is this data correct?")
                        .setTitle("INSERT SUBSCRIPTION HERE");
                dialog = builder.create();
                dialog.show();
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sw_stud_reg_digx:
                currentSubscription.setDigx(isChecked);
                break;
            case R.id.sw_stud_reg_multec:
                currentSubscription.setMultec(isChecked);
                break;
            case R.id.sw_stud_reg_werkstudent:
                currentSubscription.setWerkstudent(isChecked);
                break;
            case R.id.sw_ik_studeer_nog:
                enableSchoolDetails(isChecked);
                break;
        }
    }

    private void enableSchoolDetails(boolean isChecked) {
        schoolZipTV.setEnabled(isChecked);
        schoolZipET.setEnabled(isChecked);
        schoolNameTV.setEnabled(isChecked);
        schoolNameSPIN.setEnabled(isChecked);
        if (!isChecked)
            currentSubscription.setSchool(dbc.getSchoolByID(5648554290839552l));
    }
}
