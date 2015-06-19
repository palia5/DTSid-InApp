package be.ehb.dtsid_inapp.JSONTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author Tom
 * @version 1.0
 *here, the base url is fetched for the GetJson tasks
 */
public class GetBaseUrl extends AsyncTask<String, Integer, String> {

    private boolean connected;

    public GetBaseUrl(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            connected = true;
        else
            connected = false;
    }

    @Override
    protected String doInBackground(String... params) {
        if (connected){
            try {
                URL url = new URL(params[0]);
                Log.d("TESTBaseurl", params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                String baseUrl = null;
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                if (reader.readLine() != null){
                    baseUrl = "http://" +  reader.readLine();
                    //Log.d("TEST getBaseUrl", reader.readLine());
                }
                return baseUrl;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
