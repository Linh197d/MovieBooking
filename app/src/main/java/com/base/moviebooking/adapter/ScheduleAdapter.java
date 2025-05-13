package com.base.moviebooking.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.ViewholderScheduleBinding;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.MovieSchedule;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.ui.chonghe.ChonGheFragment;
import com.base.moviebooking.ui.main.MainActivity;
import com.base.moviebooking.ui.schedule.ScheduleCinemaModel;
import com.base.moviebooking.ui.schedule_child.ScheduleChildModel;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleAdapter extends EndlessLoadingRecyclerViewAdapter<ViewholderScheduleBinding> {
    ScheduleChildModel scheduleChildModel;
    TimeAdapter timeAdapter;
    private Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;
    private ScheduleCinemaModel scheduleCinemaModel;
    private LifecycleOwner lifecycleOwner;

    public ScheduleAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView mOnChooseRecyclerView, LifecycleOwner lifecycleOwner, ScheduleCinemaModel scheduleCinemaModel) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseRecyclerView = mOnChooseRecyclerView;
        this.lifecycleOwner = lifecycleOwner;
        this.scheduleCinemaModel = scheduleCinemaModel;
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

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewholderScheduleBinding binding, ViewGroup parent) {
        return new ScheduleViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ScheduleViewHolder scheduleViewHolder = (ScheduleViewHolder) holder;
        scheduleViewHolder.bind(getItem(position, MovieSchedule.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.viewholder_schedule;
    }

    public class ScheduleViewHolder extends NormalViewHolder<MovieSchedule> {
        private ViewholderScheduleBinding binding;

        ScheduleViewHolder(ViewholderScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(MovieSchedule data) {

            // doi anh base64
            String base64Image = data.getImage();
//            Log.d("mmm","base64"+base64Image,null);
            byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            binding.image.setImageBitmap(bitmap);
            binding.tvtAgeLimit.setText("C" + data.getAgeLimit());
            binding.tvtName.setText(data.getName().toString());
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            binding.rate.setText(decimalFormat.format(data.getRate()));
//            scheduleCinemaModel = ViewModelProviders.of(requireParentFragment(), getContext()).get(ScheduleCinemaModel.class);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            binding.listTimes.setLayoutManager(gridLayoutManager);

//            scheduleChildModel.getTimeSchedule(theater.getId(),data.getId(),day);
            timeAdapter = new TimeAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
                @Override
                public void onChoosePhim(Movie movie) {

                }

                @Override
                public void onChooseRap(Theater theater) {

                }

                @Override
                public void onChooseFilmInfo(FilmInfo filmInfo) {

                }

                @Override
                public void onChooseLichChieu(Schedule showTime) {

                }

                @Override
                public void onChooseCategory(Category category) {

                }

                @Override
                public void onChooseTime(Schedule schedule) {
                    Theater theater = scheduleCinemaModel.getDataTheater().getValue();
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("schedule", schedule);
                    Movie movie = new Movie(data.getName(), data.getDescription(), data.getImageDirector(), data.getDirector(), data.getTime(), data.getImage(), data.getId(), data.getAgeLimit(), data.getTrailer(), data.getFormat());
                    hashMap.put("movie", movie);
                    hashMap.put("cinema", theater.getName());
                    if (mContext instanceof MainActivity) {
                        ((MainActivity) mContext).getViewController().addFragment(ChonGheFragment.class, hashMap);
                    }
                }
            });
//            scheduleChildModel.dataSchedule.observe((LifecycleOwner) lifecycleOwner.getLifecycle(), new Observer<List<Schedule>>() {
//                @Override
//                public void onChanged(List<Schedule> listScheduleResponse) {
//                    if (listScheduleResponse.size()!=0){
//                        timeAdapter.addModels(listScheduleResponse, false);
//                        Log.d("fat", "add Model", null);
////                        getActivity().findViewById(R.id.dialog_load).setVisibility(View.GONE);
////                        binding.lnNoMovie.setVisibility(View.GONE);
//                    }
////                    else{
////                        getActivity().findViewById(R.id.dialog_load).setVisibility(View.GONE);
////                        binding.lnNoMovie.setVisibility(View.VISIBLE);
////                    }
//
//                }
//            });
            if (data.getListSchedule().size() != 0) {
                timeAdapter.addModels(data.getListSchedule(), false);
                Log.d("fat", "add Model", null);
            }
            binding.listTimes.setAdapter(timeAdapter);
            timeAdapter.notifyDataSetChanged();
        }
    }
}
