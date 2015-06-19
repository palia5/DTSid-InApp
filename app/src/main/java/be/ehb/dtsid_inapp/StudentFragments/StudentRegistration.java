package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Gemeente;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.XmlHandler;
import be.ehb.dtsid_inapp.R;

public class StudentRegistration extends Fragment implements View.OnClickListener
{
    StudentActivity activity;
    List<Subscription> subs;
    DatabaseContract dbc;

    TextView emailTV, naamTV, voorNaamTV,straatTV, huisNummerTV,postcodeTV,gemeenteTV;
    EditText emailET;
    EditText naamET;
    EditText voorNaamET;
    EditText straatET;
    EditText huisNummerET;
    EditText postcodeET, gemeenteET;
    Button acceptBTN;
    Button cancelBTN;
    private Animation buttonAnim;

    //Gemeente auto complete stuff
    private ArrayList<Gemeente> allGemeenten;
    private ArrayList<String> relevanteGemeenteNames;
    private ArrayAdapter gemeenteAdapter;

    private ImageView logoIV;
    private LinearLayout btnLinLay;
    private Subscription currentSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_student_registration1_2, container, false);
        activity = (StudentActivity) this.getActivity();
        allGemeenten = XmlHandler.gemeenteArrayList;

        emailTV = (TextView) v.findViewById(R.id.tv_label_email_subscription1);
        naamTV = (TextView) v.findViewById(R.id.tv_label_naam_subscription1);
        voorNaamTV = (TextView) v.findViewById(R.id.tv_label_voornaam_subscription1);
        straatTV = (TextView) v.findViewById(R.id.tv_label_straat_subscription1);
        huisNummerTV = (TextView) v.findViewById(R.id.tv_label_huisnummer_subscription1);
        postcodeTV = (TextView) v.findViewById(R.id.tv_label_postcode_subscription1);
        gemeenteTV = (TextView) v.findViewById(R.id.tv_label_gemeente_subscription1);
        emailET = (EditText) v.findViewById(R.id.et_email_subscription1);
        naamET = (EditText) v.findViewById(R.id.et_naam_subscription1);
        voorNaamET = (EditText) v.findViewById(R.id.et_voornaam_subscription1);
        straatET = (EditText) v.findViewById(R.id.et_straat_subscription1);
        huisNummerET = (EditText) v.findViewById(R.id.et_huisnummer_subscription1);
        postcodeET = (EditText) v.findViewById(R.id.et_postcode_subscription1);
        gemeenteET = (EditText) v.findViewById(R.id.et_gemeente_subscription1);
        acceptBTN = (Button) v.findViewById(R.id.btn_bevestigen_subscription1);
        cancelBTN = (Button) v.findViewById(R.id.btn_annuleren_subscription1);
        logoIV = (ImageView) v.findViewById(R.id.iv_logo_ehb);
        btnLinLay = (LinearLayout) v.findViewById(R.id.lin_lay_btn_stud_reg_1);
        buttonAnim = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.button_animation_basic);

        Typeface myCustomFont = Typeface.createFromAsset(activity.getAssets()
                , "fonts/ehb_font.ttf");

        emailTV.setTypeface(myCustomFont);
        naamTV.setTypeface(myCustomFont);
        voorNaamTV.setTypeface(myCustomFont);
        straatTV.setTypeface(myCustomFont);
        huisNummerTV.setTypeface(myCustomFont);
        postcodeTV.setTypeface(myCustomFont);
        gemeenteTV.setTypeface(myCustomFont);
        emailET.setTypeface(myCustomFont);
        naamET.setTypeface(myCustomFont);
        voorNaamET.setTypeface(myCustomFont);
        straatET.setTypeface(myCustomFont);
        huisNummerET.setTypeface(myCustomFont);
        postcodeET.setTypeface(myCustomFont);
        gemeenteET.setTypeface(myCustomFont);
        acceptBTN.setTypeface(myCustomFont);
        cancelBTN.setTypeface(myCustomFont);

        //Fix de huisnummer next
        huisNummerET.setNextFocusDownId(R.id.et_postcode_subscription1);

        //Zip Auto Complete
        postcodeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 4){
                    if (getRelevanteGemeentenNames(s.toString()).isEmpty()){
                        Toast.makeText(activity.getApplicationContext(), "Postcode niet gevonden!",
                                Toast.LENGTH_LONG).show();
                        postcodeET.setText("");
                        gemeenteET.setText("");
                    }
                    else {
                        ArrayList relevanteGemeenten = getRelevanteGemeentenNames(s.toString());
                        final String[] relevanteGemeentenArray = (String[]) relevanteGemeenten.toArray(new String[relevanteGemeenten.size()]);
                        android.app.AlertDialog.Builder gemeentenDialogBuilder = new android.app.AlertDialog.Builder(activity);
                        gemeentenDialogBuilder.setTitle("Gemeenten");
                        gemeentenDialogBuilder.setItems(relevanteGemeentenArray, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gemeenteET.setText(relevanteGemeentenArray[which]);

                                //NEEDS TESTING!!!
                                postcodeET.clearFocus();

                                InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                                inputManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                                dialog.dismiss();
                            }
                        });
                        gemeentenDialogBuilder.setNegativeButton("Annuleren", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                postcodeET.setText("");
                                gemeenteET.setText("");
                                dialog.dismiss();
                            }
                        });
                        android.app.AlertDialog gemeentenDialog = gemeentenDialogBuilder.create();
                        gemeentenDialog.show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        if (activity.getCurrentSubscription() == null) 
        {
            clearAllFields();

            setEnabled(false);
        }

        else 
        {
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

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!validEmail(s.toString()))
                    emailET.setBackgroundColor(Color.RED);
                else
                    emailET.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        emailET.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
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

        acceptBTN.setOnClickListener(this);

        cancelBTN.setOnClickListener(this);

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
    }

    public void setEnabled(Boolean enabled)
    {
        emailET.setEnabled(enabled);
        naamET.setEnabled(enabled);
        voorNaamET.setEnabled(enabled);
        straatET.setEnabled(enabled);
        huisNummerET.setEnabled(enabled);
        postcodeET.setEnabled(enabled);
        gemeenteET.setEnabled(enabled);
        acceptBTN.setEnabled(enabled);
        cancelBTN.setEnabled(enabled);
        if (enabled)
        {
            logoIV.setPivotX(0);
            logoIV.setScaleX(1.0f);
            logoIV.setScaleY(1.0f);
            btnLinLay.setVisibility(View.VISIBLE);
        }
        else
        {
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

    private ArrayList<String> getRelevanteGemeentenNames(String postcode){
        ArrayList<String> gemeentenForPostcode = new ArrayList<>();
        for (Gemeente i : allGemeenten){
            if (i.getZip().equals(postcode)){
                gemeentenForPostcode.add(i.getPlaats());
            }
        }
        return gemeentenForPostcode;
    }


    public Subscription getCurrentSubscription() {
        return currentSubscription;
    }

    @Override
    public void onClick(View v) {
        final View finalView = v;
        finalView.startAnimation(buttonAnim);
        buttonAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finalView.setVisibility(View.INVISIBLE);
                navigateAfterClick(finalView);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void navigateAfterClick(View v)
    {
        v.setVisibility(View.VISIBLE);
        switch (v.getId()){
            case R.id.btn_bevestigen_subscription1:
                if(allFieldsOK())
                {
                    currentSubscription = new Subscription();
                    currentSubscription.setFirstName(voorNaamET.getText().toString());
                    currentSubscription.setLastName(naamET.getText().toString());
                    currentSubscription.setEmail(emailET.getText().toString());
                    currentSubscription.setStreet(straatET.getText().toString());
                    currentSubscription.setStreetNumber(huisNummerET.getText().toString());
                    currentSubscription.setZip(postcodeET.getText().toString());
                    currentSubscription.setCity(gemeenteET.getText().toString());
                    currentSubscription.setTimestamp(new Date());
                    currentSubscription.setTeacher(activity.getTeacher());
                    currentSubscription.setEvent(activity.getEvent());
                    currentSubscription.setSchool(dbc.getSchoolByID(5648554290839552l));
                    currentSubscription.setDigx(false);
                    currentSubscription.setMultec(false);
                    currentSubscription.setWerkstudent(false);
                    currentSubscription.setNew(true);
                    activity.setCurrentSubscription(currentSubscription);

                    activity.setIsInSecondReg(true);
                    activity.getFragmentManager().beginTransaction().
                            replace(R.id.fragm_left_registration, new StudentRegistrationPt2())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            case R.id.btn_annuleren_subscription1:
                clearAllFields();
                activity.setCurrentSubscription(null);
                activity.onBackPressed();
                break;

        }
    }

}