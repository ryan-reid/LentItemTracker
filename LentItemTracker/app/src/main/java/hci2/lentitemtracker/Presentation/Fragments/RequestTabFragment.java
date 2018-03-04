package hci2.lentitemtracker.Presentation.Fragments;


import android.widget.ArrayAdapter;

import hci2.lentitemtracker.adapters.RequestAdapter;

public class RequestTabFragment extends ListFragment {
    public ArrayAdapter createArrayAdapter(){
        return new RequestAdapter(getContext());
    }
}
