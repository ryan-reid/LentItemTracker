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

}
