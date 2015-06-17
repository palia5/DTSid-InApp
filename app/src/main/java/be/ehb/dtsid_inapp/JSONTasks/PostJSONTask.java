package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.TeacherFragments.Options;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class PostJSONTask extends AsyncTask<Void, Integer, HashMap<String, Boolean>>
{
    private DatabaseContract dbc;
    private List<Subscription> subscriptionList;
    private Options fragment;
    private String baseUrl;

    public PostJSONTask(Options c)
    {
        fragment = c;
        dbc = new DatabaseContract(fragment.getActivity().getApplicationContext());
        baseUrl = PreferenceManager.getDefaultSharedPreferences(c.getActivity().getApplicationContext())
                .getString("BASEURL", null);
    }

    @Override
    protected HashMap<String, Boolean> doInBackground(Void... params)
    {
        subscriptionList = dbc.getAllSubscriptions();
        ArrayList<Subscription> checkList = new ArrayList<>();
        try
        {
            for (int i = 0; i < subscriptionList.size(); i++)
            {
                URL postUrl = new URL(baseUrl + POST_SUBSCRIPTION);
                HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
                postConnection.setDoOutput(true);
                postConnection.setDoInput(true);
                postConnection.setRequestMethod("POST");
                postConnection.setRequestProperty("Content-Type", "application/json");
                postConnection.setRequestProperty("Accept", "application/json");

                Subscription tempSub = subscriptionList.get(i);

                if (tempSub.getNew())
                {
                    tempSub.setId(null);
                    tempSub.setNew(false);

                    GsonBuilder gb = new GsonBuilder();
                    gb.excludeFieldsWithoutExposeAnnotation();
                    Gson gson = gb.create();
                    String jsonString = gson.toJson(tempSub);

                    postConnection.setFixedLengthStreamingMode(jsonString.getBytes().length);
                    postConnection.connect();
                    OutputStream os = postConnection.getOutputStream();

                    os.write(jsonString.getBytes());
                    checkList.add(tempSub);
                    os.flush();
                    os.close();
                }
            }

            URL url = new URL(baseUrl + ALL_SUBSCRIPTIONS);
            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
            String jsonGetString = reader.readLine();

            JSONObject rawSubs = new JSONObject(jsonGetString);
            JSONArray subsArray = rawSubs.getJSONArray(JSON_NAME_SUBSCRIPTIONS);
            ArrayList<Subscription> subsList = new ArrayList<>();

            for (int i = 0; i< subsArray.length(); i++)
            {
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

                for (int j = 0; j < checkList.size(); j++)
                {
                    if (temp.getTimestamp().equals(checkList.get(j).getTimestamp()) &&
                            temp.getEmail().equals(checkList.get(j).getEmail()))
                    {
                        checkList.remove(j);
                    }
                }
            }

            if (checkList.size() > 0)
            {
                for (int k = 0; k < checkList.size(); k++)
                {
                    Log.d("Failed to upload: ", checkList.get(k).toString());
                    // terug aan locale db meegeven.
                    // isNew terug op true zetten.
                }
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(HashMap<String, Boolean> stringBooleanHashMap)
    {
        super.onPostExecute(stringBooleanHashMap);

        dbc.close();

        fragment.allIsSynced();
    }
}