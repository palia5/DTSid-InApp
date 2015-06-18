package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;

import be.ehb.dtsid_inapp.Activities.TeacherActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.JSONTasks.GetImagesJSONTask;
import be.ehb.dtsid_inapp.JSONTasks.GetJSONTask;
import be.ehb.dtsid_inapp.Models.Gemeente;
import be.ehb.dtsid_inapp.R;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;
import static be.ehb.dtsid_inapp.Database.MySQLiteHelper.*;

public class DepartmentLogin extends Fragment
{
    TeacherActivity activity;
    private DatabaseContract dbc;
    private ProgressDialog loadingDatabaseDialog;

    private TextView codeTV;
    private EditText codeET;
    private Button loginBTN;
    private Boolean loadingSubscriptions = false;
    private Animation buttonAnim;
    private String baseURL, secret;

    private Handler depCodeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            fillDB();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_department_login_launchscreen, null);

        activity = (TeacherActivity) this.getActivity();
        dbc = new DatabaseContract(activity.getApplicationContext());

        loadingDatabaseDialog = new ProgressDialog(activity);
        loadingDatabaseDialog.setTitle("Downloading database");
        loadingDatabaseDialog.setMessage("Loading.. pls stahp..");

        codeTV = (TextView) v.findViewById(R.id.tv_label_code_launchscreen);
        codeET = (EditText) v.findViewById(R.id.et_code_launchscreen);
        loginBTN = (Button) v.findViewById(R.id.btn_bevestigen_launchscreen);

        Typeface myCustomFont = Typeface.createFromAsset(activity.getAssets()
                , "fonts/ehb_font.ttf");
        codeTV.setTypeface(myCustomFont);
        loginBTN.setTypeface(myCustomFont);
        codeET.setTypeface(myCustomFont);

        buttonAnim = AnimationUtils.loadAnimation(getActivity().getApplicationContext()
                , R.anim.button_animation_basic);

        loginBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(buttonAnim);
                buttonAnim.setAnimationListener(new Animation.AnimationListener()
                {
                    @Override
                    public void onAnimationStart(Animation animation)
                    {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        loginBTN.setVisibility(View.INVISIBLE);

                        if (!dbc.getAllSubscriptions().isEmpty())
                        {
                            everythingIsLoaded(true);
                            return;
                        }
                        else
                            loadingDatabaseDialog.show();

                        //Start JSONS
                        if (dbc.getAllTeachers().isEmpty())
                        {
                            String urlTeachers = BASEURL + ALL_TEACHERS;
                            startMyTask(urlTeachers);
                        }
                        if (dbc.getAllEvents().isEmpty())
                        {
                            String urlEvents = BASEURL + ALL_EVENTS;
                            startMyTask(urlEvents);
                        }
                        if (dbc.getAllSchools().isEmpty())
                        {
                            String urlSchools = BASEURL + ALL_SCHOOLS;
                            startMyTask(urlSchools);
                        }/*
                        if (dbc.getAllImages().isEmpty())
                        {
                            String urlImages = BASEURL + ALL_IMAGES;
                            startMyTask(urlImages);
                        }*/

                                   URL url;
                                   try {
                                       url = new URL("http://deptcodes.appspot.com/deptcode/"+secret);
                                       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                       connection.setRequestMethod("GET");
                                       connection.connect();

                                       Log.d("TEST connection", connection.toString());

                                       BufferedReader reader = new BufferedReader(
                                               new InputStreamReader(connection.getInputStream()));
                                       baseURL = "http://" +  reader.readLine();
                                       Log.d("TEST getBaseUrl", baseURL);

                                       Log.d("TEST", "sending message");
                                       depCodeHandler.sendEmptyMessage(0);

                               } catch (MalformedURLException e) {
                                   e.printStackTrace();
                               } catch (ProtocolException e) {
                                   e.printStackTrace();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                               }
                           });
                        workerThread.start();


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }
                });
            }
        });

        return v;
    }

    public void fillDB()
    {
        if (!dbc.getAllSubscriptions().isEmpty() && secret.equals(getStringFromSharedPrefs("SECRET"))) {
            everythingIsLoaded(true);
            return;
        } else if (!baseURL.equals("http://null")) {
            putStringInSharedPrefs("BASEURL", baseURL);
            putStringInSharedPrefs("SECRET", secret);
            loadingDatabaseDialog.show();

            //Start JSONS
            if (dbc.getAllTeachers().isEmpty()) {
                String urlTeachers = baseURL + ALL_TEACHERS;
                startMyTask(urlTeachers);
            }
            if (dbc.getAllEvents().isEmpty()) {
                String urlEvents = baseURL + ALL_EVENTS;
                startMyTask(urlEvents);
            }
            if (dbc.getAllSchools().isEmpty()) {
                String urlSchools = baseURL + ALL_SCHOOLS;
                startMyTask(urlSchools);
            }

            everythingIsLoaded(false);
        }
        else {
            Toast.makeText(activity.getApplicationContext(),
                    "Onbestaande code",Toast.LENGTH_LONG).show();
            codeET.setText(null);
        }
    }

    public void everythingIsLoaded(Boolean subscriptionLoaded)
    {
        if(subscriptionLoaded && !dbc.getAllImages().isEmpty())
        {
            dbc.close();

            loadingDatabaseDialog.dismiss();

            activity.setCurrentYear(Integer.parseInt(yearCalc().substring(1)));

            activity.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.teacherContainer, new TeacherLogin(), "TEACHER_LOGIN")
                    .commit();
        }

        else if(!dbc.getAllTeachers().isEmpty() && !dbc.getAllEvents().isEmpty() && !dbc.getAllSchools().isEmpty() && loadingSubscriptions == false)
        {
            String urlSubscriptions = baseURL + ALL_SUBSCRIPTIONS;
            startMyTask(urlSubscriptions);
            loadingSubscriptions = true;

            if (dbc.getAllImages().isEmpty())
            {
                String urlImages = baseURL + ALL_IMAGES;
                GetImagesJSONTask imagesJSONTask = new GetImagesJSONTask(DepartmentLogin.this);
                imagesJSONTask.execute(urlImages);
            }
        }
    }

    public void noInternet()
    {
        loadingDatabaseDialog.dismiss();
        //Toast.makeText(activity, "NO INTERNET" , Toast.LENGTH_SHORT).show();
    }

    // PARALLEL ASYNCS
    void startMyTask(String url)
    {
        GetJSONTask jsonTask = new GetJSONTask(DepartmentLogin.this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            jsonTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        else
            jsonTask.execute(url);
    }

    private void putStringInSharedPrefs(String tag, String value){
        PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext())
                .edit().putString(tag, value);
    }

    private String getStringFromSharedPrefs(String tag){
        String storedSecret = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext())
                .getString("SECRET", null);
        return storedSecret;
    }




    private class asyncXml extends AsyncTask{

        private DatabaseContract xmldbc;

        @Override
        protected Object doInBackground(Object[] params) {
            //xmlToSqlite();
            return null;
        }/*
        void xmlToSqlite(){

            Log.d("Test_", "in methode xmltosql");
            Resources res = activity.getResources();
            InputStream streamToXml =  res.openRawResource(R.raw.postcodes);
            //XmlResourceParser parser =
            SAXParser parser
            Gemeente tempGemeente = new Gemeente();
            xmldbc = new DatabaseContract(activity.getApplicationContext());

            try {

                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }
                    String name = parser.getName();
                    if (name.equals("record")) {
                        String postcode = null, plaats = null, prov = null;
                        while (parser.next() != XmlPullParser.END_TAG) {
                            //if (parser.getEventType() != XmlPullParser.START_TAG) {
                                //continue;
                            //}
                            name = parser.getName();
                            if (name.equalsIgnoreCase("Postcode")) {
                                postcode = readText(parser);
                                tempGemeente.setZip(postcode);
                                Log.d("Test_", postcode);
                            } else if (name.equalsIgnoreCase("Plaatsnaam")) {
                                plaats = readText(parser);
                                tempGemeente.setPlaats(plaats);
                                Log.d("Test_", plaats);
                            } else if (name.equalsIgnoreCase("Provincie")) {
                                prov = readText(parser);
                                tempGemeente.setProvincie(prov);
                                Log.d("Test_", prov);
                            }
                            if (xmldbc != null) {
                                xmldbc.createGemeente(tempGemeente);
                                Log.d("Test_", "wgschrijven naar db");
                            }
                        }

                    }
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            xmldbc.close();
        }

        private String readText(XmlPullParser parser) throws IOException,
                XmlPullParserException {
            String result = "";
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
            }
            return result;
        }*/
    }

}