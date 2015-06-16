package be.ehb.dtsid_inapp.Models;

/**
 * Created by Kristof on 15/06/2015.
 */
public class Gemeente {
    private String zip;
    private String gemeente;

    public Gemeente(String zip, String gemeente) {
        this.zip = zip;
        this.gemeente = gemeente;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    @Override
    public String toString() {
        return zip + gemeente;
    }
}
