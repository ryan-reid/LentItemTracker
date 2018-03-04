package hci2.lentitemtracker.Presentation.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hci2.lentitemtracker.Presentation.Fragments.ItemDetailFragment;
import hci2.lentitemtracker.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main_activity_2, fragment)
                .addToBackStack(null)
                .commit();
    }
}
