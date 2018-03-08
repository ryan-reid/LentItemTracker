package hci2.lentitemtracker.Presentation.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import hci2.lentitemtracker.Presentation.Activities.DetailActivity;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.adapters.ItemAdapter;


public class InventoryFragment extends Fragment {
    protected ArrayAdapter listItemAdapter;
    protected ListView listView;
    protected int resource = R.layout.inventory_listing;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstance){
        View rootView = inflater.inflate(resource,
                container, false);
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

    public ArrayAdapter createArrayAdapter(){
        return new ItemAdapter(getContext());
    }

    // Override in child class to specify which action to take
    public void itemClickListener(AdapterView<?> parent, View view, int position, long id){
        Intent intent = new Intent(this.getActivity(), DetailActivity.class);
        intent.putExtra("index", position);
        intent.putExtra("calledFrom", "inventory");
        startActivity(intent);
    }
}
