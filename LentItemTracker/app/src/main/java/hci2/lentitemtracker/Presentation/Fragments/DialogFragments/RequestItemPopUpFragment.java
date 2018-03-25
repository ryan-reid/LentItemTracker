package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.Utilities.Util;

public class RequestItemPopUpFragment extends DialogFragment {
    private EditText startDate;
    private EditText endDate;
    private final Calendar startCalendar = Calendar.getInstance();
    private final Calendar endCalendar = Calendar.getInstance();
    private final String dateFormat = "MM/dd/yyyy";
    private final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CANADA);

    private final DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(
                DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            startCalendar.set(Calendar.YEAR, year);
            startCalendar.set(Calendar.MONTH, monthOfYear);
            startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateStartDate();
        }
    };

    private final DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            endCalendar.set(Calendar.YEAR, i);
            endCalendar.set(Calendar.MONTH, i1);
            endCalendar.set(Calendar.DAY_OF_MONTH, i2);
            updateEndDate();
        }
    };

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.activity_item_detail).create();
    }

    private void updateStartDate(){
        this.startDate.setText(sdf.format(startCalendar.getTime()));
    }

    private void updateEndDate(){
        this.endDate.setText(sdf.format(endCalendar.getTime()));
    }

    private static ArrayList<ItemDataModel> dataModels = UserItemList.getItems();

    private final static class ViewHolder{
        TextView itemName;
        TextView itemOwner;
        TextView itemDescription;
        TextView itemStatus;
        ImageView itemImage;
    }

    @Override
    public void onStart() {
        super.onStart();
        final int clickPosition = getArguments().getInt(("clickPosition"));
        final ItemDataModel model = dataModels.get(clickPosition);

        ViewHolder holder = new ViewHolder();
        holder.itemName = (TextView) this.getDialog().findViewById(R.id.item_detail_title);
        holder.itemOwner = (TextView) this.getDialog().findViewById(R.id.item_detail_owner);
        holder.itemDescription = (TextView) this.getDialog().findViewById(R.id.item_detail_description);
        holder.itemImage = (ImageView) this.getDialog().findViewById(R.id.item_detail_image);
        holder.itemStatus = (TextView) this.getDialog().findViewById(R.id.StatusValue);

        holder.itemName.setText(model.getTitle());
        holder.itemOwner.setText(model.getOwner());
        holder.itemDescription.setText(model.getDescription());
        holder.itemImage.setImageBitmap(model.getImage());
        holder.itemStatus.setText(model.getStatus().name());

        startDate = (EditText) this.getDialog().findViewById(R.id.startDate);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        getContext(),
                        startDateListener,
                        startCalendar.get(Calendar.YEAR),
                        startCalendar.get(Calendar.MONTH),
                        startCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDate = (EditText) this.getDialog().findViewById(R.id.endDate);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(
                        getContext(),
                        endDateListener,
                        endCalendar.get(Calendar.YEAR),
                        endCalendar.get(Calendar.MONTH),
                        endCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button request_button = (Button) this.getDialog().findViewById(R.id.request_item_button);

        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Item has been requested", Toast.LENGTH_LONG).show();
                dataModels.get(clickPosition).setStatus(ItemStatus.PENDING);

                model.setNumDaysWanted(getDays());
                UserItemList.addToRequestList(model);
                Util.refreshData(getActivity(), 0);
                getDialog().dismiss();
            }
        });
    }

    private int getDays() {
        int daysAvailable = 1;

        try {
            Date start = startCalendar.getTime();
            Date end = endCalendar.getTime();

            long diff = end.getTime() - start.getTime();

            daysAvailable = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            // Minimum number of days that an item can be borrowed for

            if(daysAvailable < 1){
                return 1;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return daysAvailable;
    }
}

