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
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

import be.ehb.dtsid_inapp.Models.DataDAO;

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
                    Teacher temp = new Teacher(o.getLong(JSON_LONG_ID), o.getString(JSON_STRING_NAME), o.getInt(JSON_INT_ACADYEAR));
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
                    Event temp = new Event(o.getLong(JSON_LONG_ID), o.getString(JSON_STRING_NAME),
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
                    School temp = new School(o.getLong(JSON_LONG_ID), o.getString(JSON_STRING_NAME),
                            o.getString(JSON_STRING_GEMEENTE), o.getInt(JSON_STRING_POSTCODE));
                    schoolList.add(temp);
                    Log.d("TEST", temp.getName() + temp.getZip() + temp.getCity());
                }
                //DAO.addSchoolList(schoolList);
                DataDAO.getDAOInstance().setSchools(schoolList);
                Log.d("DATADAO", DataDAO.getDAOInstance().getAllSchools().toString());
            }
            else if (params[0].contains(ALL_SUBSCRIPTIONS)){
                JSONObject rawSubs = new JSONObject(jsonString);
                JSONArray subsArray = rawSubs.getJSONArray(JSON_NAME_SUBSCRIPTIONS);
                ArrayList<Subscription> subsList = new ArrayList<>();

                for (int i = 0; i< subsArray.length(); i++){
                    JSONObject o = subsArray.getJSONObject(i);
                    HashMap<String, String> tempInterests = new HashMap<>();
                    JSONObject oInterests = o.getJSONObject(JSON_NAME_INTERESTS);
                    JSONObject oTeacher = o.getJSONObject(JSON_NAME_TEACHER);
                    JSONObject oSchool = o.getJSONObject(JSON_NAME_SCHOOL);
                    JSONObject oEvent = o.getJSONObject(JSON_NAME_EVENT);
                    tempInterests.put(JSON_STRING_DIGX, oInterests.getString(JSON_STRING_DIGX));
                    tempInterests.put(JSON_STRING_WERKSTUDENT, oInterests.getString(JSON_STRING_WERKSTUDENT));
                    tempInterests.put(JSON_STRING_MULTEC, oInterests.getString(JSON_STRING_MULTEC));

                    Subscription temp = new Subscription(o.getString(JSON_STRING_FIRSTNAME),
                            o.getString(JSON_STRING_LASTNAME), o.get(JSON_STRING_EMAIL),
                            o.get(JSON_STRING_STREET), o.getString(JSON_STRING_STREETNUMBER),
                            o.get(JSON_STRING_ZIP), o.get(JSON_STRING_CITY), tempInterests,
                            Date.valueOf(String.valueOf(o.getLong(JSON_LONG_TIMESTAMP))),
                            DataDAO.getDAOInstance().getTeacherByID(oTeacher.getLong(JSON_LONG_ID)),
                            DataDAO.getDAOInstance().getEventByID(oEvent.getLong(JSON_LONG_ID)),
                            o.getBoolean(JSON_BOOL_NEW),
                            DataDAO.getDAOInstance().getSchoolById(oSchool.getLong(JSON_LONG_ID)));
                    subsList.add(temp);
                    //Log.d("JSON TEST", o.getString(JSON_STRING_FIRSTNAME) + oTeacher.getLong(JSON_LONG_ID));
                }
                DataDAO.getDAOInstance().setSubscriptions(subsList);
            }
            else if (params[0].contains(ALL_IMAGES)){

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
