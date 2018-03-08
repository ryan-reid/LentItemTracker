package hci2.lentitemtracker.Persistence;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Iterator;

import hci2.lentitemtracker.R;


public class UserItemList {

    private static UserItemList Instance = null;

    private static Context context;

    private static ArrayList<ItemDataModel> items = new ArrayList<ItemDataModel>();

    private static ArrayList<ItemDataModel> requestItems = new ArrayList<ItemDataModel>();

    public static UserItemList getInstance(Context context) {
        if(Instance == null) {
            Instance = new UserItemList();
            Instance.context = context;
        }
        return Instance;
    }

    public static UserItemList getInstance() {
        if(Instance == null) {
            Instance = new UserItemList();
        }
        return Instance;
    }

    private UserItemList() {
        if(items.isEmpty()) {
            items  = createSampleData();
            requestItems = createSampleRequestItems();
        }
    }

    public static void addItemToUserList(ItemDataModel item) {
        items.add(item);

    }

    public static ArrayList<ItemDataModel> getItems() {
        return items;
    }

    public static ArrayList<ItemDataModel> getRequestItems() { return requestItems; }

    public static void removeItemWithGuid(String guid) {
        for(Iterator<ItemDataModel> iterator = items.iterator(); iterator.hasNext(); ) {
            if(iterator.next().getId().equals(guid))
                iterator.remove();
        }

        for(Iterator<ItemDataModel> iterator = requestItems.iterator(); iterator.hasNext(); ) {
            if(iterator.next().getId().equals(guid))
                iterator.remove();
        }
    }

    public static void setContext(Context context) { UserItemList.context = context;}

    public static void refreshData() {
        items = createSampleData();
        requestItems = createSampleRequestItems();
    }

    private static ArrayList<ItemDataModel> createSampleData(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.controller_item), "Xbox Controller", "Xbox One Controller", "Stu Dent", 30, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.car_item),"Car", "Fast... and extra furious", "Stu Dent", 7, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.tractor_item),"John Deere Tractor", "Nothing drives like a Deere", "Stu Dent",5, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.chainsaw_item), "Chainsaw", "Black & Decker", "Stu Dent", 2, ItemStatus.AVAILABLE));
        return dataModels;
    }


    private static ArrayList<ItemDataModel> createSampleRequestItems(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.camera_item), "Camerea", "Professional System", "Mark Dent", 30, ItemStatus.INCOMING));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.bicycle_item),"Bicycle", "Lightweight Mountain Bike", "John Dent", 7, ItemStatus.INCOMING));
        return dataModels;
    }

}
