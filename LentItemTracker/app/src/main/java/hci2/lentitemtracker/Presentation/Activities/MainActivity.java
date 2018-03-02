package hci2.lentitemtracker.Presentation.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import hci2.lentitemtracker.Persistence.IncomingRequestsList;
import hci2.lentitemtracker.Presentation.Formatting.BottomNavigationViewHelper;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AddNewItemFragment;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.ConfirmIncomingRequest;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.DeclineIncomingRequest;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.DeleteItemFragment;
import hci2.lentitemtracker.Presentation.Fragments.HomePageFragment;
import hci2.lentitemtracker.Presentation.Fragments.IncomingRequestsFragment;
import hci2.lentitemtracker.Presentation.Fragments.MyItemsFragment;
import hci2.lentitemtracker.Presentation.Fragments.OutgoingRequestsFragment;
import hci2.lentitemtracker.Presentation.Fragments.SearchFragment;
import hci2.lentitemtracker.R;

public class MainActivity extends AppCompatActivity implements AddNewItemFragment.newItemInterface, DeleteItemFragment.deleteFragmentInterface, ConfirmIncomingRequest.confirmIncomingRequestInterface, DeclineIncomingRequest.declineIncomingRequestInterface{

    private TextView mTextMessage;
    FragmentManager fragmentManager;
    AddNewItemFragment.newItemInterface closeRefreshInterface;

    private static final String OUTGOING_REQUESTS_TAG = "Outgoing Requests Fragment";
    private static final String INCOMING_REQUESTS_TAG = "Incoming Requests Fragment";
    private static final String MY_ITEMS_TAG = "My Items Fragment";
    private static final String SEARCH_TAG = "Search Fragment";
    private static final String HOME_PAGE = "Home Page";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_outgoing_requests:
                    mTextMessage.setText(R.string.outgoing_requests);
                    fragmentManager.beginTransaction().replace(R.id.container, new OutgoingRequestsFragment(), OUTGOING_REQUESTS_TAG ).commit();
                    return true;
                case R.id.navigation_incoming_requests:
                    mTextMessage.setText(R.string.incoming_requests);
                    fragmentManager.beginTransaction().replace(R.id.container, new IncomingRequestsFragment(), INCOMING_REQUESTS_TAG).commit();
                    return true;
                case R.id.navigation_backpack:
                    mTextMessage.setText(R.string.backpack);
                    fragmentManager.beginTransaction().replace(R.id.container, new MyItemsFragment(), MY_ITEMS_TAG).commit();
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.search);
                    fragmentManager.beginTransaction().replace(R.id.container, new SearchFragment(), SEARCH_TAG).commit();
                    return true;
                case R.id.home_page:
                    mTextMessage.setText("Home Page");
                    fragmentManager.beginTransaction().replace(R.id.container, new HomePageFragment(), HOME_PAGE).commit();
                    return true;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        IncomingRequestsList.setContext(this);

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new SearchFragment()).commit();
    }

    @Override
    public void myItemsRefresh(String guid) {
        MyItemsFragment itemFragment = (MyItemsFragment) fragmentManager.findFragmentByTag(MY_ITEMS_TAG);
        itemFragment.refreshList();
    }

    @Override
    public void onCloseRefreshList() {
        MyItemsFragment itemFragment = (MyItemsFragment) fragmentManager.findFragmentByTag(MY_ITEMS_TAG);
        itemFragment.refreshList();
    }

    @Override
    public void refreshIncomingRequestList() {
        IncomingRequestsFragment itemFragment = (IncomingRequestsFragment) fragmentManager.findFragmentByTag(INCOMING_REQUESTS_TAG);
        itemFragment.refreshList();

    }
}
