package be.ehb.dtsid_inapp.JSONTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class GetJSONTask extends AsyncTask<String, Integer, Void>
{
    private DatabaseContract dbc;
    private ProgressDialog loadingDatabaseDialog;
    private DepartmentLogin fragment;

    public GetJSONTask(DepartmentLogin c)
    {
        fragment = c;
        dbc = new DatabaseContract(fragment.getActivity().getApplicationContext());
        loadingDatabaseDialog = new ProgressDialog(fragment.getActivity());
        loadingDatabaseDialog.setTitle("Downloading database");
        loadingDatabaseDialog.setMessage("Loading.. pls stahp..");
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        loadingDatabaseDialog.show();
    }

    @Override
    protected Void doInBackground(String... params)
    {
        try
        {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String jsonString = reader.readLine();

            if (params[0].contains(ALL_TEACHERS))
            {
                JSONObject rawTeachers = new JSONObject(jsonString);
                JSONArray teachersArray = rawTeachers.getJSONArray(JSON_NAME_TEACHERS);
                ArrayList<Teacher> teacherList = new ArrayList<>();

                for (int i = 0; i < teachersArray.length(); i++)
                {
                    JSONObject o = teachersArray.getJSONObject(i);
                    Teacher temp = new Teacher(o.getLong(JSON_LONG_ID),
                            o.getString(JSON_STRING_NAME), o.getInt(JSON_INT_ACADYEAR));
                    teacherList.add(temp);
                }

                dbc.setAllTeachers(teacherList);
            }
            else if (params[0].contains(ALL_EVENTS))
            {
                JSONObject rawEvents = new JSONObject(jsonString);
                JSONArray eventsArray = rawEvents.getJSONArray(JSON_NAME_EVENTS);
                ArrayList<Event> eventList = new ArrayList<>();

                for (int i = 0; i < eventsArray.length(); i++)
                {
                    JSONObject o = eventsArray.getJSONObject(i);
                    Event temp = new Event(o.getLong(JSON_LONG_ID), o.getString(JSON_STRING_NAME),
                            o.getInt(JSON_INT_ACADYEAR));
                    eventList.add(temp);
                }

                dbc.setAllEvents(eventList);
            }
            else if (params[0].contains(ALL_SCHOOLS))
            {
                JSONObject rawSchools = new JSONObject(jsonString);
                JSONArray schoolsArray = rawSchools.getJSONArray(JSON_NAME_SCHOOLS);
                ArrayList<School> schoolList = new ArrayList<>();

                for (int i = 0; i < schoolsArray.length(); i++)
                {
                    JSONObject o = schoolsArray.getJSONObject(i);
                    School temp = new School(o.getLong(JSON_LONG_ID), o.getString(JSON_STRING_NAME),
                            o.getString(JSON_STRING_GEMEENTE), o.getInt(JSON_STRING_POSTCODE));
                    schoolList.add(temp);
                }

                dbc.setAllSchools(schoolList);
            }
            else if (params[0].contains(ALL_SUBSCRIPTIONS)){
                JSONObject rawSubs = new JSONObject(jsonString);
                JSONArray subsArray = rawSubs.getJSONArray(JSON_NAME_SUBSCRIPTIONS);
                ArrayList<Subscription> subsList = new ArrayList<>();

                for (int i = 0; i< subsArray.length(); i++){
                    JSONObject o = subsArray.getJSONObject(i);
                    JSONObject oInterests = o.getJSONObject(JSON_NAME_INTERESTS);
                    JSONObject oTeacher = o.getJSONObject(JSON_NAME_TEACHER);
                    JSONObject oSchool = o.getJSONObject(JSON_NAME_SCHOOL);
                    JSONObject oEvent = o.getJSONObject(JSON_NAME_EVENT);

                    Subscription temp = new Subscription(o.getString(JSON_STRING_FIRSTNAME),
                            o.getString(JSON_STRING_LASTNAME), o.getString(JSON_STRING_EMAIL),
                            o.getString(JSON_STRING_STREET), o.getString(JSON_STRING_STREETNUMBER),
                            o.getString(JSON_STRING_ZIP), o.getString(JSON_STRING_CITY),
                            Boolean.parseBoolean(oInterests.getString(JSON_STRING_DIGX)),
                            Boolean.parseBoolean(oInterests.getString(JSON_STRING_MULTEC)),
                            Boolean.parseBoolean(oInterests.getString(JSON_STRING_WERKSTUDENT)),
                            new Date(o.getLong(JSON_LONG_TIMESTAMP)),
                            dbc.getTeacherByID(oTeacher.getLong(JSON_LONG_ID)),
                            dbc.getEventByID(oEvent.getLong(JSON_LONG_ID)),
                            o.getBoolean(JSON_BOOL_NEW),
                            dbc.getSchoolByID(oSchool.getLong(JSON_LONG_ID)));
                    subsList.add(temp);
                }

                dbc.setAllSubscriptions(subsList);
            }
            else if (params[0].contains(ALL_IMAGES)){
                JSONObject rawImages = new JSONObject(jsonString);
                JSONArray imagesArray = rawImages.getJSONArray(JSON_NAME_IMAGES);
                ArrayList<Image> imageList = new ArrayList<>();

                for (int i = 0; i < imagesArray.length(); i++){
                    JSONObject o = imagesArray.getJSONObject(i);
                    byte[] tempByteArray = o.getString(JSON_NAME_IMAGE).getBytes(Charset.forName("UTF-8"));
                    Image temp = new Image(o.getLong(JSON_LONG_ID), o.getInt(JSON_INT_PRIORITY),
                            tempByteArray);
                    imageList.add(temp);
                    Log.d("IMAGE TEST", o.getString(JSON_NAME_IMAGE));
                }

                dbc.setAllImages(imageList);
            }
        }
        catch (MalformedURLException | ProtocolException | JSONException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            Log.d("NO INTERNET", "THERE IS NO INTERNET");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        dbc.close();
        loadingDatabaseDialog.dismiss();

        fragment.everythingIsLoaded();
    }
}
