package be.ehb.dtsid_inapp.Models;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import be.ehb.dtsid_inapp.R;

public class TeacherAdapter extends BaseAdapter
{
    private LayoutInflater inflater;
    private List<Teacher> teachers;
    private Activity context;

    private static class ViewHolder
    {
        TextView teacherTV;
    }

    public TeacherAdapter(Activity activity, List<Teacher> teachers)
    {
        inflater = activity.getLayoutInflater();
        this.teachers = teachers;
        this.context = activity;
    }

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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = new ViewHolder();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.teachers_list_item,null);
            holder.teacherTV = (TextView) convertView.findViewById(R.id.TV_teacherSpinnerItem);

            convertView.setTag(holder);
        }

        else
            holder = (ViewHolder)convertView.getTag();

        Teacher teacherByRow = teachers.get(position);

        holder.teacherTV.setText(teacherByRow.getName());
        Typeface myCustomFont = Typeface.createFromAsset(context.getAssets()
                , "fonts/ehb_font.ttf");

        holder.teacherTV.setTypeface(myCustomFont);

        return convertView;
    }
}