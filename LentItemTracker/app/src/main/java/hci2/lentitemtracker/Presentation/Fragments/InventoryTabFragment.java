package hci2.lentitemtracker.Presentation.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.adapters.ItemAdapter;

public class InventoryTabFragment extends InventoryFragment {
    protected SwipeActionAdapter listItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstance){
        View rootView = inflater.inflate(resource, container, false);
        listItemAdapter = new SwipeActionAdapter(this.createArrayAdapter());
        listView = (ListView) rootView.findViewById(R.id.main_activity_list);
        listItemAdapter.setListView(listView);
        listView.setAdapter(listItemAdapter);

        // Set backgrounds for the swipe directions
        listItemAdapter.addBackground(SwipeDirection.DIRECTION_FAR_LEFT,R.layout.row_swipe_layout)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_LEFT,R.layout.row_swipe_layout)
                .addBackground(SwipeDirection.DIRECTION_FAR_RIGHT,R.layout.row_swipe_layout)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_RIGHT,R.layout.row_swipe_layout);

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
                ItemAdapter adapter = (ItemAdapter) listItemAdapter.getAdapter();
                for (int pos : position) {
                    ItemDataModel model = (ItemDataModel) listItemAdapter.getItem(pos);
                    UserItemList.addToRequestList(model);
                    adapter.removeItem(model);
                    listItemAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), model.getTitle() + " has been requested", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}
