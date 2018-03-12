package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Activities.MainActivity;
import hci2.lentitemtracker.Utilities.Util;

public class AcceptRequestFragment extends DialogFragment {

    private String guid;

    public AcceptRequestFragment() {
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            guid = bundle.getString("guidFromRequestTab");
        }

        return new AlertDialog.Builder(getActivity()).setTitle("Accept Item Request?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ItemDataModel item = UserItemList.getItemByGuid(guid);
                UserItemList.removeItemWithGuid(guid);
                item.setStatus(ItemStatus.LENT);
                Util.refreshData(getActivity(), 3);
                UserItemList.addItemToLentList(item);

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .create();

    }




}
