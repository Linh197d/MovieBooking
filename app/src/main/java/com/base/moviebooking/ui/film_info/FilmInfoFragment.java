package com.base.moviebooking.ui.film_info;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.FilmInformationAdapter;
import com.base.moviebooking.databinding.DienanhFragmentBinding;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Theater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class FilmInfoFragment extends BaseFragment<DienanhFragmentBinding> {
    private FilmInformationAdapter filmInformationAdapter;
    private List<FilmInfo> dienAnhList = new ArrayList<>();
    @Override
    public void backFromAddFragment() {

    }


    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        binding.rcvDienanh.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        filmInformationAdapter = new FilmInformationAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoosePhim(Movie phim) {

            }

            @Override
            public void onChooseRap(Theater rap) {

            }

            @Override
            public void onChooseFilmInfo(FilmInfo filmInfo) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("filmInfo", filmInfo);
                mViewController.addFragment(FilmInfoChildFragment.class, hashMap);
            }

            @Override
            public void onChooseLichChieu(Schedule lichChieu) {

            }

            @Override
            public void onChooseCategory(Category category) {

            }

            @Override
            public void onChooseTime(Schedule schedule) {

            }
        });
        filmInformationAdapter.addModels(getListModel(), false);
        binding.rcvDienanh.setAdapter(filmInformationAdapter);
    }

    private List<FilmInfo> getListModel() {
        dienAnhList.add(new FilmInfo("Vì sao “The Last of Us” gây sốt toàn cầu?"
                , R.drawable.lastofus, R.string.lastofus1, R.string.lastofus2));
        dienAnhList.add(new FilmInfo("Review \"Avatar 2\": Hơn cả một \"bom tấn\" đỉnh cao!"
                , R.drawable.avatar, R.string.avatar1, R.string.avatar2));
        dienAnhList.add(new FilmInfo("Preview: 'Puss in Boots: The Last Wish': Khi mèo đi hia trưởng thành"
                , R.drawable.pussinboots, R.string.pussinboots, R.string.pussinboots2));
        dienAnhList.add(new FilmInfo("[Review] Spider-Man No Way Home: Chắc Chắn Là Phim Nhện Xuất Sắc Nhất Từ Trước Đến Nay!"
                , R.drawable.spiderman, R.string.spiderman1, R.string.spiderman2));
        dienAnhList.add(new FilmInfo("[Review] Black Panther Wakanda Forever: Báo Đen Tìm Được Người Kế Vị Xứng Đáng? ·"
                , R.drawable.blackpanther, R.string.blackpanther1, R.string.blackpanther2));

        return dienAnhList;
    }

    @Override
    public void onResume() {
        TextView textView = getActivity().findViewById(R.id.tvt_header);
        textView.setText("Điện ảnh");
        getActivity().findViewById(R.id.img_header).setVisibility(View.GONE);
        getActivity().findViewById(R.id.view_header).setVisibility(View.GONE);
        super.onResume();
    }

    @Override
    public void initData() {

    }


    @NonNull
    @Override
    public DienanhFragmentBinding getViewBinding() {
        return DienanhFragmentBinding.inflate(getLayoutInflater());
    }
}
