package hci2.lentitemtracker.Persistence;

import java.util.ArrayList;
import java.util.Iterator;


public class UserItemList {

    private static UserItemList Instance = null;

    private static ArrayList<ItemDataModel> items = new ArrayList<ItemDataModel>();

    public static UserItemList getInstance() {
        if(Instance == null) {
            Instance = new UserItemList();
        }
        return Instance;
    }

    private UserItemList() {
        items  = createSampleData();
    }

    public static void addItemToUserList(ItemDataModel item) {
        items.add(item);

    }

    public static ArrayList<ItemDataModel> getItems() {
        return items;
    }

    public static void removeItemWithGuid(String guid) {
        for(Iterator<ItemDataModel> iterator = items.iterator(); iterator.hasNext(); ) {
            if(iterator.next().getId().equals(guid))
                iterator.remove();
        }
    }

    public static void refreshData() {
        items = createSampleData();
    }

    private static ArrayList<ItemDataModel> createSampleData(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel("Remote Controller", "Controls all sorts of TVs", "student1", 30));
        dataModels.add(new ItemDataModel("Are we done yet", "Blue ray version with extended recording", "umbibenb", 7));
        dataModels.add(new ItemDataModel("Lawn Mower", "Electric", "John Doe", 5, ItemStatus.PENDING));
        dataModels.add(new ItemDataModel("Chainsaw", "Black & Decker", "umbibenb", 2));
        return dataModels;
    }

}
