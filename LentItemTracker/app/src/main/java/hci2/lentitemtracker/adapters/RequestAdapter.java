package hci2.lentitemtracker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.R;


public class RequestAdapter extends ItemAdapter {
    protected int resource = R.layout.request_item_row;

    public RequestAdapter(Context context) {
        super(context);
    }

    public RequestAdapter(ArrayList<ItemDataModel> dataModels, Context context) {
        super(dataModels, context);
    }

    private static class RequestRowView{
        TextView itemRequester;
        TextView numberOfDays;
        TextView itemTitle;
        ImageView itemImage;
        ImageView arrowPointer;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        RequestAdapter.RequestRowView viewHolder;
        ItemDataModel dataModel = this.getItem(position);
        final View result;
        if(view == null){
            viewHolder = new RequestAdapter.RequestRowView();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.request_item_row, viewGroup, false);
            viewHolder.itemTitle = (TextView) view.findViewById(R.id.request_item_title);
            viewHolder.itemRequester = (TextView) view.findViewById(R.id.request_item_owner);
            viewHolder.numberOfDays = (TextView) view.findViewById(R.id.request_item_days_available);
            viewHolder.itemImage = (ImageView) view.findViewById(R.id.request_item_image);
            viewHolder.arrowPointer = (ImageView) view.findViewById(R.id.request_item_arrow);
            result = view;
            view.setTag(viewHolder);
        }else{
            viewHolder = (RequestAdapter.RequestRowView) view.getTag();
            result = view;
        }

        viewHolder.itemTitle.setText(dataModel.getTitle());
        viewHolder.itemRequester.setText(dataModel.getOwner());
        viewHolder.numberOfDays.setText(String.format("%d days", dataModel.getNumDaysAvailableForLending()));
        viewHolder.itemImage.setImageBitmap(dataModel.getImage());
        Random randomGenerator = new Random();
        int randNumber = randomGenerator.nextInt(10);
        if(randNumber <= 5){
            viewHolder.arrowPointer.setImageResource(R.drawable.inbound_arrow);
        }else{
            viewHolder.arrowPointer.setImageResource(R.drawable.outbound_arrow);
        }
        return result;
    }
}
