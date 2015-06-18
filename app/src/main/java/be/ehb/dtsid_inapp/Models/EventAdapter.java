package be.ehb.dtsid_inapp.Models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import be.ehb.dtsid_inapp.R;

/**
 *
 * @author Dorothée
 * @version 1.0
 *
 *
 */

public class EventAdapter extends BaseAdapter
{
    private LayoutInflater inflater;
    private List<Event> events;

    private static class ViewHolder
    {
        TextView eventTV;
    }

    public EventAdapter(Activity activity, List<Event> events)
    {
        inflater = activity.getLayoutInflater();
        this.events = events;
    }

    @Override
    public int getCount()
    {
        return events.size();
    }

    @Override
    public Object getItem(int position)
    {
        return events.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return events.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = new ViewHolder();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.event_list_item,null);
            holder.eventTV = (TextView) convertView.findViewById(R.id.TV_name_eventSpinnerItem);

            convertView.setTag(holder);
        }

        else
            holder = (ViewHolder)convertView.getTag();

        Event eventByRow = events.get(position);

        holder.eventTV.setText(eventByRow.getName());

        return convertView;
    }
}