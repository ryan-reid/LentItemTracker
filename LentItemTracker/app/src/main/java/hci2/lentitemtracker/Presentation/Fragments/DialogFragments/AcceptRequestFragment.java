package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;

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
            }
        });

    }

    private void setupDeclineButton() {

    }






}
