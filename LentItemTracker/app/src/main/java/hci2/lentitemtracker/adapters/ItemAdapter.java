package hci2.lentitemtracker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;



public class ItemAdapter extends ArrayAdapter implements View.OnClickListener {
    private ArrayList<ItemDataModel> dataModels;
    private Context context;
    private String calledFrom = "neither";

    //View cache
    private static class ViewHolder {
        TextView itemTitle;
        TextView itemOwner;
        TextView itemStatus;
        TextView duration;
        ImageView thumbnail;
    }

    public ItemAdapter(ArrayList<ItemDataModel> dataModels, Context context){
        super(context, R.layout.single_item_list_view, dataModels);
        this.dataModels = dataModels;
        this.context = context;
    }

    public ItemAdapter(ArrayList<ItemDataModel> dataModels, Context context, String calledFrom){
        super(context, R.layout.single_item_list_view, dataModels);
        this.dataModels = dataModels;
        this.context = context;
        this.calledFrom = calledFrom;
    }

    public ItemAdapter(Context context){
        this(UserItemList.getInstance(context).getItems(), context);
    }

    @Override
    public void onClick(View view) {
        int clickedPosition = (Integer) view.getTag();
        ItemDataModel clicked = (ItemDataModel) getItem(clickedPosition);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        ViewHolder viewHolder;
        ItemDataModel dataModel = (ItemDataModel) getItem(position);
        final View result;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.single_item_list_view, viewGroup, false);
            viewHolder.thumbnail = (ImageView) view.findViewById(R.id.itemThumbnail);
            viewHolder.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            viewHolder.itemOwner = (TextView) view.findViewById(R.id.itemOwner);
            viewHolder.duration = (TextView) view.findViewById(R.id.duration);
            viewHolder.thumbnail = (ImageView) view.findViewById(R.id.itemThumbnail);
            viewHolder.itemStatus = (TextView) view.findViewById(R.id.itemStatus);
            result = view;
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
            result = view;
        }

        if(dataModel.getImage() != null) {
            viewHolder.thumbnail.setImageBitmap(dataModel.getImage());
        }

            viewHolder.itemTitle.setText(dataModel.getTitle());
            viewHolder.itemOwner.setText(dataModel.getOwner());
        if(calledFrom.equals("borrowed") || calledFrom.equals("lent")) {
            viewHolder.duration.setText(String.format("%d days", dataModel.getNumDaysWanted()));

        } else {
            viewHolder.duration.setText(String.format("%d days", dataModel.getNumDaysAvailableForLending()));
        }
            viewHolder.itemStatus.setText(dataModel.getStatus().toString());
            viewHolder.thumbnail.setOnClickListener(this);
            viewHolder.thumbnail.setTag(position);
        return result;
    }

    public ItemDataModel getItem(int position){
        return this.dataModels.get(position);
    }
}
