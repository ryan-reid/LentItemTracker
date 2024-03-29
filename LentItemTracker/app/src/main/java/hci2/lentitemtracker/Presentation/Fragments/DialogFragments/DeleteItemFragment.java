package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Activities.MainActivity;

public class DeleteItemFragment extends DialogFragment {

    private String guid;

    public DeleteItemFragment() {};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            guid = bundle.getString("guid");
        }

        return new AlertDialog.Builder(getActivity()).setTitle("Delete Item?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserItemList.removeItemWithGuid(guid);
                MainActivity activity = (MainActivity) getActivity();
                activity.resetData(0);

            }
        }).create();

    }


}
