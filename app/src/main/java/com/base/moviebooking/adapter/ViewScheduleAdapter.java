package com.base.moviebooking.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.base.moviebooking.ui.schedule_child.schedule_child1.ScheduleChildFragment1;
import com.base.moviebooking.ui.schedule_child.schedule_child2.ScheduleChildFragment2;
import com.base.moviebooking.ui.schedule_child.schedule_child3.ScheduleChildFragment3;
import com.base.moviebooking.ui.schedule_child.schedule_child4.ScheduleChildFragment4;
import com.base.moviebooking.ui.schedule_child.schedule_child5.ScheduleChildFragment5;
import com.base.moviebooking.ui.schedule_child.schedule_child6.ScheduleChildFragment6;
import com.base.moviebooking.ui.schedule_child.schedule_child7.ScheduleChildFragment7;

public class ViewScheduleAdapter extends FragmentStateAdapter {

    public ViewScheduleAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ScheduleChildFragment1();
            case 1:
                return new ScheduleChildFragment2();
            case 2:
                return new ScheduleChildFragment3();
            case 3:
                return new ScheduleChildFragment4();
            case 4:
                return new ScheduleChildFragment5();
            case 5:
                return new ScheduleChildFragment6();
            case 6:
                return new ScheduleChildFragment7();
            default:
                return new ScheduleChildFragment1();
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
