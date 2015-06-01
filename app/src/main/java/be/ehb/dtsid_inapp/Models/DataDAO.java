package be.ehb.dtsid_inapp.Models;

import java.util.List;

public class DataDAO
{
    //Variables
    private static DataDAO DAOInstance;
    private static EventList events;
    private static SchoolList schools;
    private static SubscriptionList subscriptions;
    private static TeacherList teachers;

    //Private methods
    private DataDAO()
    {
        DAOInstance = this;
        events = new EventList();
        schools = new SchoolList();
        subscriptions = new SubscriptionList();
        teachers = new TeacherList();
    }
    private static EventList getEvents()
    {
        return events;
    }
    private static SchoolList getSchools()
    {
        return schools;
    }
    private static SubscriptionList getSubscriptions()
    {
        return subscriptions;
    }
    private static TeacherList getTeachers()
    {
        return teachers;
    }

    //Methods
    public static void addSubscription()
    {

    }

    //Getters and setters
    public static DataDAO getDAOInstance()
    {
        if(DAOInstance == null)
            new DataDAO();

        return DAOInstance;
    }
    public List<Event> getAllEvents()
    {
        return events.getEvents();
    }
    public List<School> getAllSchools()
    {
        return schools.getSchools();
    }
    public List<Subscription> getAllSubscriptions()
    {
        return subscriptions.getSubscriptions();
    }
    public List<Teacher> getAllTeachers()
    {
        return teachers.getTeachers();
    }
    public void setEvents(List<Event> events)
    {
        getEvents().setEvents(events);
    }
    public void setSchools(List<School> schools)
    {
        getSchools().setSchools(schools);
    }
    public void setSubscriptions(List<Subscription> subscriptions)
    {
        getSubscriptions().setSubscriptions(subscriptions);
    }
    public void setTeachers(List<Teacher> teachers)
    {
        getTeachers().setTeachers(teachers);
    }
}