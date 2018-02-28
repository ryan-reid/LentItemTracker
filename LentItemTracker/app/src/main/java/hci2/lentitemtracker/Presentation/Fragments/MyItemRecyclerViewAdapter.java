package hci2.lentitemtracker.Presentation.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.R;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<ItemDataModel> mValues;
    private final MyItemsFragment.OnFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(ArrayList<ItemDataModel> items, MyItemsFragment.OnFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_card_item_in_backpack, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mView.setImageBitmap(mValues.get(position).getImage());
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mDescription.setText(mValues.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mView;
        public final TextView mTitle;
        public final TextView mDescription;

        public ViewHolder(View view) {
            super(view);
            mView = (ImageView) view.findViewById(R.id.rowImageRowCard);
            mTitle = (TextView) view.findViewById(R.id.itemTitleRowCard);
            mDescription = (TextView) view.findViewById(R.id.itemDescriptionRowCard);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}

