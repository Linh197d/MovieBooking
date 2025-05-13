package com.base.moviebooking.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.ItemCommentBinding;
import com.base.moviebooking.entity.Comment;
import com.base.moviebooking.listener.OnChooseComment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentAdapter extends EndlessLoadingRecyclerViewAdapter<ItemCommentBinding> {
    private final Context mContext;
    private OnChooseComment mOnChooseComment;

    public CommentAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseComment mOnChooseComment) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseComment = mOnChooseComment;
    }

    public static String convertDateTimeFormat(String inputDateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        try {
            Date date = inputFormat.parse(inputDateTime);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
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

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemCommentBinding binding, ViewGroup parent) {
        return new CommentViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        commentViewHolder.bind(getItem(position, Comment.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_comment;
    }

    public class CommentViewHolder extends NormalViewHolder<Comment> {
        private final ItemCommentBinding binding;

        CommentViewHolder(ItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Comment data) {
            binding.tvName.setText(data.getFullName());
            String sumRate = String.valueOf(data.getRate());
            String dateComment = convertDateTimeFormat(data.getCreateAt());
            binding.tvDateComment.setText(dateComment);
            binding.tvSumStar.setText(sumRate + "/5");
            binding.tvContent.setText(data.getContent());
            if (data.getAvatar() != null) {
                // doi anh base64
                String base64Image = data.getAvatar();
//            Log.d("mmm","base64"+base64Image,null);
                byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                binding.imgAvatar.setImageBitmap(bitmap);
            } else {
                binding.imgAvatar.setImageResource(R.drawable.user2);
                binding.imgAvatar.setBackgroundResource(R.drawable.oval_btn_blackground_grey);
            }
            binding.lyComment.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnChooseComment.ChooseComment(data);
                    return false;
                }
            });
        }
    }
}
