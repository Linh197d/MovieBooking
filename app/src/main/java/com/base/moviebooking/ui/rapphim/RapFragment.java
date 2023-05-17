package com.base.moviebooking.ui.rapphim;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.HomeAdapter;
import com.base.moviebooking.adapter.RapAdapter;
import com.base.moviebooking.adapter.SlideAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.RapphimFragmentBinding;
import com.base.moviebooking.databinding.SplashFragmentBinding;
import com.base.moviebooking.entity.Phim;
import com.base.moviebooking.entity.Rap;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashViewModel;

import java.util.ArrayList;
import java.util.List;

public class RapFragment extends BaseFragment<RapphimFragmentBinding> {
    String TAG="fat";
    private RapViewModel mViewModel;
    private List<Rap> rapList= new ArrayList<>();
    private RapAdapter rapAdapter;
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
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(RapViewModel.class);
        binding.rcvRapphim.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rapAdapter = new RapAdapter(getContext(),false,getContext());
        rapAdapter.addModels(getListModel(),false);
        binding.rcvRapphim.setAdapter(rapAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        binding.rcvRapphim.addItemDecoration(itemDecoration);
    }
    private List<Rap> getListModel() {
        rapList.add(new Rap("Titanic",R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM","0987654321"));
        rapList.add(new Rap("Titanic",R.drawable.mario,"116 Nguyễn Du,Quận 1,Tp.HCM","0987654321"));
        rapList.add(new Rap("Titanic",R.drawable.avatar,"116 Nguyễn Du,Quận 1,Tp.HCM","0987654321"));
        rapList.add(new Rap("Titanic",R.drawable.galaxy,"116 Nguyễn Du,Quận 1,Tp.HCM","0987654321"));
        rapList.add(new Rap("Titanic",R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM","0987654321"));

        return rapList;
    }
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.rapphim_fragment, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.tvt_header);
//        textView.setText("Rạp Phim");
//        ImageView imageView = (ImageView) rootView.findViewById(R.id.img_header);
//        imageView.setVisibility(View.GONE);
//        View view = (View) rootView.findViewById(R.id.view_header);
//        view.setVisibility(View.GONE);
//        return rootView;
//    }

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


//    void changeTitle(){
//        View header = getActivity().findViewById(R.id.header);
//        TextView tvtHeader= header.findViewById(R.id.tvt_header);
//        tvtHeader.setText();
//    }
}
