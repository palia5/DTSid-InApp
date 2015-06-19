package be.ehb.dtsid_inapp.Models;

public class Gemeente
{
    private String zip;
    private String plaats;
    private String provincie;

    /**
     * Constructor
     * @param zip
     * @param plaats
     * @param provincie
     */
    public Gemeente(String zip, String plaats, String provincie)
    {
        this.zip = zip;
        this.plaats = plaats;
        this.provincie = provincie;
    }

    /**
     * Constructor
     */
    public Gemeente() {
    }

    /**
     *
     * @return String zip
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return String plaats
     */
    public String getPlaats() {
        return plaats;
    }

    /**
     *
     * @param plaats
     */
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    /**
     *
     * @return String provincie
     */
    public String getProvincie() {
        return provincie;
    }

    /**
     *
     * @param provincie
     */
    public void setProvincie(String provincie) {
        this.provincie = provincie;
    }

    @Override
    public String toString() {
        return plaats;
    }
}
