package be.ehb.dtsid_inapp.Models;

/**
 * Created by Kristof on 15/06/2015.
 */
public class Gemeente {
    private String zip;
    private String plaats;
    private String provincie;

    public Gemeente(String zip, String plaats, String provincie) {
        this.zip = zip;
        this.plaats = plaats;
        this.provincie = provincie;
    }

    public Gemeente() {
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getProvincie() {
        return provincie;
    }

    public void setProvincie(String provincie) {
        provincie = provincie;
    }

    @Override
    public String toString() {
        return zip + plaats;
    }
}
