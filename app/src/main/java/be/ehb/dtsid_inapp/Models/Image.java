package be.ehb.dtsid_inapp.Models;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

import be.ehb.dtsid_inapp.Database.DatabaseContract;

public class Image {

    private Long id;
    private int priority;
    private byte[] image;
    private Bitmap imageBitmap;

    public Image(){}


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

    public Bitmap getImageBitmap()
    {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        imageBitmap = bitmap;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
