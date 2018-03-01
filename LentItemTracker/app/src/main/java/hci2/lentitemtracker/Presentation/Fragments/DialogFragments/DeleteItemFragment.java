package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;

public class DeleteItemFragment extends DialogFragment {

    private String guid;
    private AddNewItemFragment.OnCloseRefreshList mCallback;
    public DeleteItemFragment() {};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddNewItemFragment.OnCloseRefreshList) {
            mCallback = (AddNewItemFragment.OnCloseRefreshList) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }

    }

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
                mCallback.onCloseRefreshList();

            }
        }).create();

    }

    public interface deleteFragmentInterface {
        void onDeleteDeleteItemFromList(String guid);
    }




}
