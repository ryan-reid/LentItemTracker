package hci2.lentitemtracker.Persistence.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import hci2.lentitemtracker.Persistence.IncomingRequestsList;
import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.ConfirmIncomingRequest;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.DeclineIncomingRequest;
import hci2.lentitemtracker.R;


public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private FragmentManager fragmentManager;

    public MyPagerAdapter(FragmentManager fragmentManager, Context context) {
        mContext = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        final ItemDataModel modelObject = IncomingRequestsList.getInstance().getItems().get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.requested_item_for_borrow, collection, false);

        TextView nameOfItem = (TextView) layout.findViewById(R.id.nameOfItem);
        nameOfItem.setText(modelObject.getTitle());

        TextView numItems = (TextView) layout.findViewById(R.id.number_of_items);
        String numItemsToSet = (position +1) + " / " + IncomingRequestsList.getInstance().getItems().size();
        numItems.setText(numItemsToSet);

        ImageView itemImage = (ImageView) layout.findViewById(R.id.imageView_requested_item_for_borrow);
        itemImage.setImageBitmap(modelObject.getImage());

        Button approveButton = (Button)layout.findViewById(R.id.approve_request_item_for_borrow);
        approveButton.setOnClickListener( new View.OnClickListener() {

                                              @Override
                                              public void onClick(View v) {
                                                  ConfirmIncomingRequest fragment = new ConfirmIncomingRequest();
                                                  Bundle bundle = new Bundle();
                                                  bundle.putString("guid", modelObject.getId());
                                                  fragment.setArguments(bundle);

                                                  fragment.show(fragmentManager, "AcceptIncomingRequestFragment");
                                              }
                                          });

        Button declineButton = (Button)layout.findViewById(R.id.decline_request_item_for_borrow2);
        declineButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DeclineIncomingRequest fragment = new DeclineIncomingRequest();
                Bundle bundle = new Bundle();
                bundle.putString("guid", modelObject.getId());
                fragment.setArguments(bundle);

                fragment.show(fragmentManager, "DeclineIncomingRequestFragment");
            }
        });
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
