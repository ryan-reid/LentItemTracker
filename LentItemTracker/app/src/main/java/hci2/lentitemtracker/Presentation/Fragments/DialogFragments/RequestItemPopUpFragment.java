package hci2.lentitemtracker.Presentation.Fragments.DialogFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.ItemStatus;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;
import hci2.lentitemtracker.Utilities.Util;

public class RequestItemPopUpFragment extends DialogFragment {


    EditText startDate;
    EditText endDate;
    final Calendar myCalendar = Calendar.getInstance();



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.activity_item_detail).create();
    }

    private static ArrayList<ItemDataModel> dataModels = UserItemList.getItems();

    private final static class ViewHolder{
        TextView itemName;
        TextView itemOwner;
        TextView itemDescription;
        TextView itemStatus;
        ImageView itemImage;
        Integer numDaysAvailableForLending;

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

        setupStartDate();
        setupEndDate();

        }

        private int getDays() {
            String startDateX = ((EditText) this.getDialog().findViewById(R.id.startDate)).getText().toString();
            String endDateX = ((EditText) this.getDialog().findViewById(R.id.endDate)).getText().toString();
            int daysAvailable = 0;

            SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yy");

            try {
                Date date1 = myFormat.parse(startDateX);
                Date date2 = myFormat.parse(endDateX);

                daysAvailable = date2.getDate() - date1.getDate();


            } catch(ParseException e) {
                e.printStackTrace();
            }

            return daysAvailable;
        }

        private void setupStartDate() {
            startDate = (EditText) this.getDialog().findViewById(R.id.startDate);


            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabelStart();
                }

            };

            startDate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    new DatePickerDialog(getDialog().getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
        }

    private void setupEndDate() {
        endDate = (EditText) this.getDialog().findViewById(R.id.endDate);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }

        };

        endDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(getDialog().getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDate.setText(sdf.format(myCalendar.getTime()));
    }


    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDate.setText(sdf.format(myCalendar.getTime()));
    }
    }

