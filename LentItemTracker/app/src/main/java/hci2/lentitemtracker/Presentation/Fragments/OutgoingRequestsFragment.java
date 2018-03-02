package hci2.lentitemtracker.Presentation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hci2.lentitemtracker.R;

public class OutgoingRequestsFragment extends Fragment {

    public OutgoingRequestsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outgoing_requests, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
