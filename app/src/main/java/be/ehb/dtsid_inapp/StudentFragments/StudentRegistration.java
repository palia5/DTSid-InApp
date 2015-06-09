package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.R;

public class StudentRegistration extends Fragment
{
    StudentActivity activity;
    List<Subscription> subs;
    DatabaseContract dbc;

    EditText emailET;
    EditText naamET;
    EditText voorNaamET;
    EditText straatET;
    EditText huisNummerET;
    EditText postcodeET;
    Spinner gemeenteSP;
    Button acceptBTN;
    Button cancelBTN;

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
        acceptBTN = (Button) v.findViewById(R.id.btn_bevestigen_subscription1);
        cancelBTN = (Button) v.findViewById(R.id.btn_annuleren_subscription1);

        setEnabled(false);
        clearAllFields();

        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                dbc = new DatabaseContract(activity.getApplicationContext());
                subs = new ArrayList<>();
                subs = dbc.getAllSubscriptions();
                activity.leftTouched();
                setEnabled(true);
                return true;
            }
        });

        emailET.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!validEmail(s.toString()))
                    emailET.setBackgroundColor(Color.RED);
                else
                    emailET.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });

        emailET.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String email = emailET.getText().toString();
                    if (validEmail(email))
                    {
                        DatabaseContract dbc = new DatabaseContract(activity.getApplicationContext());
                        List<Subscription> subs = new ArrayList<>();

                        for(int i = 0 ; i < subs.size() ; i++)
                            if(email == subs.get(i).getEmail())
                            {
                                naamET.setText(subs.get(i).getLastName());
                                voorNaamET.setText(subs.get(i).getFirstName());
                                straatET.setText(subs.get(i).getStreet());
                                huisNummerET.setText(subs.get(i).getStreetNumber());
                                postcodeET.setText(subs.get(i).getZip());
                            }
                }
            }
        });

        acceptBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(allFieldsOK())
                {
                    Subscription newSub = new Subscription(
                            voorNaamET.getText().toString(),
                            naamET.getText().toString(),
                            emailET.getText().toString(),
                            straatET.getText().toString(),
                            huisNummerET.getText().toString(),
                            postcodeET.getText().toString(),
                            "Iemand heeft ne spinner gezet bij Gemeente :p",
                            false,
                            false,
                            false,
                            new Date(),
                            activity.getTeacher(),
                            activity.getEvent(),
                            true,
                            dbc.getAllSchools().get(1)
                    );

                    dbc.createSubscription(newSub);

                    dbc.close();

                    clearAllFields();
                    activity.onBackPressed();
                }
            }
        });

        cancelBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearAllFields();
                activity.onBackPressed();
            }
        });

        return v;
    }

    private boolean validEmail(String email)
    {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void clearAllFields()
    {
        emailET.setText("");
        naamET.setText("");
        voorNaamET.setText("");
        straatET.setText("");
        huisNummerET.setText("");
        postcodeET.setText("");
        emailET.setBackgroundColor(Color.TRANSPARENT);
    }

    public void setEnabled(Boolean enabled)
    {
        emailET.setEnabled(enabled);
        naamET.setEnabled(enabled);
        voorNaamET.setEnabled(enabled);
        straatET.setEnabled(enabled);
        huisNummerET.setEnabled(enabled);
        postcodeET.setEnabled(enabled);
        gemeenteSP.setEnabled(enabled);
        acceptBTN.setEnabled(enabled);
        cancelBTN.setEnabled(enabled);
    }

    private Boolean allFieldsOK()
    {
        String whatsWrong = "/";
        if(!validEmail(emailET.getText().toString()))
            whatsWrong += " e-mail not valid /";
        if(naamET.getText().toString().equals(""))
            whatsWrong += " name not entered /";
        if(voorNaamET.getText().toString().equals(""))
            whatsWrong += " first name not entered /";

        if(!whatsWrong.equals("/"))
        {
            Toast.makeText(getActivity(), whatsWrong, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}