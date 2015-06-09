package be.ehb.dtsid_inapp.TeacherFragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import be.ehb.dtsid_inapp.R;

public class OptionsPreferences extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
