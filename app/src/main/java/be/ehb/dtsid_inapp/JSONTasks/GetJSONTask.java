package be.ehb.dtsid_inapp.JSONTasks;

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
import java.util.ArrayList;

import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Teacher;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class GetJSONTask extends AsyncTask<String, Integer, Void>
{
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
                    Teacher temp = new Teacher(o.getString(JSON_STRING_NAME), o.getInt(JSON_INT_ACADYEAR));
                    //teacherList.add(temp);
                }
            }
            else if (params[0].contains(ALL_EVENTS))
            {
                JSONObject rawEvents = new JSONObject(jsonString);
                JSONArray eventsArray = rawEvents.getJSONArray(JSON_NAME_EVENTS);
                ArrayList<Event> eventList = new ArrayList<>();

                for (int i = 0; i < eventsArray.length(); i++)
                {
                    JSONObject o = eventsArray.getJSONObject(i);
                    Event temp = new Event(o.getString(JSON_STRING_NAME),
                            o.getInt(JSON_INT_ACADYEAR));
                    //eventList.add(temp);
                }

                //DAO.addEventList(eventList);
            }
            else if (params[0].contains(ALL_SCHOOLS))
            {
                JSONObject rawSchools = new JSONObject(jsonString);
                JSONArray schoolsArray = rawSchools.getJSONArray(JSON_NAME_SCHOOLS);
                ArrayList<School> schoolList = new ArrayList<>();

                for (int i = 0; i < schoolsArray.length(); i++)
                {
                    JSONObject o = schoolsArray.getJSONObject(i);
                    School temp = new School(o.getString(JSON_STRING_NAME),
                            o.getString(JSON_STRING_GEMEENTE), o.getInt(JSON_STRING_POSTCODE));
                    schoolList.add(temp);
                    Log.d("TEST", temp.getName() + temp.getZip() + temp.getCity());
                }
                //DAO.addSchoolList(schoolList);
            }
        }
        catch (MalformedURLException | ProtocolException | JSONException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
