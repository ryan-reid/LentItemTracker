package hci2.lentitemtracker.Presentation.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Activities.DetailActivity;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AcceptRequestFragment;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AddNewItemFragment;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.adapters.RequestAdapter;

public class RequestTabFragment extends InventoryFragment {

    private static Context context;
    private static RequestAdapter requestAdapter;

    public ArrayAdapter createArrayAdapter(){
        requestAdapter = new RequestAdapter(UserItemList.getInstance().getRequestItems(), getContext(), getFragmentManager());
        return requestAdapter;
    }

    @Override
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id){
        addNewItem(UserItemList.getRequestItems().get(position).getId());
    }


    private void addNewItem(String guid) {
        AcceptRequestFragment frag = new AcceptRequestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("guidFromRequestTab", guid);
        frag.setArguments(bundle);
        frag.show(getFragmentManager(), "addNewItemFragment");
    }


}
