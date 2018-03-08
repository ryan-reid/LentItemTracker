package hci2.lentitemtracker.Presentation.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.R;

public class DetailActivity extends AppCompatActivity {
    private final static ArrayList<ItemDataModel> dataModels = UserItemList.getInstance().getItems();

    private final static class ViewHolder{
        TextView itemName;
        TextView itemOwner;
        TextView itemDescription;
        TextView itemStatus;
        ImageView itemImage;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        int dataItemId = getIntent().getIntExtra("index", 0);
        String calledFrom = getIntent().getStringExtra("calledFrom");
        final ItemDataModel model = dataModels.get(dataItemId);

        ViewHolder holder = new ViewHolder();
        holder.itemName = (TextView) findViewById(R.id.item_detail_title);
        holder.itemOwner = (TextView) findViewById(R.id.item_detail_owner);
        holder.itemDescription = (TextView) findViewById(R.id.item_detail_description);
        holder.itemImage = (ImageView) findViewById(R.id.item_detail_image);
        holder.itemStatus = (TextView) findViewById(R.id.StatusValue);

        holder.itemName.setText(model.getTitle());
        holder.itemOwner.setText(model.getOwner());
        holder.itemDescription.setText(model.getDescription());
        holder.itemImage.setImageBitmap(model.getImage());
        holder.itemStatus.setText(model.getStatus().name());



        Button request_button = (Button) findViewById(R.id.request_item_button);

        if(!calledFrom.equals("inventory")) {
            request_button.setVisibility(View.GONE);
        }
        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Item has been requested", Toast.LENGTH_LONG).show();
                UserItemList.removeItemWithGuid(model.getId());
                UserItemList.addToRequestList(model);
                finish();
            }
        });

    }
}
