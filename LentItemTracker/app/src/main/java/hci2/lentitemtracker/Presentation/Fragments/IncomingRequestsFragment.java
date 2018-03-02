package hci2.lentitemtracker.Presentation.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hci2.lentitemtracker.Persistence.Adapters.MyPagerAdapter;
import hci2.lentitemtracker.R;


public class IncomingRequestsFragment extends Fragment {

    public IncomingRequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_incoming_requests, container, false);
        ViewPager viewPager = (ViewPager) myView.findViewById(R.id.incoming_requests_viewPager);
        viewPager.setAdapter(new MyPagerAdapter(getContext()));
        return myView;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
