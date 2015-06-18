package be.ehb.dtsid_inapp.Models;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import be.ehb.dtsid_inapp.R;

/**
 *
 * @author Dorothée
 * @version 1.0
 *
 *
 */

public class SubscriptionAdapter extends BaseAdapter
{
    private ArrayList<Subscription> subscriptionArrayList;
    private LayoutInflater inflater;
    private Activity context;

    private static class ViewHolder
    {
        TextView naamVoornaamTV;
        TextView interestTV;
        TextView schoolTV;
    }

    public SubscriptionAdapter(Activity activity, ArrayList<Subscription> subscriptionArrayList)
    {
        this.subscriptionArrayList = subscriptionArrayList;
        inflater = activity.getLayoutInflater();
        this.context = activity;
    }

    @Override
    public int getCount()
    {
        return subscriptionArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return subscriptionArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return subscriptionArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = new ViewHolder();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.subscriptions_list_item,null);
            holder.naamVoornaamTV = (TextView) convertView.findViewById(R.id.tv_naam_voornaam_subscriptionitemlist);
            holder.interestTV = (TextView) convertView.findViewById(R.id.tv_interest_subscriptionitemlist);
            holder.schoolTV = (TextView) convertView.findViewById(R.id.tv_school_subscriptionitemlist);

            convertView.setTag(holder);

        }

        else
            holder = (ViewHolder)convertView.getTag();

        Subscription subscriptionByRow = subscriptionArrayList.get(position);

        holder.naamVoornaamTV.setText(subscriptionByRow.getLastName().toString() + " " + subscriptionByRow.getFirstName().toString());
        holder.interestTV.setText(subscriptionByRow.getInterests().toString());
        holder.schoolTV.setText(subscriptionByRow.getSchool().getName());

        Typeface myCustomFont = Typeface.createFromAsset(context.getAssets()
                , "fonts/ehb_font.ttf");

        holder.naamVoornaamTV.setTypeface(myCustomFont);
        holder.interestTV.setTypeface(myCustomFont);
        holder.schoolTV.setTypeface(myCustomFont);


        //View v = super.getView(position, convertView, parent);
        //((TextView) v).setTypeface(StaticUtils.sTypeFace(getApplicationContext()));//Typeface for normal view


        return convertView;
    }
}
