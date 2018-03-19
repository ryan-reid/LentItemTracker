package hci2.lentitemtracker.Presentation.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AcceptRequestFragment;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.CancelRequestFragment;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.Utilities.Util;
import hci2.lentitemtracker.adapters.ItemAdapter;
import hci2.lentitemtracker.adapters.RequestAdapter;

public class RequestTabFragment extends InventoryFragment {
    private SwipeActionAdapter listItemAdapter;

    public ArrayAdapter createArrayAdapter(){
        RequestAdapter requestAdapter = new RequestAdapter(UserItemList.getInstance().getRequestItems(), getContext(), getFragmentManager());
        return requestAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){
        View view = layoutInflater.inflate(resource, viewGroup, false);
        this.listItemAdapter = new SwipeActionAdapter(this.createArrayAdapter());
        listView = (ListView) view.findViewById(R.id.main_activity_list);
        listItemAdapter.setListView(listView);
        listView.setAdapter(listItemAdapter);

        listItemAdapter.addBackground(SwipeDirection.DIRECTION_FAR_LEFT, R.layout.row_swipe_decline)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_LEFT, R.layout.row_swipe_decline)
                .addBackground(SwipeDirection.DIRECTION_FAR_RIGHT, R.layout.row_swipe_approve)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_RIGHT, R.layout.row_swipe_approve);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClickListener(adapterView, view, i, l);
            }
        });

        listItemAdapter.setSwipeActionListener(new SwipeActionAdapter.SwipeActionListener() {
            @Override
            public boolean hasActions(int position, SwipeDirection direction) {
                return direction.isLeft() || direction.isRight();
            }

            @Override
            public boolean shouldDismiss(int position, SwipeDirection direction) {
                return false;
            }

            @Override
            public void onSwipe(int[] position, SwipeDirection[] direction) {
                RequestAdapter adapter = (RequestAdapter) listItemAdapter.getAdapter();
                for(int i: position) {
                    ItemDataModel model = adapter.getItem(i);
                    try {
                        switch (direction[i]) {
                            case DIRECTION_FAR_RIGHT:
                                model.setStatus(ItemStatus.LENT);
                                UserItemList.addItemToLentList(model);
                                adapter.removeItem(model);
                                Toast.makeText(getContext(), "Item request accepted", Toast.LENGTH_LONG).show();
                            case DIRECTION_FAR_LEFT:
                                adapter.removeItem(model);
                                UserItemList.addItemToUserList(model);
                                Toast.makeText(getContext(), "Item request declined", Toast.LENGTH_LONG).show();
                        }
                        Util.refreshData(getActivity(), 3);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
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


