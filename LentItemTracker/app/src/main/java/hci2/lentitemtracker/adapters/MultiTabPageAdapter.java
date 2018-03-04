package hci2.lentitemtracker.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import hci2.lentitemtracker.Presentation.Fragments.BorrowTabFragment;
import hci2.lentitemtracker.Presentation.Fragments.InventoryTabFragment;
import hci2.lentitemtracker.Presentation.Fragments.ItemDetailFragment;
import hci2.lentitemtracker.Presentation.Fragments.LentTabFragment;
import hci2.lentitemtracker.Presentation.Fragments.RequestTabFragment;


public class MultiTabPageAdapter extends FragmentPagerAdapter {
    private final String[] PAGE_TITLES = {"Inventory", "Borrowed", "Lent", "Requests"};

    public MultiTabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new BorrowTabFragment();
            case 2:
                return new LentTabFragment();
            case 3:
                return new RequestTabFragment();
            default:
                return new InventoryTabFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        try{
            return PAGE_TITLES[position];
        }catch (IndexOutOfBoundsException exc){
            return "Tab " + (position + 1);
        }
    }
}
