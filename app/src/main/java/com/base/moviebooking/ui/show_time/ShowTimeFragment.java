package com.base.moviebooking.ui.show_time;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.ViewPagerAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.LichPhimBinding;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.ui.home.HomeFragment;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowTimeFragment extends BaseFragment<LichPhimBinding> {
    public final String TAG = "fat";
    Movie movie;
    private ShowTimeViewModel mViewModel;
    private ViewPagerAdapter viewPagerAdapter;

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

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        stopAllVideos();
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        mViewController.replaceFragment(HomeFragment.class, null);
        return false;
    }

    @Override
    public void initView() {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        mViewModel = new ViewModelProvider(this).get(ShowTimeViewModel.class);
        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewpagerLichphim.setAdapter(viewPagerAdapter);
        binding.viewpagerLichphim.setNestedScrollingEnabled(true);
        binding.viewpagerLichphim.setUserInputEnabled(true);
        new TabLayoutMediator(binding.tabLayout, binding.viewpagerLichphim, new TabLayoutMediator.TabConfigurationStrategy() {

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Lịch chiếu");
                        break;
                    case 1:
                        tab.setText("Thông tin");
                        break;
                    case 2:
                        tab.setText("Đánh giá");
                }
            }
        }).attach();
        //toolbar
        activity.setSupportActionBar(binding.toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //tablayout animation
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.antman);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(@Nullable Palette palette) {
//                int myColor = palette.getVibrantColor(getResources().getColor(R.color.colorStrokeBlue));
//                int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.colorBlack));
//                binding.collapsingLayout.setContentScrimColor(myColor);
//                binding.collapsingLayout.setStatusBarScrimColor(myDarkColor);
//
//            }
//        });

        setDataView();
    }

    @Override
    public void initData() {
        eventClick();
    }

    private void eventClick() {
        // set event click back
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAllVideos();
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.replaceFragment(HomeFragment.class, null);

            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Thay đổi màu của thanh gạch chân khi tab được chọn
                binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Xử lý khi tab không được chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn lại
            }
        });

    }

    private void stopAllVideos() {
        if (getActivity() == null) {
            return; // Đảm bảo rằng getActivity() không null
        }

        PlayerView playerView1 = getActivity().findViewById(R.id.exoplayer);
        if (playerView1 == null || playerView1.getPlayer() == null) {
            return; // Đảm bảo rằng PlayerView và Player đã được khởi tạo
        }

        Player player = playerView1.getPlayer();
        // Kiểm tra trạng thái của Player trước khi dừng
        if (player.getPlaybackState() != Player.STATE_IDLE &&
                (player.getPlayWhenReady() || player.getPlaybackState() == Player.STATE_BUFFERING)) {
            player.stop();
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume Showtime");
        setDataView();
        super.onResume();
    }

    private void setDataView() {
        Bundle bundle = getArguments();

        if (bundle != null && !bundle.isEmpty()) {
            movie = (Movie) bundle.getSerializable("movie");
            // Xử lý dữ liệu trong bundle
            Log.d("linhd", "movie"+movie.getId());

            mViewModel.sendData(movie);
            mViewModel.sendDataMovieComment(movie);


            if (movie.getImage() != null) {
                // doi anh base64
                String base64Image = movie.getImage();
//            Log.d("mmm","base64"+base64Image,null);
                byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                binding.imgMovie.setImageBitmap(bitmap);
            } else {
                binding.imgMovie.setImageResource(R.drawable.lastofus);
            }


//            switch (movie.getId()){
//                case 1:
//                    binding.imgMovie.setImageResource(R.drawable.lastofus);
//                    break;
//                case 12:
//                    binding.imgMovie.setImageResource(R.drawable.avatar);
//                    break;
//                case 13:
//                    binding.imgMovie.setImageResource(R.drawable.pussinboots);
//                    break;
//                case 19:
//                    binding.imgMovie.setImageResource(R.drawable.blackpanther);
//                    break;
//                case 20:
//                    binding.imgMovie.setImageResource(R.drawable.teenwolf);
//                    break;
//                case 22:
//                    binding.imgMovie.setImageResource(R.drawable.megan);
//                    break;
//                case 23:
//                    binding.imgMovie.setImageResource(R.drawable.spiderman);
//                    break;
//                case 24:
//                    binding.imgMovie.setImageResource(R.drawable.spiritedaway);
//                    break;
//
//            }

            binding.collapsingLayout.setTitle(movie.getName());
            binding.movieTime.setText(String.valueOf(movie.getTime()));
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            binding.movieStar.setText(decimalFormat.format(movie.getRate()));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lastofus);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@Nullable Palette palette) {
                    int myColor = palette.getVibrantColor(getResources().getColor(R.color.colorStrokeBlue));
                    int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.colorBlack));
                    binding.collapsingLayout.setContentScrimColor(myColor);
                    binding.collapsingLayout.setStatusBarScrimColor(myDarkColor);
                }
            });
        } else {
            // Không có dữ liệu để xử lý
            Log.d("cather", "des NO data");
        }
    }

    @NonNull
    @Override
    public LichPhimBinding getViewBinding() {
        return LichPhimBinding.inflate(getLayoutInflater());
    }
}