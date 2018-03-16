package hci2.lentitemtracker.Persistence;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class ItemDataModel {
    private Bitmap image;
    private String description;
    private String owner;
    private String title;
    private int numDaysAvailableForLending;
    private ItemStatus status;
    private String id;
    private int numDaysWanted;

    public ItemDataModel(Bitmap image, String title, String description, String owner, int numDaysAvailableForLending, ItemStatus status) {
        this.image = image;
        this.description = description;
        this.title = title;
        this.owner = owner;
        this.numDaysAvailableForLending = numDaysAvailableForLending;
        this.status = status;
        this.id = java.util.UUID.randomUUID().toString();
    }

    public ItemDataModel(Bitmap image, String title, String description, String owner, ItemStatus status, int numDaysWanted) {
        this.image = image;
        this.description = description;
        this.title = title;
        this.owner = owner;
        this.numDaysWanted = numDaysWanted;
        this.status = status;
        this.id = java.util.UUID.randomUUID().toString();
    }

    public ItemDataModel(Bitmap image, String title, String description, int numDaysAvailableForLending, ItemStatus status) {
        this(image, title, description, "Jim James", numDaysAvailableForLending, status);
    }

    public ItemDataModel(String title, String description, String owner, int numDaysAvailableForLending, ItemStatus available){
        this(null, title, description, owner, numDaysAvailableForLending, available);
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumDaysAvailableForLending() {
        return numDaysAvailableForLending;
    }

    public void setNumDaysAvailableForLending(int numDaysAvailableForLending) {
        this.numDaysAvailableForLending = numDaysAvailableForLending;
    }

    public ItemStatus isAvailable() {
        return status;
    }

    public void setAvailable(ItemStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getOwner(){
        return this.owner;
    }

    public ItemStatus getStatus(){
        return this.status;
    }

    public void setStatus(ItemStatus status) { this.status = status;}

    public int getNumDaysWanted() {
        return numDaysWanted;
    }

    public void setNumDaysWanted(int numDaysWanted) {
        this.numDaysWanted = numDaysWanted;
    }

}
