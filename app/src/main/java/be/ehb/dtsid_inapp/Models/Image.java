package be.ehb.dtsid_inapp.Models;

public class Image {

    /**
     *
     * @author Dorothée
     * @version 1.0
     *
     *
     */

    private Long id;
    private int priority;
    private String image;
    private byte[] imageByteArray;

    public Image() {
    }

    public Image(Long id, int priority, String image) {
        this.id = id;
        this.priority = priority;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
