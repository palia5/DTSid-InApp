package be.ehb.dtsid_inapp.TeacherFragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.ehb.dtsid_inapp.R;

/**
 *
 * @author Tom
 * @version 1.0
 *
 *
 */

public class OptionsPreferences extends PreferenceFragment
{
    private SwitchPreference autosyncSW;
    private SwitchPreference datesyncSW;
    private TimePreference datesyncTP;
    private SwitchPreference closesyncSW;
    private Activity context;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        view.setBackgroundColor(Color.parseColor("#ffbca1a5"));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);


        autosyncSW = (SwitchPreference) findPreference("pref_sw_autosynx");
        datesyncSW = (SwitchPreference) findPreference("pref_sw_syncdate");
        datesyncTP = (TimePreference) findPreference("pref_tp_syncdatepicker");
        closesyncSW = (SwitchPreference) findPreference("pref_sw_syncclose");


        if(autosyncSW.isChecked())
        {
            datesyncSW.setEnabled(true);
            closesyncSW.setEnabled(true);

            if(datesyncSW.isChecked())
                datesyncTP.setEnabled(true);
            else
                datesyncTP.setEnabled(false);
        }
        else
        {
            datesyncSW.setEnabled(false);
            closesyncSW.setEnabled(false);
            datesyncTP.setEnabled(false);
        }

        autosyncSW.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue)
            {
                if(newValue.equals(true))
                {
                    datesyncSW.setEnabled(true);
                    closesyncSW.setEnabled(true);

                    if(datesyncSW.isChecked())
                        datesyncTP.setEnabled(true);
                    else
                        datesyncTP.setEnabled(false);
                }
                else
                {
                    datesyncSW.setEnabled(false);
                    closesyncSW.setEnabled(false);
                    datesyncTP.setEnabled(false);
                }
                return true;
            }
        });

        datesyncSW.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.equals(true))
                    datesyncTP.setEnabled(true);
                else
                    datesyncTP.setEnabled(false);
                return true;
            }
        });
    }
}
