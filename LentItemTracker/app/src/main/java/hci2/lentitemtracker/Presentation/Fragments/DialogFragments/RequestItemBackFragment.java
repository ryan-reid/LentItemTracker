package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Activities.MainActivity;
import hci2.lentitemtracker.Utilities.Util;

public class RequestItemBackFragment extends DialogFragment {

    private int indexClicked;

    public RequestItemBackFragment() {};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            indexClicked = bundle.getInt("index");
        }

        return new AlertDialog.Builder(getActivity()).setTitle("Request item back from borrower?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserItemList.getLentItems().get(indexClicked).setStatus(ItemStatus.RECALLED);
                Util.refreshData(getActivity(), 2);
                dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
            }).create();
    }


}
