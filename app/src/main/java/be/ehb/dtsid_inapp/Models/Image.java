package be.ehb.dtsid_inapp.Models;

public class Image {

    private Long id;
    private int priority;
    private byte[] image;

    public Image() {
    }

    public Image(Long id, int priority, byte[] image) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
