package be.ehb.dtsid_inapp.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.StudentFragments.PhotoGallery;
import be.ehb.dtsid_inapp.StudentFragments.StudentRegistration;

public class StudentActivity extends AppCompatActivity
{
    private Boolean isInMainScreen = true;

    EditText emailET;
    EditText naamET;
    EditText voorNaamET;
    EditText straatET;
    EditText huisNummerET;
    EditText postcodeET;
    Spinner gemeenteSP;
    Button bevestigenBTN;
    Button annulerenBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        emailET = (EditText) findViewById(R.id.et_email_subscription1);
        naamET = (EditText) findViewById(R.id.et_naam_subscription1);
        voorNaamET = (EditText) findViewById(R.id.et_voornaam_subscription1);
        straatET = (EditText) findViewById(R.id.et_straat_subscription1);
        huisNummerET = (EditText) findViewById(R.id.et_huisnummer_subscription1);
        postcodeET = (EditText) findViewById(R.id.et_postcode_subscription1);
        gemeenteSP = (Spinner) findViewById(R.id.sp_gemeente_subscription1);
        bevestigenBTN = (Button) findViewById(R.id.btn_bevestigen_subscription1);
        annulerenBTN = (Button) findViewById(R.id.btn_annuleren_subscription1);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.fragm_left_registration, new StudentRegistration());
        ft.add(R.id.fragm_right_images, new PhotoGallery());
        ft.commit();
    }

    @Override
    public void onBackPressed()
    {
        if(isInMainScreen)
            super.onBackPressed();
        else
        {
            changeWeightOfFragments(50, 50);
            isInMainScreen = true;
        }
    }

    public void leftTouched()
    {
        isInMainScreen = false;
        changeWeightOfFragments(100, 0);
    }

    public void rightTouched()
    {
        isInMainScreen = false;
        changeWeightOfFragments(0, 100);
    }

    private void changeWeightOfFragments(float weightLeftFragment, float weigthRightFragment)
    {
        //Set registration weight
        FrameLayout flRegistration = (FrameLayout) findViewById(R.id.fragm_left_registration);
        LinearLayout.LayoutParams lpRegistration = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                weightLeftFragment);
        flRegistration.setLayoutParams(lpRegistration);

        //Set images weight
        FrameLayout flImages = (FrameLayout) findViewById(R.id.fragm_right_images);
        LinearLayout.LayoutParams lpImages = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                weigthRightFragment);
        flImages.setLayoutParams(lpImages);
    }

    private void disableEditTeksts() {
        //if (emailET.isEnabled()){
            emailET.setEnabled(false);
            naamET.setEnabled(false);
            voorNaamET.setEnabled(false);
            straatET.setEnabled(false);
            huisNummerET.setEnabled(false);
            postcodeET.setEnabled(false);
            gemeenteSP.setEnabled(false);
            bevestigenBTN.setEnabled(false);
            annulerenBTN.setEnabled(false);
        //}
    }

    private void enableEditTeksts()
    {
        //if (!emailET.isEnabled()) {
            emailET.setEnabled(true);
            naamET.setEnabled(true);
            voorNaamET.setEnabled(true);
            straatET.setEnabled(true);
            huisNummerET.setEnabled(true);
            postcodeET.setEnabled(true);
            gemeenteSP.setEnabled(true);
            bevestigenBTN.setEnabled(true);
            annulerenBTN.setEnabled(true);
        //}
    }
}
