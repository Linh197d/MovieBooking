package com.base.moviebooking.adapter;

import android.content.Context;
import android.util.Log;
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
import java.util.TimeZone;

public class GiaoDichAdapter extends EndlessLoadingRecyclerViewAdapter<RcvGiaodichBinding> {
    private Context mContext;
    private GiaoDichListener giaoDichListener;

    public GiaoDichAdapter(Context context, boolean enableSelectedMode, GiaoDichListener giaoDichListener) {
        super(context, enableSelectedMode);
        this.mContext = context;
        this.giaoDichListener = giaoDichListener;
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
            if (data.getProducts() != null) {
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
            binding.tien.setText("Tổng tiền: " + data.getValue() + "");
            binding.cinema.setText("Rạp: " + data.getCinema());
            binding.room.setText("Phòng: " + data.getRoom());
            binding.movie.setText("Phim: " + data.getMovie());
            StringBuilder s = new StringBuilder();
            if (data.getChairs() != null) {
                for (int i = 0; i < data.getChairs().size(); i++) {
                    s.append(data.getChairs().get(i)).append(" ");
                }
            }

            binding.chair.setText("Ghế đã đặt: " + s);
            String gio = data.getPremiere().toString().substring(11, 16);
            gio = changeTimeZone(gio);
            String ngay = data.getPremiere().toString().substring(0, 10);
            SimpleDateFormat stringformat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = null;
            try {
                date = stringformat.parse(ngay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            binding.gio.setText("Thời gian: " + gio + " " + dateFormat.format(date));
            if (data.getIs_cancel() == 1) {
                binding.lnHuy.setVisibility(View.VISIBLE);
                binding.lnGDich.setEnabled(false);
            } else {
                if (checkTicket(data.getPremiere())) {//checkTicket(data.getCreated_date())
                    binding.lnGDich.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            Log.d("linhd", data.getCode().toString());
                            giaoDichListener.onChooseGD(data.getCode());
//                    Toast.makeText(getContext(), "Longclick", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    });
                }
            }

            binding.setGiaodich(data);
        }
    }

    private boolean checkTicket(String s) {
        String createdDateString = s;

        // Định dạng chuỗi thời gian theo chuẩn ISO 8601

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {

            // Chuyển đổi chuỗi thành đối tượng Date

            Date createdDate = dateFormat.parse(createdDateString);

            // Lấy thời gian hiện tại

            Date currentDate = new Date();

            // Kiểm tra xem thời gian hiện tại có phải trước thời gian tạo và hơn 1 tiếng hay không.

            if (currentDate.before(createdDate)) {

                long timeDiff = createdDate.getTime() - currentDate.getTime(); // Tính sự khác biệt bằng mili giây

                if (timeDiff > 3600000) {

                    System.out.println("Thời gian hiện tại chưa đến thời gian trong database và còn nhiều hơn 1 tiếng.");
                    return true;
                } else {
                    Toast.makeText(mContext,"Vé không thể huỷ ở thời điểm hiện tại",Toast.LENGTH_SHORT).show();
                    System.out.println("Thời gian hiện tại chưa đến thời gian trong database nhưng ít hơn 1 tiếng.");
                    return false;

                }

            } else {
                Toast.makeText(mContext,"Vé đã hết thời hạn huỷ",Toast.LENGTH_SHORT).show();
                System.out.println("Thời gian hiện tại đã qua thời gian trong database.");
                return false;

            }

        } catch (ParseException e) {

            e.printStackTrace();

        }
        return false;
    }

    public String changeTimeZone(String s) {
        String s1 = s.substring(0, 2);
        String s2 = s.substring(3, 5);
        int i = Integer.parseInt(s1) + 7;
        if (i >= 24) {
            i = i - 24;
        }
        s = i + ":" + s2;
        return s;
    }


}
