package hci2.lentitemtracker.Presentation.Fragments;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.adapters.RequestAdapter;

public class RequestTabFragment extends InventoryFragment {
    public ArrayAdapter createArrayAdapter(){
        return new RequestAdapter(UserItemList.getInstance().getRequestItems(), getContext());
    }

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id){
        // show page with item details
    }
}
