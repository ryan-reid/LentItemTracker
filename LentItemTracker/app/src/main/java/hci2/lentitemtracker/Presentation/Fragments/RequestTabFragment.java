package hci2.lentitemtracker.Presentation.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AcceptRequestFragment;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.CancelRequestFragment;
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
        if(UserItemList.getRequestItems().get(position).getStatus().name().equals(ItemStatus.INCOMING.name())) {
            promptIncoming(UserItemList.getRequestItems().get(position).getId());
        } else {
            promptOutgoing(UserItemList.getRequestItems().get(position).getId());
        }

    }


    private void promptIncoming(String guid) {
        AcceptRequestFragment frag = new AcceptRequestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("guid", guid);
        frag.setArguments(bundle);
        frag.show(getFragmentManager(), "addNewItemFragment");
    }

    private void promptOutgoing(String guid) {
        CancelRequestFragment frag = new CancelRequestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("guid", guid);
        frag.setArguments(bundle);
        frag.show(getFragmentManager(), "promptIncoming");
    }
}


