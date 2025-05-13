package com.base.moviebooking.ui.comments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.CommentAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ActiveCommentFragmentBinding;
import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.Comment;
import com.base.moviebooking.entity.CommentUpdate;
import com.base.moviebooking.entity.CreateComment;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.listener.OnChooseComment;
import com.base.moviebooking.ui.show_time.ShowTimeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CommentFragment extends BaseFragment<ActiveCommentFragmentBinding> {

    Movie nMovie;
    Account account;
    private CommentModel mViewModel;
    private List<Comment> commentList;
    private List<Comment> userListComment;
    private ShowTimeViewModel showTimeViewModel;
    private CommentAdapter commentAdapter;
    private Dialog dialog;
    private BottomSheetDialog bottomSheetDialog;
    private boolean isDialogShown = false;

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
        return false;
    }

    @Override
    public void initView() {
//        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        mViewModel = new ViewModelProvider(this).get(CommentModel.class);
        showTimeViewModel = new ViewModelProvider(requireParentFragment()).get(ShowTimeViewModel.class);
        showTimeViewModel.getDataMovieComent().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                nMovie = movie;
                //get user Comment
                mViewModel.getUserComment(movie.getId());
                mViewModel.userComment.observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
                    @Override
                    public void onChanged(List<Comment> comments) {
                        if (comments.size() == 0) {
                            binding.lySendComment.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //get user info
                mViewModel.getInfo();
                mViewModel.dataUser.observe(getViewLifecycleOwner(), new Observer<List<Account>>() {
                    @Override
                    public void onChanged(List<Account> accounts) {
                        if (accounts.size() != 0) {
                            account = accounts.get(0);
                            if (accounts.get(0).getAvatar() != null) {
                                // doi anh base64
                                String base64Image = accounts.get(0).getAvatar();
//            Log.d("mmm","base64"+base64Image,null);
                                byte[] imageBytes = Base64.decode(parseBase64(base64Image), Base64.DEFAULT);
                                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                binding.imgAvatar.setImageBitmap(bitmap);
                            } else {
                                binding.imgAvatar.setImageResource(R.drawable.user2);
                                binding.imgAvatar.setBackgroundResource(R.drawable.oval_btn_blackground_grey);
                            }
                        }
                    }
                });
                // Show list comment
                binding.ryComment.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                mViewModel.getListComments(movie.getId());
                commentAdapter = new CommentAdapter(getContext(), false, getContext(), new OnChooseComment() {

                    @Override
                    public void ChooseComment(Comment comment) {
                        if (comment.getUserId() == account.getId()) {
                            showOptionComment(comment);
                        }
                    }
                });
                mViewModel.comments.observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
                    @Override
                    public void onChanged(List<Comment> movieListResponse) {
                        if (movieListResponse.size() != 0) {
                            commentList = movieListResponse;
                            commentAdapter.clear();
                            commentAdapter.addModels(movieListResponse, false);
                            binding.ryComment.setVisibility(View.VISIBLE);
                            binding.lyCommentEmpty.setVisibility(View.GONE);
                        } else {
                            binding.ryComment.setVisibility(View.GONE);
                            binding.lyCommentEmpty.setVisibility(View.VISIBLE);
                        }
                        commentAdapter.notifyDataSetChanged();
                        Log.d("fat", "add Model", null);
                        getActivity().findViewById(R.id.dialog_load).setVisibility(View.GONE);
                    }
                });
                binding.ryComment.setAdapter(commentAdapter);

                // get ticket movie of user
                mViewModel.getTicket(nMovie.getId());
                mViewModel.userTicket.observe(getViewLifecycleOwner(), new Observer<List<ThongTinThanhToan>>() {
                    @Override
                    public void onChanged(List<ThongTinThanhToan> tickets) {
                        if (tickets.size() == 0) {
                            isDialogShown = true;
                        } else {
                            isDialogShown = false;
                        }
                    }
                });

            }
        });


    }

    @Override
    public void initData() {
        binding.tvContentComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDialogShown) {
                    Toast.makeText(getContext(), "Bạn phải mua vé phim này để tiến hành đánh giá", Toast.LENGTH_SHORT).show();
                } else {
                    dialog = new Dialog(requireContext(), R.style.MyAlertDialogTheme2);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_rate);
                    Window window = dialog.getWindow();
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    window.setGravity(Gravity.CENTER);
                    dialog.setCancelable(false);
                    dialog.show();
                    dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                            int rate = (int) ratingBar.getRating();
                            EditText editContent = dialog.findViewById(R.id.edtFeedBack);
                            String content = editContent.getText().toString();
                            if (!content.equals("") && rate != 0) {
                                CreateComment createComment = new CreateComment(nMovie.getId(), content, rate);
                                mViewModel.createComment(createComment);
                                Toast.makeText(getContext(), "Gửi đánh giá thành công", Toast.LENGTH_SHORT).show();
                                binding.lySendComment.setVisibility(View.GONE);
                                dialog.cancel();
                            } else {
                                Toast.makeText(getContext(), "Hãy điền đầy đủ các thông tin", Toast.LENGTH_SHORT).show();
                                binding.lySendComment.setVisibility(View.GONE);
                            }

                        }
                    });
                }
            }
        });
    }

    void showOptionComment(Comment comment) {
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(R.layout.comment_bottom_sheet);
        bottomSheetDialog.show();

        //UpdateComment
        bottomSheetDialog.findViewById(R.id.updateComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
                dialog = new Dialog(requireContext(), R.style.MyAlertDialogTheme2);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_update_comment);
                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setGravity(Gravity.CENTER);
                dialog.setCancelable(false);
                dialog.show();
                RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                EditText editContent = dialog.findViewById(R.id.edtFeedBack);
                ratingBar.setRating(comment.getRate());
                editContent.setText(comment.getContent());
                dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int rate = (int) ratingBar.getRating();
                        String content = editContent.getText().toString();
                        if (!content.equals("") && rate != 0) {
                            CommentUpdate commentUpdate = new CommentUpdate(comment.getId(), comment.getMovieId(), rate, content);
                            mViewModel.updateComment(commentUpdate);
                            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        } else {
                            Toast.makeText(getContext(), "Hãy điền đầy đủ các thông tin", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        //Delete Comment
        bottomSheetDialog.findViewById(R.id.deleteComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
                dialog = new Dialog(requireContext(), R.style.MyAlertDialogTheme2);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_comment);
                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setGravity(Gravity.CENTER);
                dialog.setCancelable(false);
                dialog.show();
                dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.acceptDelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewModel.deleteComment(comment.getId(), comment.getMovieId());
                        Toast.makeText(getContext(), "Xóa đánh giá thành công", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        binding.lySendComment.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public ActiveCommentFragmentBinding getViewBinding() {
        return ActiveCommentFragmentBinding.inflate(getLayoutInflater());
    }
}
