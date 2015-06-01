package be.ehb.dtsid_inapp.Models;

import java.util.List;

public class SubscriptionList
{
    //Variables
    private List<Subscription> subscriptions;

    //Constructor
    public SubscriptionList()
    {
    }

    //Getters and setters
    public List<Subscription> getSubscriptions()
    {
        return subscriptions;
    }
    public void setSubscriptions(List<Subscription> subscriptions)
    {
        this.subscriptions = subscriptions;
    }
}