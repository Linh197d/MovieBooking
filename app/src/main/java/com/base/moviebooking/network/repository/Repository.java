package com.base.moviebooking.network.repository;

import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.Amount;
import com.base.moviebooking.entity.CancelTicket;
import com.base.moviebooking.entity.Chair;
import com.base.moviebooking.entity.ChangePass;
import com.base.moviebooking.entity.Cinema;
import com.base.moviebooking.entity.ForgetPass;
import com.base.moviebooking.entity.LoginRequest;
import com.base.moviebooking.entity.LoginResponse;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.News;
import com.base.moviebooking.entity.Product;
import com.base.moviebooking.entity.RegisterRequest;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Seat;
import com.base.moviebooking.entity.ThanhToan;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.entity.UserUpdate;
import com.base.moviebooking.entity.VNPay;
import com.base.moviebooking.network.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }
    //lấy thông tin phim
    public Single<List<Movie>> getMovieData() {
        return apiInterface.getMovie( )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    // đăng nhập
    public Single<LoginResponse> getLoginResponse(LoginRequest loginRequest) {
        return apiInterface.login(loginRequest )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //đăng ký
    public Single<RegisterResponse> getRegisterResponse(RegisterRequest registerRequest) {
        return apiInterface.register(registerRequest )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //lấy thông tin user
    public Single<List<Account>> getInfoUser( ) {
        return apiInterface.infoUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //lấy all chair
    public Single<List<Seat>> getAllChair( ) {
        return apiInterface.getAllChair()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //lấy all Cinema
    public Single<List<Cinema>> getCinemas( ) {
        return apiInterface.getCinemas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //lấy all Schedule
    public Single<List<Schedule>> getschedules(int cinemaId,String day,int movieId ) {
        return apiInterface.getSchedules(cinemaId,day,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //lấy ghế đã đặt
    public Single<List<Chair>> getChaired(int id ) {
        return apiInterface.getChaired(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //lấy cinema by Id
    public Single<List<Cinema>> getCinemaFromID(int id ) {
        return apiInterface.getCinemabyId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //
    //lấy amount
    public Single<List<Amount>> getAmount(int dateType,int dayType,int formatId ) {
        return apiInterface.getAmount(dateType, dayType, formatId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //thanh toán
    public Single<RegisterResponse> getThanhToan(ThanhToan thanhToan) {
        return apiInterface.thanhtoan(thanhToan )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //update user
    public Single<RegisterResponse> updateUser(UserUpdate update) {
        return apiInterface.updateUser(update )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //products
    public Single<List<Product>> getProducts( ) {
        return apiInterface.getProducts( )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //lấy thông tin thanh toán
    public Single<List<ThongTinThanhToan>> getThongtinThanhToan( ) {
        return apiInterface.getThongTinThanhToan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    // thanh toan webview
    public Single<String> thanhToanWebview(VNPay vnPay) {
        return apiInterface.postWebviewTT(vnPay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //get News
    public Single<List<News>> getNews() {
        return apiInterface.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //get News by ID
    public Single<News> getNewsByID(int id) {
        return apiInterface.getNewsByID(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //change pass
    public Single<LoginResponse> changePassword(ChangePass changePass) {
        return apiInterface.changPass(changePass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //cancel ticket
    public Single<LoginResponse> cancelTicket(CancelTicket cancelTicket) {
        return apiInterface.cancelTicket(cancelTicket)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //quen mk
    public Single<LoginResponse> forgotPassword(ForgetPass email) {
        return apiInterface.forgotPass(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
