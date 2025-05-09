package com.base.moviebooking.ui.theater;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.TheaterAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.RapphimFragmentBinding;
import com.base.moviebooking.entity.Theater;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class TheaterFragment extends BaseFragment<RapphimFragmentBinding> {
    String TAG="fat";
    private List<Theater> rapList= new ArrayList<>();
    private TheaterAdapter rapAdapter;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        binding.rcvRapphim.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rapAdapter = new TheaterAdapter(getContext(),false,getContext());
        rapAdapter.addModels(getListModel(),false);
        binding.rcvRapphim.setAdapter(rapAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        binding.rcvRapphim.addItemDecoration(itemDecoration);
    }
    private List<Theater> getListModel() {
        rapList.add(new Theater(1,"Mê Linh Plaza","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));
        rapList.add(new Theater(2,"Joy City Point","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","116 Nguyễn Du,Quận 1,Tp.HCM","TPHCM","0987654321"));
        rapList.add(new Theater(3,"Vincom Plaza","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));
        rapList.add(new Theater(4,"Tower Plaza","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));
        rapList.add(new Theater(6,"Thăng Long Plaza","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));
        rapList.add(new Theater(8,"Hoàn Kiếm Plaza","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));
        rapList.add(new Theater(9,"Vincom Thái Bình","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));
        rapList.add(new Theater(10,"Lotte Hà Nội","http://res.cloudinary.com/drlb07jfr/image/upload/v1709864456/ou0mhqz3nps1x4twk4p4.jpg","","Hà Nội","0987654321"));

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


    @NonNull
    @Override
    public RapphimFragmentBinding getViewBinding() {
        return RapphimFragmentBinding.inflate(getLayoutInflater());
    }
}
