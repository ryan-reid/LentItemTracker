package hci2.lentitemtracker.Utilities;

import android.app.Activity;

import hci2.lentitemtracker.Presentation.Activities.MainActivity;

public class Util {

    public static void refreshData(Activity activity, int calledFrom) {
        MainActivity activityX = (MainActivity) activity;
        activityX.resetData(calledFrom);
    }

}
