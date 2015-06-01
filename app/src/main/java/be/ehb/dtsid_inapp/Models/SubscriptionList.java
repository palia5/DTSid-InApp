package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;
import java.util.List;

public class SubscriptionList implements Serializable
{
    //Variables
    private List<Subscription> subscriptions;

    //Constructor
    public SubscriptionList(List<Subscription> subscriptions)
    {
        super();
        this.subscriptions = subscriptions;
    }
    public SubscriptionList()
    {
        super();
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