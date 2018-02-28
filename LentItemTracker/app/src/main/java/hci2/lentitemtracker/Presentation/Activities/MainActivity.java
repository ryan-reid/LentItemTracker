package hci2.lentitemtracker.Presentation.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import hci2.lentitemtracker.Presentation.Formatting.BottomNavigationViewHelper;
import hci2.lentitemtracker.Presentation.Fragments.IncomingRequestsFragment;
import hci2.lentitemtracker.Presentation.Fragments.MyItemsFragment;
import hci2.lentitemtracker.Presentation.Fragments.OutgoingRequestsFragment;
import hci2.lentitemtracker.Presentation.Fragments.SearchFragment;
import hci2.lentitemtracker.R;

public class MainActivity extends AppCompatActivity implements IncomingRequestsFragment.OnFragmentInteractionListener, MyItemsFragment.OnFragmentInteractionListener, OutgoingRequestsFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener {

    private TextView mTextMessage;
    FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_outgoing_requests:
                    mTextMessage.setText(R.string.outgoing_requests);
                    fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new OutgoingRequestsFragment()).commit();
                    return true;
                case R.id.navigation_incoming_requests:
                    mTextMessage.setText(R.string.incoming_requests);
                    fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new IncomingRequestsFragment()).commit();
                    return true;
                case R.id.navigation_backpack:
                    mTextMessage.setText(R.string.backpack);
                    fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new MyItemsFragment()).commit();
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.search);
                    fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new SearchFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new SearchFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
