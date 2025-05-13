package com.base.moviebooking.network;


import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.Actor;
import com.base.moviebooking.entity.Amount;
import com.base.moviebooking.entity.CancelTicket;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.Chair;
import com.base.moviebooking.entity.ChangePass;
import com.base.moviebooking.entity.Cinema;
import com.base.moviebooking.entity.Comment;
import com.base.moviebooking.entity.CommentUpdate;
import com.base.moviebooking.entity.CreateComment;
import com.base.moviebooking.entity.ForgetPass;
import com.base.moviebooking.entity.LoginRequest;
import com.base.moviebooking.entity.LoginResponse;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.MovieSchedule;
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

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //lấy thông tin phim
    @GET("movies")
    Single<List<Movie>> getMovie();

    //lấy thông tin phim qua tên
    @GET("movies/listMovie/{nameMovie}")
    Single<List<Movie>> getMovieByName(@Path("nameMovie") String nameMovie);

    //lấy thông tin phim theo category
    @GET("movies/list/{categoryId}")
    Single<List<Movie>> getMovieByCategoryId(@Path("categoryId") int categoryId);

    // đăng nhập
    @POST("auth/login")
    Single<LoginResponse> login(@Body LoginRequest loginRequest);

    // đăng ký
    @POST("auth/register")
    Single<RegisterResponse> register(@Body RegisterRequest registerRequest);

    //get thông tin user
    @GET("auth/information")
    Single<List<Account>> infoUser();

    // get listCategory
    @GET("/movies/categories")
    Single<List<Category>> getListCategory();

    // get listCategory by movie ID
    @GET("/movies/{movieId}/categories")
    Single<List<Category>> getListCategoryByMovieId(@Path("movieId") int movieId);

    // get listActor by movie ID
    @GET("/movies/{movieId}/actors")
    Single<List<Actor>> getListActorsByMovieId(@Path("movieId") int movieId);

    //get cinema
    @GET("cinemas/")
    Single<Theater> getTheater();

    // get Chair
    @GET("schedules/allChairs")
    Single<List<Seat>> getAllChair();

    //get all cinema
    @GET("cinemas")
    Single<List<Theater>> getCinemas();

    //get all cinema has movie schedule
    @GET("cinemas/hasSchedule/{movieId}")
    Single<List<Cinema>> getCinemasByMovieId(@Path("movieId") int movieId);

    //get schedule
    @GET("schedules")
    Single<List<Schedule>> getSchedules(@Query("cinemaId") int cinemaId, @Query("day") String day, @Query("movieId") int movieId);

    //get chair đã đặt bby Id schedule
    @GET("schedules/chairsByScheduleId/{id}")
    Single<List<Chair>> getChaired(@Path("id") int id);

    //get cinema by id
    @GET("cinemas/{id}")
    Single<List<Cinema>> getCinemabyId(@Path("id") int id);

    //get product
    @GET("products")
    Single<List<Product>> getProducts();


    @GET("schedules/amount")
    Single<List<Amount>> getAmount(@Query("date_type") int dateType, @Query("time_type") int timeType, @Query("format_id") int formatId);

    // thanh toan

    @POST("schedules/bookingChairsCheckout")
    Single<RegisterResponse> thanhtoan(@Body ThanhToan thanhToan);

    //update user
    @PUT("auth/information")
    Single<RegisterResponse> updateUser(@Body UserUpdate user);

    //getItemVe
    @GET("auth/myTickets?handle=true")
    Single<List<ThongTinThanhToan>> getThongTinThanhToan();

    //getTicket of user with movie
    @GET("auth//getMyTicketOfMovie?handle=true")
    Single<List<ThongTinThanhToan>> getMyTicketOfMovie(@Query("movieId") int movieId);

    @POST("checkout/create_payment_url")
    Single<String> postWebviewTT(@Body VNPay vnPay);

    @POST("auth/changePassword")
    Single<LoginResponse> changPass(@Body ChangePass changePass);
    //confirm pass
//    @POST("auth/confirmPassword")
//    Single<List<Account>> infoUser();

    //get News
    @GET("/news?type=NEWS")
    Single<List<News>> getNews();

    //create News
    @POST("news")
    Single<List<News>> postNews();


    @POST("schedules/cancelTicket")
    Single<LoginResponse> cancelTicket(@Body CancelTicket code);

    @POST("auth/forgotPassword")
    Single<LoginResponse> forgotPass(@Body ForgetPass forgetPass);


    //get News ny ID
    @GET("news/{id}")
    Single<News> getNewsByID(@Path("id") int id);

    // get Movie has Schedule
    @GET("movies/getMovies")
    Single<List<MovieSchedule>> getMovieHasSchedule(@Query("cinemaId") int cinemaId, @Query("day") String day);

    // get time has Schedule
    @GET("schedules/getScheduleOfMovie")
    Single<List<Schedule>> getScheduleOfCinema(@Query("cinemaId") int cinemaId, @Query("movieId") int movieId, @Query("day") String day);

    // get list comments
    @GET("comment/list/{movieId}")
    Single<List<Comment>> getListComments(@Path("movieId") int movieId);

    // get comment movie of user
    @GET("comment/userComment/{movieId}")
    Single<List<Comment>> getCommentMovieOfUser(@Path("movieId") int movieId);

    // create comment movie of user
    @POST("comment/")
    Single<List<Comment>> createComment(@Body CreateComment createComment);

    // update comment movie of user
    @PUT("comment/")
    Single<List<Comment>> updateComment(@Body CommentUpdate updateComment);

    // delete comment movie of user
    @DELETE("comment/deleteComment")
    Single<List<Comment>> deleteComment(@Query("commentId") int commentId, @Query("movieId") int movieId);
}
