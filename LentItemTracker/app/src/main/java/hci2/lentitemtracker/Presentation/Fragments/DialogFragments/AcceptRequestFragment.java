package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.Utilities.Util;


public class AcceptRequestFragment extends DialogFragment {

    private String guid;

    ArrayList<ItemDataModel> requestData = UserItemList.getRequestItems();


    public AcceptRequestFragment() {
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            guid = bundle.getString("guid");
        }

        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.request_popup).create();
    }


    @Override
    public void onStart() {
        super.onStart();
        setupAcceptButton();
        setupDeclineButton();
        setupPage();
    }

    private void setupAcceptButton() {
        Button acceptButton = (Button)getDialog().findViewById(R.id.accept_item_button);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ItemDataModel item = UserItemList.getItemByGuid(guid);
                item.setStatus(ItemStatus.LENT);
                UserItemList.removeItemWithGuid(guid);
                UserItemList.addItemToLentList(item);
                Util.refreshData(getActivity(), 3);
                dismiss();
                Toast.makeText(getContext(), "Item request accepted", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupDeclineButton() {
        Button declineButton = (Button)getDialog().findViewById(R.id.decline_item_button);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDataModel item = UserItemList.getItemByGuid(guid);
                UserItemList.removeItemWithGuid(guid);
                Util.refreshData(getActivity(), 3);
                dismiss();
                Toast.makeText(getContext(), "Item request declined", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupPage() {
        ItemDataModel item = UserItemList.getItemByGuid(guid);

        TextView message = (TextView)getDialog().findViewById(R.id.item_detail_descriptionX);
        CheckBox checkBox = (CheckBox)getDialog().findViewById(R.id.item_detail_pickupX);

        message.setText("I really need to borrow your item! If it would be possible, I would like to use it between the dates below.");
        checkBox.setChecked(true);
        checkBox.setClickable(false);

        ImageView image = (ImageView)getDialog().findViewById(R.id.item_detail_imageX);
        image.setImageBitmap(item.getImage());


    }






}
