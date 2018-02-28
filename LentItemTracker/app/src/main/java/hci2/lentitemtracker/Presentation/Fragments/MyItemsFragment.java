package hci2.lentitemtracker.Presentation.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AddNewItemFragment;
import hci2.lentitemtracker.R;


public class MyItemsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton addNewItemFAB;

    public MyItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        FloatingActionButton addNewItemFAB = (FloatingActionButton) this.getView().findViewById(R.id.floatingActionButton);
        addNewItemFAB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNewItem();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_my_items, container, false);

        RecyclerView recyclerView = (RecyclerView) myView.findViewById(R.id.recycleViewlist);

        // Set the adapter
        if (recyclerView instanceof RecyclerView) {
            Context context = myView.getContext();

            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(getFragmentManager(), UserItemList.getInstance().getItems(), mListener));
        }



        return myView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
    }

    private void addNewItem() {
        AddNewItemFragment frag = new AddNewItemFragment();
        frag.show(getFragmentManager(), "fragment");
    }


}
