package hci2.lentitemtracker.Persistence.Adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import hci2.lentitemtracker.Persistence.ItemDataModel;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.DeleteItemFragment;
import hci2.lentitemtracker.Presentation.Fragments.MyItemsFragment;
import hci2.lentitemtracker.R;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<ItemDataModel> mValues;
    private final MyItemsFragment.OnFragmentInteractionListener mListener;
    FragmentManager fragmentManager;

    public MyItemRecyclerViewAdapter(FragmentManager fragmentManager, ArrayList<ItemDataModel> items, MyItemsFragment.OnFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_card_item_in_backpack, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mView.setImageBitmap(mValues.get(position).getImage());
        holder.mTitle.setText(mValues.get(position).getTitle());
        holder.mDescription.setText(mValues.get(position).getDescription());

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteItemFragment fragment = new DeleteItemFragment();
                Bundle bundle = new Bundle();
                bundle.putString("guid", mValues.get(position).getId());
                fragment.setArguments(bundle);

                fragment.show(fragmentManager, "fragment");

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
        public final ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mView = (ImageView) view.findViewById(R.id.rowImageRowCard);
            mTitle = (TextView) view.findViewById(R.id.itemTitleRowCard);
            mDescription = (TextView) view.findViewById(R.id.itemDescriptionRowCard);
            mDeleteButton = (ImageButton) view.findViewById(R.id.deletebuttonRowCard);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}

