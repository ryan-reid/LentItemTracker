package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.Utilities.Util;

public class RequestItemPopUpFragment extends DialogFragment {




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.activity_item_detail).create();
    }

    private static ArrayList<ItemDataModel> dataModels = UserItemList.getItems();

    private final static class ViewHolder{
        TextView itemName;
        TextView itemOwner;
        TextView itemDescription;
        TextView itemStatus;
        ImageView itemImage;

    }

    @Override
    public void onStart() {
        super.onStart();
        final int clickPosition = getArguments().getInt(("clickPosition"));
        final ItemDataModel model = dataModels.get(clickPosition);

        ViewHolder holder = new ViewHolder();
        holder.itemName = (TextView) this.getDialog().findViewById(R.id.item_detail_title);
        holder.itemOwner = (TextView) this.getDialog().findViewById(R.id.item_detail_owner);
        holder.itemDescription = (TextView) this.getDialog().findViewById(R.id.item_detail_description);
        holder.itemImage = (ImageView) this.getDialog().findViewById(R.id.item_detail_image);
        holder.itemStatus = (TextView) this.getDialog().findViewById(R.id.StatusValue);

        holder.itemName.setText(model.getTitle());
        holder.itemOwner.setText(model.getOwner());
        holder.itemDescription.setText(model.getDescription());
        holder.itemImage.setImageBitmap(model.getImage());
        holder.itemStatus.setText(model.getStatus().name());



        Button request_button = (Button) this.getDialog().findViewById(R.id.request_item_button);

        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item has been requested", Toast.LENGTH_LONG).show();
                dataModels.get(clickPosition).setStatus(ItemStatus.PENDING);
                UserItemList.addToRequestList(model);
                Util.refreshData(getActivity(), 0);
                getDialog().dismiss();

            }
        });
    }
}
