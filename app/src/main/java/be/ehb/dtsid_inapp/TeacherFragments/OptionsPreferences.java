package be.ehb.dtsid_inapp.TeacherFragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import be.ehb.dtsid_inapp.R;

public class OptionsPreferences extends PreferenceFragment
{
    private SwitchPreference autosyncSW;
    private CheckBoxPreference datesyncCB;
    private TimePreference datesyncTP;
    private CheckBoxPreference closesyncCB;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        autosyncSW = (SwitchPreference) findPreference("pref_sw_autosynx");
        datesyncCB = (CheckBoxPreference) findPreference("pref_cb_syncdate");
        datesyncTP = (TimePreference) findPreference("pref_tp_syncdatepicker");
        closesyncCB = (CheckBoxPreference) findPreference("pref_cb_syncclose");

        if(autosyncSW.isChecked())
        {
            datesyncCB.setEnabled(true);
            closesyncCB.setEnabled(true);

            if(datesyncCB.isChecked())
                datesyncTP.setEnabled(true);
            else
                datesyncTP.setEnabled(false);
        }
        else
        {
            datesyncCB.setEnabled(false);
            closesyncCB.setEnabled(false);
            datesyncTP.setEnabled(false);
        }

        autosyncSW.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                if(newValue.equals(true))
                {
                    datesyncCB.setEnabled(true);
                    closesyncCB.setEnabled(true);

                    if(datesyncCB.isChecked())
                        datesyncTP.setEnabled(true);
                    else
                        datesyncTP.setEnabled(false);
                }
                else
                {
                    datesyncCB.setEnabled(false);
                    closesyncCB.setEnabled(false);
                    datesyncTP.setEnabled(false);
                }
                return true;
            }
        });

        datesyncCB.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                if(newValue.equals(true))
                    datesyncTP.setEnabled(true);
                else
                    datesyncTP.setEnabled(false);
                return true;
            }
        });
    }
}
