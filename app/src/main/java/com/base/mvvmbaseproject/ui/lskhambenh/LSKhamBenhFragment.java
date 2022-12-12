package com.base.mvvmbaseproject.ui.lskhambenh;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.base.mvvmbaseproject.DataLocalManager;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.adapter.LSKhamBenhAdapter;
import com.base.mvvmbaseproject.adapter.SearchAdapter;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.base.ListLoadmoreReponse;
import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.base.ListResponseChild;
import com.base.mvvmbaseproject.databinding.FragmentLoginBinding;
import com.base.mvvmbaseproject.databinding.LskhambenhBinding;
import com.base.mvvmbaseproject.entity.LSKhamBenh;
import com.base.mvvmbaseproject.entity.LoginRequest;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.base.mvvmbaseproject.ui.UserProfile.UserFragment;
import com.base.mvvmbaseproject.ui.home.HomeFragment;
import com.base.mvvmbaseproject.ui.login.LoginViewModel;
import com.base.mvvmbaseproject.ui.main.MainActivity;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class LSKhamBenhFragment extends BaseFragment<LskhambenhBinding> {
    private LSkhamBenhViewModel mViewModel;
    private LSKhamBenhAdapter lsKhamBenhAdapter;
    private ArrayList<LSKhamBenh> lsKhamBenhArrayList = new ArrayList<>();
    private ProgressBar loadingPB;
    boolean isLoading = false;
    int totalPage = 1, currentPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.lskhambenh;
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
        lsKhamBenhArrayList = new ArrayList<>();
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LSkhamBenhViewModel.class);
        mViewModel.search(1);

        mViewModel.dataLSKB.observe(getViewLifecycleOwner(), new Observer<ListResponseChild>() {
            @Override
            public void onChanged(ListResponseChild listResponseChild) {
                lsKhamBenhArrayList = new ArrayList<>(listResponseChild.getData());
                if (lsKhamBenhAdapter == null) {
                    lsKhamBenhAdapter = new LSKhamBenhAdapter(getContext(), listResponseChild.getData());
                    binding.rcvLskb.setAdapter(lsKhamBenhAdapter);
                } else {
                    lsKhamBenhAdapter.addData(listResponseChild.getData());
                }
                totalPage = listResponseChild.getTotal_page();
                currentPage = listResponseChild.getCurrent_page();
                isLoading = false;
            }
        });
        initScrollListener();
        lsKhamBenhAdapter = new LSKhamBenhAdapter(getContext(), lsKhamBenhArrayList);
        binding.rcvLskb.setAdapter(lsKhamBenhAdapter);
    }

    private void initScrollListener() {
        binding.rcvLskb.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.rcvLskb.getLayoutManager();
                if (!isLoading) {
                    //Log.d("mmm","findLasr"+linearLayoutManager.findLastCompletelyVisibleItemPosition()+"size"+lsKhamBenhArrayList.size() );
                    //Nếu item cuối cùng của layout = với giá trị cuối của recycleView thì ta gọi hàm LoadMore
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == lsKhamBenhArrayList.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        //Log.d("mmm","t22222222");
        lsKhamBenhArrayList.add(null);
        if (currentPage < totalPage) {
            mViewModel.search(currentPage + 1);
        }
        lsKhamBenhAdapter.notifyItemInserted(lsKhamBenhArrayList.size() - 1);
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
//        binding.rcvLskb.enableLoadmore(canLoadmore);
//        if (isRefresh) {
//            binding.rcvLskb.refresh(data);
//        } else {
//            binding.rcvLskb.addItem(data);
//        }
//        lsKhamBenhAdapter.enableLoadingMore(canLoadmore);
//        if (isRefresh) {
//            lsKhamBenhAdapter.refresh(data);
//        } else {
//           lsKhamBenhAdapter.addModels(data,false);
//        }
    }


    @Override
    public void initData() {
        getActivity().findViewById(R.id.btnTrangChu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(HomeFragment.class, null);
            }
        });
        getActivity().findViewById(R.id.btnHoSo).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewController.addFragment(UserFragment.class, null);
            }
        });
        binding.backLSKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.backFromAddFragment(null);
            }
        });
        binding.swipeRefresh.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                binding.swipeRefresh.setRefreshing(false);
                mViewModel.search(1);
                lsKhamBenhAdapter = null;
            }, 2000);
        });
    }
}
