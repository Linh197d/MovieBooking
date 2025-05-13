package com.base.moviebooking.ui.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.ScheduleAdapter;
import com.base.moviebooking.adapter.ViewScheduleAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ScheduleCinemaFragmentBinding;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.ui.theater.TheaterFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScheduleCinemaFragment extends BaseFragment<ScheduleCinemaFragmentBinding> {
    Theater theater;
    List<String> item_Lich = new ArrayList<>();
    private ScheduleCinemaModel mViewModel;
    private ViewScheduleAdapter viewPagerAdapter;
    private ScheduleAdapter scheduleAdapter;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean backPressed() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        mViewController.replaceFragment(TheaterFragment.class, null);
        return false;
    }

    @Override
    public void initView() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        mViewModel = new ViewModelProvider(this).get(ScheduleCinemaModel.class);
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            theater = (Theater) bundle.getSerializable("theater");
            // Xử lý dữ liệu trong bundle
            mViewModel.sendDataTheater(theater);
            viewPagerAdapter = new ViewScheduleAdapter(this);
            binding.viewpagerLichphim.setAdapter(viewPagerAdapter);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(calendar.getTime());
            item_Lich.add(dateString);

            for (int i = 0; i < 7; i++) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                String date2 = formatter.format(calendar.getTime());
                item_Lich.add(date2);
            }
            new TabLayoutMediator(binding.tabLayout, binding.viewpagerLichphim, new TabLayoutMediator.TabConfigurationStrategy() {

                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    switch (position) {
                        case 0:
                            tab.setText(item_Lich.get(0).toString());
                            String selectedDate = fomatDate(item_Lich.get(0));
                            mViewModel.sendDay(selectedDate);
                            break;
                        case 1:
                            tab.setText(item_Lich.get(1).toString());
                            String selectedDate1 = fomatDate(item_Lich.get(1));
                            mViewModel.sendDay1(selectedDate1);
                            break;
                        case 2:
                            tab.setText(item_Lich.get(2).toString());
                            String selectedDate2 = fomatDate(item_Lich.get(2));
                            mViewModel.sendDay2(selectedDate2);
                            break;
                        case 3:
                            tab.setText(item_Lich.get(3).toString());
                            String selectedDate3 = fomatDate(item_Lich.get(3));
                            mViewModel.sendDay3(selectedDate3);
                            break;
                        case 4:
                            tab.setText(item_Lich.get(4).toString());
                            String selectedDate4 = fomatDate(item_Lich.get(4));
                            mViewModel.sendDay4(selectedDate4);
                            break;
                        case 5:
                            tab.setText(item_Lich.get(5).toString());
                            String selectedDate5 = fomatDate(item_Lich.get(5));
                            mViewModel.sendDay5(selectedDate5);
                            break;
                        case 6:
                            tab.setText(item_Lich.get(6).toString());
                            String selectedDate6 = fomatDate(item_Lich.get(6));
                            mViewModel.sendDay6(selectedDate6);
                            break;
                    }
                }
            }).attach();
            //toolbar
            activity.setSupportActionBar(binding.toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

        } else {
            // Không có dữ liệu để xử lý
            Log.d("cather", "des NO data");
        }
    }

    @Override
    public void initData() {
        eventClick();
    }

    private void eventClick() {
        // set event click back
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.replaceFragment(TheaterFragment.class, null);

            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Thay đổi màu của thanh gạch chân khi tab được chọn
                binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
//                if (!item_Lich.isEmpty() && tab.getPosition() < item_Lich.size()) {
//                    String selectedDate = fomatDate(item_Lich.get(tab.getPosition()));
//                    mViewModel.sendDay(selectedDate);
//                } else {
//                    Calendar calendar = Calendar.getInstance();
//                    String selectedDate = fomatDate(String.valueOf(calendar.getTime()));
//                    mViewModel.sendDay(selectedDate);
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Xử lý khi tab không được chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn lại
            }
        });

    }

    private String fomatDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    @Override
    public ScheduleCinemaFragmentBinding getViewBinding() {
        return ScheduleCinemaFragmentBinding.inflate(getLayoutInflater());
    }
}
