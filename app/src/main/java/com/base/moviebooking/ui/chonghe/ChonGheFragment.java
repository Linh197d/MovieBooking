package com.base.moviebooking.ui.chonghe;

import android.os.Handler;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.DienAnhAdapter;
import com.base.moviebooking.adapter.SeatAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ChongheFragmentBinding;
import com.base.moviebooking.entity.DienAnh;
import com.base.moviebooking.entity.LichChieu;
import com.base.moviebooking.entity.Phim;
import com.base.moviebooking.entity.Rap;
import com.base.moviebooking.entity.Seat;

import java.util.ArrayList;
import java.util.List;


public class ChonGheFragment extends BaseFragment<ChongheFragmentBinding> {

    private ChonGheViewModel mViewModel;
    private SeatAdapter seatAdapter;
    private List<Seat> seatList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.chonghe_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(ChonGheViewModel.class);
        binding.rcvSeat.setLayoutManager(new GridLayoutManager(getContext(), 5));
        seatAdapter = new SeatAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoosePhim(Phim phim) {

            }

            @Override
            public void onChooseRap(Rap rap) {

            }

            @Override
            public void onChooseLichChieu(LichChieu lichChieu) {

            }
        });
        seatAdapter.addModels(getListModel(),false);
        binding.rcvSeat.setAdapter(seatAdapter);
    }
    private List<Seat> getListModel() {
        seatList.add(new Seat("A1",false));
        seatList.add(new Seat("A2",false));
        seatList.add(new Seat("A3",false));
        seatList.add(new Seat("A4",false));
        seatList.add(new Seat("A5",false));
        seatList.add(new Seat("B1",false));
        seatList.add(new Seat("B2",false));
        seatList.add(new Seat("B3",false));
        seatList.add(new Seat("B4",false));
        seatList.add(new Seat("B5",false));
        seatList.add(new Seat("C1",false));
        seatList.add(new Seat("C2",false));
        seatList.add(new Seat("C3",false));
        seatList.add(new Seat("C4",false));
        seatList.add(new Seat("C5",false));
        seatList.add(new Seat("D1",false));
        seatList.add(new Seat("D2",false));
        seatList.add(new Seat("D3",false));

        return seatList;
    }

    @Override
    public void initData() {

    }


}
