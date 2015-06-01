package be.ehb.dtsid_inapp.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by doortje on 1/06/15.
 */
public class Subscription implements Serializable {

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

    public Subscription(List<Subscription> subscriptions)
    {


        super();
        this.subscriptions = subscriptions;

    }
}
