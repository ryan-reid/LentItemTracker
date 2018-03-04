package hci2.lentitemtracker.Presentation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.R;


public class ItemDetailFragment extends Fragment {
    private static ArrayList<ItemDataModel> itemList = ItemDataModel.createSampleData();

    private static class ViewHolder{
        EditText itemName;
        EditText itemOwner;
        EditText itemDescription;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstance){
        View rootView = inflater.inflate(R.layout.item_detail_layout, container, false);
        ItemDataModel itemDataModel;
        if(savedInstance != null){
            itemDataModel = itemList.get(savedInstance.getInt("index"));
        }else {
            itemDataModel = itemList.get(0);
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.itemName = (EditText) rootView.findViewById(R.id.item_detail_title);
        viewHolder.itemOwner = (EditText) rootView.findViewById(R.id.item_detail_owner);
        viewHolder.itemDescription = (EditText) rootView.findViewById(R.id.item_detail_description);

        viewHolder.itemName.setText(itemDataModel.getTitle());
        viewHolder.itemOwner.setText(itemDataModel.getOwner());
        viewHolder.itemDescription.setText(itemDataModel.getDescription());
        return rootView;
    }
}
