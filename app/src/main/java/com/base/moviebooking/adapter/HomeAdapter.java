package com.base.moviebooking.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvPhimHomeBinding;
import com.base.moviebooking.entity.Movie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeAdapter extends EndlessLoadingRecyclerViewAdapter<RcvPhimHomeBinding> {
    private Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;

    public HomeAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = context;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvPhimHomeBinding binding, ViewGroup parent) {
        return new PhimViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        PhimViewHolder searchViewHolder = (PhimViewHolder) holder;
        searchViewHolder.bind(getItem(position, Movie.class));
//        searchViewHolder.binding.image.setImageResource(R.drawable.antman);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_phim_home;
    }


    public class PhimViewHolder extends NormalViewHolder<Movie> {
        private RcvPhimHomeBinding binding;

        PhimViewHolder(RcvPhimHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Movie data) {


            // doi anh base64
            String base64Image = data.getImage();
//            Log.d("mmm","base64"+base64Image,null);
            byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            binding.image.setImageBitmap(bitmap);
            binding.tvtAgeLimit.setText("C" + data.getAgeLimit());
            binding.setMovie(data);
            binding.lnPhim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mOnChooseRecyclerView.onChoosePhim(data);
                }
            });


        }
    }

    public static String parseBase64(String base64) {

        try {
            Pattern pattern = Pattern.compile("((?<=base64,).*\\s*)", Pattern.DOTALL | Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(base64);
            if (matcher.find()) {
                return matcher.group().toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }
}
