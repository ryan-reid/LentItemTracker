package hci2.lentitemtracker.Presentation.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AcceptRequestFragment;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.RequestItemBackFragment;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.RequestItemPopUpFragment;
import hci2.lentitemtracker.adapters.ItemAdapter;
import hci2.lentitemtracker.adapters.RequestAdapter;


public class LentTabFragment extends InventoryFragment {

    private static ItemAdapter itemAdapter;

    public ArrayAdapter createArrayAdapter(){
        itemAdapter = new ItemAdapter(UserItemList.getInstance().getLentItems(), getContext());
        return itemAdapter;
    }

    public void itemClickListener(AdapterView<?> parent, View view, int position, long id){

        RequestItemBackFragment frag = new RequestItemBackFragment();
        FragmentManager fm = getFragmentManager();
        Bundle bundle = new Bundle();

        bundle.putInt("index", position);
        frag.setArguments(bundle);
        frag.show(fm, "addNewItemFragment");
    }
}
