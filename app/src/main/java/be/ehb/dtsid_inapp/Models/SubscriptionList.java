package be.ehb.dtsid_inapp.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by doortje on 1/06/15.
 */
public class SubscriptionList implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3498952619618662678L;
    private List<Subscription> subscriptions;

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public SubscriptionList(List<Subscription> subscriptions) {
        super();
        this.subscriptions = subscriptions;
    }

    public SubscriptionList() {
        // TODO Auto-generated constructor stub
    }
}
