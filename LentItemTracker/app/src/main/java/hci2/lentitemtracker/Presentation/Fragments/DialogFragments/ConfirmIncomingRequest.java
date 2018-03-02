package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import hci2.lentitemtracker.Persistence.IncomingRequestsList;


public class ConfirmIncomingRequest extends DialogFragment {

    private String guid;
    private ConfirmIncomingRequest.confirmIncomingRequestInterface mCallback;
    public ConfirmIncomingRequest() {};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ConfirmIncomingRequest.confirmIncomingRequestInterface) {
            mCallback = (ConfirmIncomingRequest.confirmIncomingRequestInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement confirmIncomingRequestInterface");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            guid = bundle.getString("guid");
        }

        return new AlertDialog.Builder(getActivity()).setTitle("Accept Item Request?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  IncomingRequestsList.getInstance().setAvailability(guid, false);
                        IncomingRequestsList.removeItemWithGuid(guid);
                        getDialog().dismiss();
                        mCallback.refreshIncomingRequestList();

                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                })

                .create();

    }

    public interface confirmIncomingRequestInterface {
        void refreshIncomingRequestList();
    }




}
