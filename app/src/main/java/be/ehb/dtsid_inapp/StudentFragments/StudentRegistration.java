package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.R;

public class StudentRegistration extends Fragment
{
    EditText emailET;
    EditText naamET;
    EditText voorNaamET;
    EditText straatET;
    EditText huisNummerET;
    EditText postcodeET;
    Spinner gemeenteSP;
    Button bevestigenBTN;
    Button annulerenBTN;

    StudentActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_student_registration1_2, null);
        activity = (StudentActivity) this.getActivity();

        emailET = (EditText) v.findViewById(R.id.et_email_subscription1);
        naamET = (EditText) v.findViewById(R.id.et_naam_subscription1);
        voorNaamET = (EditText) v.findViewById(R.id.et_voornaam_subscription1);
        straatET = (EditText) v.findViewById(R.id.et_straat_subscription1);
        huisNummerET = (EditText) v.findViewById(R.id.et_huisnummer_subscription1);
        postcodeET = (EditText) v.findViewById(R.id.et_postcode_subscription1);
        gemeenteSP = (Spinner) v.findViewById(R.id.sp_gemeente_subscription1);
        bevestigenBTN = (Button) v.findViewById(R.id.btn_bevestigen_subscription1);
        annulerenBTN = (Button) v.findViewById(R.id.btn_annuleren_subscription1);

        disableEditTeksts();

        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                activity.leftTouched();
                enableEditTeksts();
                return true;
            }
        });

        emailET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String email = emailET.getText().toString();
                    if (validEmail(email)) {
                        //check if the email is in the database already
                    } else {
                        emailET.setBackgroundColor(Color.RED);
                        Toast.makeText(getActivity(), "email is not valid", Toast.LENGTH_LONG).show();
                    }
                }
                else if (hasFocus){
                    emailET.setBackgroundColor(Color.rgb(207,203,203));
                }
            }
        });

        return v;
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void disableEditTeksts() {
        emailET.setEnabled(false);
        naamET.setEnabled(false);
        voorNaamET.setEnabled(false);
        straatET.setEnabled(false);
        huisNummerET.setEnabled(false);
        postcodeET.setEnabled(false);
        gemeenteSP.setEnabled(false);
        bevestigenBTN.setEnabled(false);
        annulerenBTN.setEnabled(false);
    }

    public void enableEditTeksts()
    {
        emailET.setEnabled(true);
        naamET.setEnabled(true);
        voorNaamET.setEnabled(true);
        straatET.setEnabled(true);
        huisNummerET.setEnabled(true);
        postcodeET.setEnabled(true);
        gemeenteSP.setEnabled(true);
        bevestigenBTN.setEnabled(true);
        annulerenBTN.setEnabled(true);
    }


}
