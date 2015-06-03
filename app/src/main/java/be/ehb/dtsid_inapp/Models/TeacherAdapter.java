package be.ehb.dtsid_inapp.Models;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class TeacherAdapter extends BaseAdapter
{
    private ArrayList<Teacher> teachers;

    @Override
    public int getCount()
    {
        return teachers.size();
    }

    @Override
    public Object getItem(int position)
    {
        return teachers.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return teachers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}