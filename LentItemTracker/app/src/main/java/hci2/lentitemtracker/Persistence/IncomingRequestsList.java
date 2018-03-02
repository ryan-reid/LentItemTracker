package hci2.lentitemtracker.Persistence;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Iterator;

import hci2.lentitemtracker.R;


public class IncomingRequestsList {

        private static Context context;

        private IncomingRequestsList() {};

        private static IncomingRequestsList Instance = null;

        private static ArrayList<ItemDataModel> items = new ArrayList<ItemDataModel>();

        public static void setContext(Context context) { IncomingRequestsList.context = context;};

        public static IncomingRequestsList getInstance() {
            if(Instance == null) {
                Instance = new IncomingRequestsList();
                initIncomingRequestList();
            }
            return Instance;
        }

        public static void addItemToUserList(ItemDataModel item) {
            items.add(item);
        }

        public static void initIncomingRequestList() {
            items.add(new ItemDataModel(BitmapFactory.decodeResource(context.getResources(), R.drawable.bicycle_item), "Bicycle", "My Bicycle", 5, true));
            items.add(new ItemDataModel(BitmapFactory.decodeResource(context.getResources(), R.drawable.car_item), "Car", "My Car", 2, true));

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
