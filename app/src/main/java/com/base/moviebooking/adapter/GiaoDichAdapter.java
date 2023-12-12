package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvGiaodichBinding;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.listener.GiaoDichListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GiaoDichAdapter extends EndlessLoadingRecyclerViewAdapter<RcvGiaodichBinding> {
    private Context mContext;
    private GiaoDichListener giaoDichListener;

    public GiaoDichAdapter(Context context, boolean enableSelectedMode,GiaoDichListener giaoDichListener) {
        super(context, enableSelectedMode);
        this.mContext = context;
        this.giaoDichListener=giaoDichListener;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvGiaodichBinding binding, ViewGroup parent) {
        return new GiaoDichViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        GiaoDichViewHolder searchViewHolder = (GiaoDichViewHolder) holder;
        searchViewHolder.bind(getItem(position, ThongTinThanhToan.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_giaodich;
    }


    public class GiaoDichViewHolder extends NormalViewHolder<ThongTinThanhToan> {
        private RcvGiaodichBinding binding;

        GiaoDichViewHolder(RcvGiaodichBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(ThongTinThanhToan data) {
            if(data.getProducts()!=null) {
                for (int i = 0; i < data.getProducts().size(); i++) {
                    if (data.getProducts().get(i).getId() == 2) {
                        binding.bongngo.setVisibility(View.VISIBLE);
                        binding.bongngo.setText(data.getProducts().get(i).getQuantity() + "x Bỏng ngô");
                    } else if (data.getProducts().get(i).getId() == 3) {
                        binding.nuoc.setVisibility(View.VISIBLE);
                        binding.nuoc.setText(data.getProducts().get(i).getQuantity() + "x Nước");

                    } else if (data.getProducts().get(i).getId() == 4) {
                        binding.combo.setVisibility(View.VISIBLE);
                        binding.combo.setText(data.getProducts().get(i).getQuantity() + "x Combo");

                    }
                }
            }
            binding.tien.setText("Tổng tiền: "+data.getValue()+"");
            binding.cinema.setText("Rạp: "+data.getCinema());
            binding.room.setText("Phòng: "+data.getRoom());
            binding.movie.setText("Phim: "+data.getMovie());
            StringBuilder s = new StringBuilder();
            if(data.getChairs()!=null){
                for(int i =0;i<data.getChairs().size();i++){
                    s.append(data.getChairs().get(i)).append(" ");
                }
            }

            binding.chair.setText("Ghế đã đặt: "+s);
            String gio = data.getPremiere().toString().substring(11,16);
            gio = changeTimeZone(gio);
            String ngay = data.getPremiere().toString().substring(0,10);
            SimpleDateFormat stringformat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = null;
            try {
                date = stringformat.parse(ngay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            binding.gio.setText("Thời gian: "+gio +" "+dateFormat.format(date));
            binding.lnGDich.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    giaoDichListener.onChooseGD();
                    Toast.makeText(getContext(),"Choose GD",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            binding.lnGDich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    giaoDichListener.onChooseGD();
                    Toast.makeText(getContext(),"Choose GD",Toast.LENGTH_SHORT).show();
                }
            });

            binding.setGiaodich(data);
        }
    }
    public String changeTimeZone(String s){
        String s1 = s.substring(0,2);
        String s2 = s.substring(3,5);
        int i = Integer.parseInt(s1)+7;
        if(i>=24){
            i = i-24;
        }
        s= i+":"+s2;
        return s;
    }


}
