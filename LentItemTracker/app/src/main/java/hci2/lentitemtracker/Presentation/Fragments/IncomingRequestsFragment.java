package hci2.lentitemtracker.Presentation.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hci2.lentitemtracker.Persistence.Adapters.MyPagerAdapter;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.ConfirmIncomingRequest;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.DeleteItemFragment;
import hci2.lentitemtracker.R;


public class IncomingRequestsFragment extends Fragment {

    private View thisView;
    private ViewPager thisViewPager;

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
        thisView = inflater.inflate(R.layout.fragment_incoming_requests, container, false);
        thisViewPager = (ViewPager) thisView.findViewById(R.id.incoming_requests_viewPager);
        thisViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(), getContext()));
        return thisView;
    }

    public void refreshList() {
        thisViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(), getContext()));
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
