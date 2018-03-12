package hci2.lentitemtracker.Presentation.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import hci2.lentitemtracker.Persistence.UserItemList;
import hci2.lentitemtracker.Presentation.Fragments.DialogFragments.AddNewItemFragment;
import hci2.lentitemtracker.adapters.MultiTabPageAdapter;
import hci2.lentitemtracker.R;


public class MainActivity extends AppCompatActivity {
    MultiTabPageAdapter pageAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    private MenuItem searchMenu;
    private boolean isSearchOpen = false;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        UserItemList.setContext(this);


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pageAdapter = new MultiTabPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        final Activity activity = this;

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_menu_my_items);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bottom_menu_new_item:
                                addNewItem();
                        }
                        return false;
                    }
                }
        );

    }

    public void resetData(int calledFrom) {
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(calledFrom);
    }

    private void addNewItem() {
        AddNewItemFragment frag = new AddNewItemFragment();
        FragmentManager fm = this.getSupportFragmentManager();
        frag.show(fm, "addNewItemFragment");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        searchMenu = menu.findItem(R.id.app_bar_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(item.getItemId()) {
            case R.id.app_bar_search:
                handleSearchMenu();
                return true;
            case R.id.data_refresh_item:
                UserItemList.refreshData();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleSearchMenu(){
        ActionBar actionBar = getSupportActionBar();
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(isSearchOpen){
            actionBar.setDisplayShowCustomEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);


            inputMethodManager.hideSoftInputFromWindow(searchText.getWindowToken(), 0);

            searchMenu.setIcon(getResources().getDrawable(R.drawable.search));

            isSearchOpen = false;
        }else{
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.search_bar);
            actionBar.setDisplayShowTitleEnabled(true);

            searchText = (EditText) actionBar.getCustomView().findViewById(R.id.search_text);
            searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if(i == EditorInfo.IME_ACTION_SEARCH){
                        query_data_base();
                        return true;
                    }
                    return false;
                }
            });
            searchText.requestFocus();
            inputMethodManager.showSoftInput(searchText, InputMethodManager.SHOW_IMPLICIT);

            searchMenu.setIcon(getResources().getDrawable(R.drawable.search));

            isSearchOpen = true;
        }
    }
    // To filter list items
    public void query_data_base(){

    }

    @Override
    public void onBackPressed(){
        if(isSearchOpen) {
            handleSearchMenu();
        }
        super.onBackPressed();
    }

}
