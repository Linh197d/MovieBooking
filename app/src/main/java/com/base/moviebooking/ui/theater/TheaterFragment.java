package com.base.moviebooking.ui.theater;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.TheaterAdapter;
import com.base.moviebooking.databinding.RapphimFragmentBinding;
import com.base.moviebooking.entity.Theater;

import java.util.ArrayList;
import java.util.List;

public class TheaterFragment extends BaseFragment<RapphimFragmentBinding> {
    String TAG="fat";
    private TheaterViewModel mViewModel;
    private List<Theater> rapList= new ArrayList<>();
    private TheaterAdapter rapAdapter;
    @Override
    protected int getLayoutId() {

        return R.layout.rapphim_fragment;
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
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(TheaterViewModel.class);
        binding.rcvRapphim.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rapAdapter = new TheaterAdapter(getContext(),false,getContext());
        rapAdapter.addModels(getListModel(),false);
        binding.rcvRapphim.setAdapter(rapAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        binding.rcvRapphim.addItemDecoration(itemDecoration);
    }
    private List<Theater> getListModel() {
        rapList.add(new Theater(1,"Mê Linh Plaza",R.drawable.rap5,"Hà Nội","0987654321"));
        rapList.add(new Theater(2,"Joy City Point",R.drawable.rap1,"116 Nguyễn Du,Quận 1,Tp.HCM","0987654321"));
        rapList.add(new Theater(3,"Vincom Plaza",R.drawable.rap_2,"Thái Bình","0987654321"));
        rapList.add(new Theater(4,"Tower Plaza",R.drawable.rap_3,"Nam Định","0987654321"));
        rapList.add(new Theater(6,"Thăng Long Plaza",R.drawable.rap4,"Hà Nội","0987654321"));
        rapList.add(new Theater(8,"Hoàn Kiếm Plaza",R.drawable.rap5,"Hà Nội","0987654321"));
        rapList.add(new Theater(9,"Vincom Thái Bình",R.drawable.rap6,"Hà Nội","0987654321"));
        rapList.add(new Theater(10,"Lotte Hà Nội",R.drawable.rap_3,"Hà Nội","0987654321"));

        return rapList;
    }


    @Override
    public void initData() {

    }



    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.view_header).setVisibility(View.GONE);
        getActivity().findViewById(R.id.img_header).setVisibility(View.GONE);
        TextView textView = getActivity().findViewById(R.id.tvt_header);
        textView.setText("Rạp phim");
        Log.d(TAG,"on Resume Rap");

    }



}
