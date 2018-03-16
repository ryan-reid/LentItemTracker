package hci2.lentitemtracker.Presentation.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import hci2.lentitemtracker.R;

public class InventoryTabFragment extends InventoryFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstance){
        View rootView = inflater.inflate(resource, container, false);
        this.listItemAdapter = this.createArrayAdapter();
        listView = (ListView) rootView.findViewById(R.id.main_activity_list);
        listView.setAdapter(listItemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemClickListener(adapterView, view, i, l);
            }
        });

        return rootView;
    }

}
