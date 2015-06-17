package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
    private ImageView logoIV;
    private LinearLayout btnLinLay;
    private Subscription currentSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_student_registration1_2, container, false);
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
        logoIV = (ImageView) v.findViewById(R.id.iv_logo_ehb);
        btnLinLay = (LinearLayout) v.findViewById(R.id.lin_lay_btn_stud_reg_1);

        if (activity.getCurrentSubscription() == null) {
            clearAllFields();
            setEnabled(false);
        }

        else {
            setAllFields(activity.getCurrentSubscription());
        }

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
                    if (!validEmail(emailET.getText().toString()))
                        Toast.makeText(getActivity(), "E-mail is not valid!", Toast.LENGTH_LONG).show();
                    else
                        for (int i = 0; i < subs.size(); i++)
                            if (emailET.getText().toString().equals(subs.get(i).getEmail()))
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
                    currentSubscription = new Subscription();
                            currentSubscription.setFirstName(voorNaamET.getText().toString());
                            currentSubscription.setLastName(naamET.getText().toString());
                            currentSubscription.setEmail(emailET.getText().toString());
                            currentSubscription.setStreet(straatET.getText().toString());
                            currentSubscription.setStreetNumber(huisNummerET.getText().toString());
                            currentSubscription.setZip(postcodeET.getText().toString());
                            currentSubscription.setCity("Iemand heeft ne spinner gezet bij Gemeente :p");
                            currentSubscription.setTimestamp(new Date());
                            currentSubscription.setTeacher(activity.getTeacher());
                            currentSubscription.setEvent(activity.getEvent());
                    activity.setCurrentSubscription(currentSubscription);

                    activity.setIsInSecondReg(true);
                    activity.getFragmentManager().beginTransaction().
                            replace(R.id.fragm_left_registration, new StudentRegistrationPt2())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        cancelBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearAllFields();
                activity.setCurrentSubscription(null);
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

    private void setAllFields(Subscription s)
    {
        emailET.setText(s.getEmail());
        naamET.setText(s.getLastName());
        voorNaamET.setText(s.getFirstName());
        straatET.setText(s.getStreet());
        huisNummerET.setText(s.getStreetNumber());
        postcodeET.setText(s.getZip());
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
        if (enabled){
            logoIV.setPivotX(0);
            logoIV.setScaleX(1.0f);
            logoIV.setScaleY(1.0f);
            btnLinLay.setVisibility(View.VISIBLE);
        }
        else {
            logoIV.setPivotX(0);
            logoIV.setScaleX(0.65f);
            logoIV.setScaleY(0.65f);
            btnLinLay.setVisibility(View.GONE);
        }
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

    public Subscription getCurrentSubscription() {
        return currentSubscription;
    }
}