package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Fragments.MyItemsFragment;
import hci2.lentitemtracker.R;

public class AddNewItemFragment extends DialogFragment {


    private Button uploadImageButton;
    private Button okButton;
    private Button cancelButton;
    private Bitmap itemImage;
    private newItemInterface mCallback;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof newItemInterface) {
            mCallback = (newItemInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement newItemInterface");
        }

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
            try {
                itemImage = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), selectedImage);
                ImageView imagePreview =  (ImageView) this.getDialog().findViewById(R.id.uploadedImagePreview);
                imagePreview.setImageBitmap(itemImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //big boys need big interfaces
    public interface newItemInterface {
        void onCloseRefreshList();
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

                if(runValidation()) {
                    dataModel = new ItemDataModel(itemImage, ((EditText) getDialog().findViewById(R.id.itemName)).getText().toString(), ((EditText) getDialog().findViewById(R.id.itemDescription)).getText().toString(), Integer.parseInt(((EditText) getDialog().findViewById(R.id.daysAvailableInt)).getText().toString()), true);
                    UserItemList.getInstance().addItemToUserList(dataModel);
                    mCallback.onCloseRefreshList();
                    getDialog().dismiss();
                }
            }
        });

    }

    private boolean runValidation() {
        if (!isitemNameValid() || !isItemDescriptionValid() || !isDaysAvailableValid()) {
            return false;
        }
        return true;
    }

    private boolean isitemNameValid() {
        if (TextUtils.isEmpty(((EditText) getDialog().findViewById(R.id.itemName)).getText())) {
            ((EditText) getDialog().findViewById(R.id.itemName)).setError("Required Field");
            return false;
        }
        return true;
    }

    private boolean isItemDescriptionValid() {
        if (TextUtils.isEmpty(((EditText) getDialog().findViewById(R.id.itemDescription)).getText())) {
            ((EditText) getDialog().findViewById(R.id.itemDescription)).setError("Required Field");
            return false;
        }
        return true;
    }

    private boolean isDaysAvailableValid() {
        if (TextUtils.isEmpty(((EditText) getDialog().findViewById(R.id.daysAvailableInt)).getText())) {
            ((EditText) getDialog().findViewById(R.id.daysAvailableInt)).setError("Required Field");
            return false;
        }
        return true;


    }
}

