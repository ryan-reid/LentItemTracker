package hci2.lentitemtracker.Presentation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hci2.lentitemtracker.Persistence.Adapters.MyPagerAdapter;
import hci2.lentitemtracker.R;

public class HomePageFragment extends Fragment {
    public HomePageFragment() {
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
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
