package hci2.lentitemtracker.Persistence.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import hci2.lentitemtracker.Persistence.IncomingRequestsList;
import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.R;


public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;

    public MyPagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ItemDataModel modelObject = IncomingRequestsList.getInstance().getItems().get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.requested_item_for_borrow, collection, false);

        TextView nameOfItem = (TextView) layout.findViewById(R.id.nameOfItem);
        nameOfItem.setText(modelObject.getTitle());

        TextView numItems = (TextView) layout.findViewById(R.id.number_of_items);
        String numItemsToSet = (position +1) + " / " + IncomingRequestsList.getInstance().getItems().size();
        numItems.setText(numItemsToSet);

        ImageView itemImage = (ImageView) layout.findViewById(R.id.imageView_requested_item_for_borrow);
        itemImage.setImageBitmap(modelObject.getImage());


        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return IncomingRequestsList.getInstance().getItems().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



}
