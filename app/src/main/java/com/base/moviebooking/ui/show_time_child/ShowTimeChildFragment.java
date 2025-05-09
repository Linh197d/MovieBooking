package com.base.moviebooking.ui.show_time_child;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.News;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.VietnamComparator;
import com.base.moviebooking.adapter.ShowTimesAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.LichChieuFragmentBinding;
import com.base.moviebooking.entity.Cinema;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.ShowTime;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.ui.chonghe.ChonGheFragment;
import com.base.moviebooking.ui.main.MainActivity;
import com.base.moviebooking.ui.show_time.ShowTimeViewModel;
import com.base.moviebooking.ui.sign_in.SignInFragment;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowTimeChildFragment extends BaseFragment<LichChieuFragmentBinding> {
    private Context context;
    Movie nMovie;
    private ShowTimeChildViewModel mViewModel;
    private ShowTimeViewModel showTimeViewModel;
    private ShowTimesAdapter showTimesAdapter;
    private List<ShowTime> lichChieuList = new ArrayList<>();
    private  List<Cinema> listCinema = new ArrayList<>();
    Date date;


    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        stopAllVideos();
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);

        showTimeViewModel = new ViewModelProvider(this).get(ShowTimeViewModel.class);
        showTimeViewModel.dataMovie.observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                nMovie = movie;
            }
        });
        mViewModel = new ViewModelProvider(this).get(ShowTimeChildViewModel.class);
        binding.rcvRap.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        showTimesAdapter = new ShowTimesAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoosePhim(Movie phim) {

            }

            @Override
            public void onChooseRap(Theater rap) {

            }

            @Override
            public void onChooseFilmInfo(FilmInfo filmInfo) {

            }

            @Override
            public void onChooseLichChieu(Schedule lichChieu) {
                if (DataLocalManager.getInstance() != null && DataLocalManager.getBooleanValue()) {
                    Log.d("mmm","chonLichchieu đã Login",null);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("schedule", lichChieu);
                    hashMap.put("movie", nMovie);
                    hashMap.put("cinema",binding.spinnerRap.getSelectedItem().toString());
                    stopAllVideos();
                    ((MainActivity)getActivity()).getViewController().addFragment(ChonGheFragment.class,hashMap);

                } else {
                    Log.d("mmm","chonLichchieu chưa Login",null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
                    ((MainActivity)getActivity()).getViewController().replaceFragment(SignInFragment.class,null);

                }
            }

            @Override
            public void onChooseCategory(Category category) {

            }

            @Override
            public void onChooseTime(Schedule schedule) {

            }
        });
        // get Schedule lúc mới sang màn Schedule
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String s = formatter.format(calendar.getTime());
        SimpleDateFormat stringformat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;
        try {
            date = stringformat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("fat","rap"+binding.spinnerRap.getSelectedItemPosition());
//        mViewModel.getListSchedule(1,dateFormat.format(date), 1);//nMovie.getId()
        mViewModel.listSchedule.observe(getViewLifecycleOwner(), new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                if(schedules.size()!=0){
                    showTimesAdapter.refresh(schedules);
//                    showTimesAdapter.addModels(schedules, false);
                    binding.rcvRap.setVisibility(View.VISIBLE);
                    binding.lnNoSchedule.setVisibility(View.GONE);
                }else {
                    binding.rcvRap.setVisibility(View.GONE);
                    binding.lnNoSchedule.setVisibility(View.VISIBLE);
                }

            }
        });

//                showTimesAdapter.addModels(getListModel(), false);

        binding.rcvRap.setAdapter(showTimesAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.rcvRap.addItemDecoration(itemDecoration);
    }

    @Override
    public void initData() {
        if(showTimeViewModel.dataMovie!= null){
            MutableLiveData<Movie> movie = showTimeViewModel.dataMovie;
            List<String> item_Lich = new ArrayList<>();
            item_Lich.add("Chọn ngày");

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(calendar.getTime());
            item_Lich.add(dateString);

            for (int i = 0; i < 7; i++) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                String date2= formatter.format(calendar.getTime());
                item_Lich.add(date2);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, item_Lich);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerNgay.setAdapter(adapter);
            SimpleDateFormat stringformat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            binding.spinnerNgay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String s = binding.spinnerNgay.getSelectedItem().toString();
                    date = null;
                    try {
                        date = stringformat.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(binding.spinnerRap.getSelectedItemPosition()!=0){
                        mViewModel.getListSchedule(listCinema.get(binding.spinnerRap.getSelectedItemPosition()-1).getId(),dateFormat.format(date),nMovie.getId());
                    }
//        binding.birthdayUser.setText(dateFormat.format(date));

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            binding.spinnerRap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(binding.spinnerNgay.getSelectedItemPosition()!=0){
                        mViewModel.getListSchedule(listCinema.get(binding.spinnerRap.getSelectedItemPosition()-1).getId(),dateFormat.format(date),nMovie.getId());
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            dataSpiner(movie.getValue().getId());
        } else {
            // Không có dữ liệu để xử lý
            Log.d("cather","des NO data");
        }
    }

    private void dataSpiner(int movieId) {
        List<String> item_Rap = new ArrayList<>();
        item_Rap.add("Chọn rạp");
        mViewModel.getCinemasByMovieId(movieId);
        mViewModel.data.observe(getViewLifecycleOwner(), new Observer<List<Cinema>>() {
            @Override
            public void onChanged(List<Cinema> list) {
                if(!list.isEmpty()){
                    listCinema.addAll(list);
                    for(int i=0;i< list.size();i++){
                        item_Rap.add(list.get(i).getName())  ;
                    }
                }

            }
        });
        VietnamComparator vietnameseComparator = new VietnamComparator();
        Collections.sort(item_Rap, vietnameseComparator);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, item_Rap);
//
//// Specify the layout to use for the dropdown list
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRap.setAdapter(adapter2);

    }
    private void stopAllVideos() {
        if (getActivity() == null) {
            return; // Đảm bảo rằng getActivity() không null
        }

        PlayerView playerView1 = getActivity().findViewById(R.id.exoplayer);
        if (playerView1 == null || playerView1.getPlayer() == null) {
            return; // Đảm bảo rằng PlayerView và Player đã được khởi tạo
        }

        Player player = playerView1.getPlayer();
        // Kiểm tra trạng thái của Player trước khi dừng
        if (player.getPlaybackState() != Player.STATE_IDLE &&
                (player.getPlayWhenReady() || player.getPlaybackState() == Player.STATE_BUFFERING)) {
            player.stop();
        }
    }


    @NonNull
    @Override
    public LichChieuFragmentBinding getViewBinding() {
        return LichChieuFragmentBinding.inflate(getLayoutInflater());
    }
}

