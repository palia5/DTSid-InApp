package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_INT_PRIORITY;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_LONG_ID;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_NAME_IMAGE;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_NAME_IMAGES;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class GetImagesJSONTask extends AsyncTask<String, Integer, Void> {

    private DepartmentLogin fragment;

    public GetImagesJSONTask(DepartmentLogin c)
    {
        fragment = c;
    }


    @Override
    protected Void doInBackground(String... params)
    {
        try
        {
            URL urlImages = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) urlImages.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Log.d("ImageTask", "Connection made");

            String jsonString = "";
            int length = 1;
            char[] buffer = new char[1];

            while(length > 0)
            {
                length = reader.read(buffer, 0, 1);

                jsonString += String.copyValueOf(buffer);

                if(String.copyValueOf(buffer).equals(""))
                    Log.d("ImageTask","First Image completed: " + jsonString);
            }

            Log.d("ImageTask", "READING COMPLETE: Length: " + length + " String length: " + jsonString.length());

            connection.disconnect();

            JSONObject rawImages = new JSONObject("123");
            JSONArray imagesArray = rawImages.getJSONArray(JSON_NAME_IMAGES);
            ArrayList<Image> imageList = new ArrayList<>();
            String tempstring;
            String filename;
            FileOutputStream outputStream;

            for (int i = 0; i < imagesArray.length(); i++)
            {
                JSONObject o = imagesArray.getJSONObject(i);
                Log.d("ImageTask","" + o.getLong("id"));

                //bytearray opvullen vanuit de JSON
                byte[] array = (byte[]) o.get(JSON_NAME_IMAGE);

                //filename uniek maken voor elke afbeelding
                filename = "picture_" + i + ".jpeg";

                //bytearray wegschrijven als jpeg met de filename
                outputStream = fragment.getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(array);
                outputStream.close();

                //array opvullen met objecten die de prioriteit en locatie van de afbeeldingen bevatten
                Image temp = new Image(o.getLong(JSON_LONG_ID), o.getInt(JSON_INT_PRIORITY),
                        filename);

                imageList.add(temp);
            }
        }
        catch (MalformedURLException| ProtocolException| JSONException e)
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

}
