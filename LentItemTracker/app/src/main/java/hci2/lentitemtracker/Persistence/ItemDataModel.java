package hci2.lentitemtracker.Persistence;


import android.graphics.Bitmap;

public class ItemDataModel {

    private Bitmap image;
    private String description;
    private String title;
    private int numDaysAvailableForLending;
    private boolean available;

    public ItemDataModel(Bitmap image, String title, String description, int numDaysAvailableForLending, boolean available) {
        this.image = image;
        this.description = description;
        this.title = title;
        this.numDaysAvailableForLending = numDaysAvailableForLending;
        this.available = available;
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

}
