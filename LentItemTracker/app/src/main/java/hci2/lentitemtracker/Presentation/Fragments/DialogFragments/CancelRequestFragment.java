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

public class CancelRequestFragment extends DialogFragment {

    private String guid;

    public CancelRequestFragment() {};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            guid = bundle.getString("guid");
        }

        return new AlertDialog.Builder(getActivity()).setTitle("Rescind Request?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ItemDataModel item = UserItemList.getItemByGuid(guid);
                UserItemList.removeItemWithGuid(guid);
                item.setStatus(ItemStatus.AVAILABLE);
                UserItemList.addItemToUserList(item);
                MainActivity activity = (MainActivity) getActivity();
                activity.resetData(3);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        } ).create();

    }


}
