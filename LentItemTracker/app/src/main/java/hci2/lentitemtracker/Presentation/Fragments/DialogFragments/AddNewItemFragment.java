package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Activities.MainActivity;
import hci2.lentitemtracker.R;

public class AddNewItemFragment extends DialogFragment {


    private Button uploadImageButton;
    private Button okButton;
    private Button cancelButton;
    private OnFragmentInteractionListener mListener;
    private ItemDataModel dataModel;

    public AddNewItemFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.fragment_add_new_item).create();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        setupUploadButton();
        setupOKButton();
        setupCancelButton();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==3 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
                dataModel = new ItemDataModel(bitmap, ((EditText)getDialog().findViewById(R.id.itemName)).getText().toString(), ((EditText)getDialog().findViewById(R.id.itemDescription)).getText().toString(), Integer.parseInt(((EditText)getDialog().findViewById(R.id.daysAvailableInt)).getText().toString()), ItemStatus.AVAILABLE);
                ImageView imagePreview =  (ImageView) this.getDialog().findViewById(R.id.uploadedImagePreview);
                imagePreview.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setupUploadButton() {
        uploadImageButton =  (Button) this.getDialog().findViewById(R.id.uploadImageButton);
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 3);

            }
        });
    }

    private void setupCancelButton() {
        cancelButton = (Button) this.getDialog().findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void setupOKButton() {
        okButton = (Button) this.getDialog().findViewById(R.id.OKButton);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserItemList.getInstance().addItemToUserList(dataModel);
                MainActivity activity = (MainActivity) getActivity();
                activity.resetData(0);
                getDialog().dismiss();

            }
        });

    }

}
