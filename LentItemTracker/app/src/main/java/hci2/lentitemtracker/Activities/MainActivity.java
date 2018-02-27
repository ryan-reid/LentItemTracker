package hci2.lentitemtracker.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import hci2.lentitemtracker.Formatting.BottomNavigationViewHelper;
import hci2.lentitemtracker.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_outgoing_requests:
                    mTextMessage.setText(R.string.outgoing_requests);
                    return true;
                case R.id.navigation_incoming_requests:
                    mTextMessage.setText(R.string.incoming_requests);
                    return true;
                case R.id.navigation_backpack:
                    mTextMessage.setText(R.string.backpack);
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.search);
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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
