package com.base.mvvmbaseproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.OnChooseBloodType;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.EndlessLoadingRecyclerViewAdapter;
import com.base.mvvmbaseproject.databinding.DataLskbBinding;
import com.base.mvvmbaseproject.databinding.LskhambenhBinding;
import com.base.mvvmbaseproject.entity.BloodType;
import com.base.mvvmbaseproject.entity.LSKhamBenh;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class LSKhamBenhAdapter extends RecyclerView.Adapter<LSKhamBenhAdapter.ViewHolder> {
    private Context mContext;
    private List<LSKhamBenh> mLsKhamBenh;


    public LSKhamBenhAdapter(Context mContext, List<LSKhamBenh> mLsKhamBenh) {
        this.mContext = mContext;
        this.mLsKhamBenh = mLsKhamBenh;
    }

    public void addData(List<LSKhamBenh> lsKhamBenh) {
        int OldSize = mLsKhamBenh.size();
        mLsKhamBenh.addAll(lsKhamBenh);
        int NewSize = mLsKhamBenh.size();
        notifyItemRangeInserted(OldSize, NewSize - OldSize);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDoctorName, mServiceName, mDateHistory, mPosition;
        private ImageView mAvatar;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mDoctorName = (itemView).findViewById(R.id.tvt_NameDoctor);
            mPosition = itemView.findViewById(R.id.position);
            mDateHistory = itemView.findViewById(R.id.date_histories);
            mAvatar = itemView.findViewById(R.id.avatar_doctor);
            mServiceName = itemView.findViewById(R.id.service_name);
            progressBar = itemView.findViewById(R.id.idPBLoading);
        }
    }

    @NonNull
    @Override
    public LSKhamBenhAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(LayoutInflater.from(inflater.getContext()).inflate(R.layout.data_lskb, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LSKhamBenhAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (mLsKhamBenh.get(position) == null) {
            holder.progressBar.setVisibility(View.VISIBLE);
            return;
        }
        holder.progressBar.setVisibility(View.GONE);
        LSKhamBenh lsKhamBenh = mLsKhamBenh.get(position);
        holder.mServiceName.setText(lsKhamBenh.getService_specialty_name());
        holder.mDoctorName.setText(lsKhamBenh.getDoctor_name());
        holder.mDateHistory.setText(lsKhamBenh.getDate_medical_histories());
        holder.mPosition.setText(lsKhamBenh.getPosition());
        Glide.with(mContext)
                .load("http://hsba-v2.beetechdev.vn:1680/storage/" + lsKhamBenh.getAvatar())
                .into(holder.mAvatar);
    }

    @Override
    public int getItemCount() {
        return mLsKhamBenh.size();
    }

}