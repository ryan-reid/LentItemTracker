package hci2.lentitemtracker.Persistence;

import java.util.ArrayList;


public class UserItemList {

    private UserItemList() {};

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
}
