package com.base.moviebooking.ui.comments;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.Comment;
import com.base.moviebooking.entity.CommentUpdate;
import com.base.moviebooking.entity.CreateComment;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class CommentModel extends ViewModel {
    private final Repository repository;
    MutableLiveData<List<Comment>> comments = new MutableLiveData<>();
    MutableLiveData<List<Account>> dataUser = new MutableLiveData<>();
    MutableLiveData<List<Comment>> userComment = new MutableLiveData<>();
    MutableLiveData<List<ThongTinThanhToan>> userTicket = new MutableLiveData<>();

    @Inject
    public CommentModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Account>> getDataUser() {
        return dataUser;
    }

    public MutableLiveData<List<Comment>> getDataComment() {
        return comments;
    }

    public MutableLiveData<List<Comment>> getDataUserComment() {
        return userComment;
    }

    public MutableLiveData<List<ThongTinThanhToan>> getDataUserTicket() {
        return userTicket;
    }

    public void getListComments(int movieId) {
        repository.getListComments(movieId)
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Comment> list) {
                        comments.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void getUserComment(int movieId) {
        repository.getMovieCommentOfUser(movieId)
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Comment> list) {
                        userComment.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void getInfo() {
        repository.getInfoUser()
                .subscribe(new SingleObserver<List<Account>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Account> account) {
                        dataUser.postValue(account);
                        Log.d("fat", "success get Data User");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void createComment(CreateComment createComment) {
        repository.createComment(createComment)
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Comment> list) {
                        comments.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void updateComment(CommentUpdate commentUpdate) {
        repository.updateComment(commentUpdate)
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Comment> list) {
                        comments.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    //delete Comment
    public void deleteComment(int commentId, int movieId) {
        repository.deleteComment(commentId, movieId)
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Comment> list) {
                        comments.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    //get user Ticket
    public void getTicket(int movieId) {
        repository.getMyTicketOfMovie(movieId)
                .subscribe(new SingleObserver<List<ThongTinThanhToan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<ThongTinThanhToan> list) {
                        userTicket.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }
}
