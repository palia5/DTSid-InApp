package be.ehb.dtsid_inapp.Models;

import java.util.List;

public class DataDAO
{
    //Variables
    private static DataDAO DAOInstance = new DataDAO();
    private static EventList events;
    private static SchoolList schools;
    private static SubscriptionList subscriptions;
    private static TeacherList teachers;

    //Private methods
    private DataDAO()
    {
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
        return DAOInstance;
    }
    public static List<Event> getAllEvents()
    {
        return events.getEvents();
    }
    public static List<School> getAllSchools()
    {
        return schools.getSchools();
    }
    public static List<Subscription> getAllSubscriptions()
    {
        return subscriptions.getSubscriptions();
    }
    public static List<Teacher> getAllTeachers()
    {
        return teachers.getTeachers();
    }
    public static void setEvents(List<Event> events)
    {
        getEvents().setEvents(events);
    }
    public static void setSchools(List<School> schools)
    {
        getSchools().setSchools(schools);
    }
    public static void setSubscriptions(List<Subscription> subscriptions)
    {
        getSubscriptions().setSubscriptions(subscriptions);
    }
    public static void setTeachers(List<Teacher> teachers)
    {
        getTeachers().setTeachers(teachers);
    }
}