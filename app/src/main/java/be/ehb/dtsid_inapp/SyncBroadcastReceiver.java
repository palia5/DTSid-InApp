package be.ehb.dtsid_inapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import be.ehb.dtsid_inapp.JSONTasks.PostJSONTask;
import be.ehb.dtsid_inapp.TeacherFragments.Options;

public class SyncBroadcastReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        PostJSONTask jsonTask = new PostJSONTask(new Options());
        jsonTask.execute();
        Log.d("BROADCAST", "Syncing right now!");
    }
}