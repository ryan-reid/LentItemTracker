package hci2.lentitemtracker.Persistence;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class ItemDataModel {

    private Bitmap image;
    private String description;
    private String owner;
    private String title;
    private int numDaysAvailableForLending;
    private boolean available;
    private String id;

    public ItemDataModel(Bitmap image, String title, String description, String owner, int numDaysAvailableForLending, boolean available) {
        this.image = image;
        this.description = description;
        this.title = title;
        this.owner = owner;
        this.numDaysAvailableForLending = numDaysAvailableForLending;
        this.available = available;
        this.id = java.util.UUID.randomUUID().toString();
    }

    public ItemDataModel(Bitmap image, String title, String description, int numDaysAvailableForLending, boolean available) {
        this(image, title, description, "umbibenb", numDaysAvailableForLending, available);
    }

    public ItemDataModel(String title, String description, String owner, int numDaysAvailableForLending, boolean available){
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public static ArrayList<ItemDataModel> createSampleData(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel("Remote Controller", "Controls all sorts of TVs", "student1", 30, true));
        dataModels.add(new ItemDataModel("Are we done yet", "Blue ray version with extended recording", "umbibenb", 7, true));
        dataModels.add(new ItemDataModel("Lawn Mower", "Electric", "John Doe", 5, true));
        dataModels.add(new ItemDataModel("Chainsaw", "Black & Decker", "umbibenb", 2, true));
        return dataModels;
    }

}
