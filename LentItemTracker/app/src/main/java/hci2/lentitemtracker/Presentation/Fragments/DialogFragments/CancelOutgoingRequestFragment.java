package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hci2.lentitemtracker.R;

public class CancelOutgoingRequestFragment extends DialogFragment {

    private Button cancelRequestButton;

    public CancelOutgoingRequestFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.fragment_cancel_outgoing_request_dialog)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO: implement cancel
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        setupCancelRequest();
    }

    public void setupCancelRequest() {
        cancelRequestButton = (Button) this.getDialog().findViewById(R.id.cancelRequestButton);
        cancelRequestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCancelRequestDialog();
            }
        });
    }

    public void showCancelRequestDialog() {
        this.show(this.getFragmentManager(), "cancel_request_dialog");
    }
}
