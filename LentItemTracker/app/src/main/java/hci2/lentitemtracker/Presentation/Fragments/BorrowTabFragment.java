package hci2.lentitemtracker.Presentation.Fragments;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.adapters.ItemAdapter;
import hci2.lentitemtracker.adapters.RequestAdapter;

public class BorrowTabFragment extends InventoryFragment {

    private static ItemAdapter itemAdapter;

    public ArrayAdapter createArrayAdapter(){
        itemAdapter = new ItemAdapter(UserItemList.getInstance().getBorrowedItems(), getContext(), "borrowed");
        return itemAdapter;
    }


    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id){
        // show page with item details
    }
}
