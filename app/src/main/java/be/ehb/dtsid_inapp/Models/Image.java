package be.ehb.dtsid_inapp.Models;

/**
 * @author Dorothée
 * @version 1.0
 */

public class Image {



    private Long id;
    private int priority;
    private String image;
    private byte[] imageByteArray;

    /**
     * Constructor
     */
    public Image() {
    }

    /**
     * Constructor
     * @param id
     * @param priority
     * @param image
     */
    public Image(Long id, int priority, String image) {
        this.id = id;
        this.priority = priority;
        this.image = image;
    }

    /**
     *
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return int priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     *
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     *
     * @return String image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
