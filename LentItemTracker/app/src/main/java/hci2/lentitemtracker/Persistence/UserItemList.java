package hci2.lentitemtracker.Persistence;

import android.content.Context;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import hci2.lentitemtracker.R;


public class UserItemList {

    private static UserItemList Instance = null;

    private static Context context;

    private static ArrayList<ItemDataModel> items = new ArrayList<>();

    private static ArrayList<ItemDataModel> requestItems = new ArrayList<>();

    private static ArrayList<ItemDataModel> lentItems = new ArrayList<>();

    private static ArrayList<ItemDataModel> borrowedItems = new ArrayList<>();


    public static void approveAllOutgoingRequests() {
        for (int i = 0; i < requestItems.size(); i++) {
            if(requestItems.get(i).getStatus().equals(ItemStatus.PENDING)) {
                ItemDataModel item = requestItems.get(i);
                removeItemWithGuid(item.getId());
                item.setStatus(ItemStatus.BORROWED);
                borrowedItems.add(item);
            }
        }

    }

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

    public static ArrayList<ItemDataModel> getLentItems() {
        return lentItems;
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
    public static ArrayList<ItemDataModel> getBorrowedItems() { return borrowedItems; }

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
        lentItems = new ArrayList<ItemDataModel>();
        borrowedItems = new ArrayList<ItemDataModel>();
    }

    public static ItemDataModel getItemByGuid(String guid) {

        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getId().equals(guid)) {
                return items.get(i);
            }
        }

        for(int i = 0; i < requestItems.size(); i++) {
            if(requestItems.get(i).getId().equals(guid)) {
                return requestItems.get(i);
            }
        }

        return null;

    }

    public static void addItemToLentList(ItemDataModel item) {
        lentItems.add(item);
    }

    public static void addToRequestList(ItemDataModel item) {
        requestItems.add(item);
    }

    public static ArrayList<ItemDataModel> createSampleData(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.controller_item), "Xbox Controller", "Xbox One Controller", "Kevin Jones", 30, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.car_item),"Car", "Fast... and extra furious", "Drew Williams", 7, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.tractor_item),"John Deere Tractor", "Nothing drives like a Deere", "James Sharp",5, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.chainsaw_item), "Chainsaw", "Black & Decker", "Lebron Jones", 2, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.alarm_clock), "Alarm Clock", "Never snooze again with TimexPro", "Stephen Thyme", 7, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.book), "A Twist In Time", "A fantasy novel by No Won Ye", "James Softie", 14, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.calendar), "Calendar", "Track your events easier", "Kevin Durable", 31, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.camera), "Camera", "Never miss that perfect underwater shot while on vacation", "Isiah Tom", 14, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.car_battery), "Car Battery", "Antifreeze battery for the coldest winters", "Jack White", 7, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.christmas_tree), "Christmas Tree", "Santa would never skip your home with this", "J.R Henny", 14, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.coffee_pot), "Coffee Pot", "Best coffee pot for a coffee guru", "Rick Drooper", 4, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.mittens), "Mittens", "Comfy baking guaranteed", "Huncho Jack", 5, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.office_phone), "Office Phone", "Depot House supplies", "Quavo Smith", 4, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.personal_computer), "personal_computer", "Depot House PC", "Sean Jean", 1, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.sleigh), "Sleigh", "Chill winter essentials", "Wendy Will", 3, ItemStatus.AVAILABLE));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.smart_phone), "TechNoid Phone", "TechNoid Mobile Ltd.", "Norm Swelly", 7, ItemStatus.AVAILABLE));
        return dataModels;
    }

    private static ArrayList<ItemDataModel> createSampleRequestItems(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.camera_item), "Camera", "Professional System", "Mark Dent", ItemStatus.INCOMING,30));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.bicycle_item),"Bicycle", "Lightweight Mountain Bike", "John Dent", ItemStatus.INCOMING, 7));
        return dataModels;
    }

    public static ArrayList<ItemDataModel> createSampleBorrowedItems(){
        ArrayList<ItemDataModel> dataModels = new ArrayList<>();
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.camera_item), "Camera", "Professional System", "Mark Dent", ItemStatus.BORROWED,30));
        dataModels.add(new ItemDataModel(BitmapFactory.decodeResource(UserItemList.context.getResources(), R.drawable.bicycle_item),"Bicycle", "Lightweight Mountain Bike", "John Dent", ItemStatus.BORROWED, 7));
        return dataModels;
    }

    public static void sortRequestItemsByName() {
        Collections.sort(requestItems, new Comparator<ItemDataModel>() {
            @Override
            public int compare(ItemDataModel o1, ItemDataModel o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
        System.out.print(requestItems);
    }

    public static void sortRequestItemsByType() {
        Collections.sort(requestItems, new Comparator<ItemDataModel>() {
            @Override
            public int compare(ItemDataModel o1, ItemDataModel o2) {
                return o1.getStatus().toString().compareToIgnoreCase(o2.getStatus().toString());
            }
        });
    }
}
