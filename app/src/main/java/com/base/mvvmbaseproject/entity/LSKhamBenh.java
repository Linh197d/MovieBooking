package com.base.mvvmbaseproject.entity;

import static com.base.mvvmbaseproject.BuildConfig.BASE_STORAGE;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.base.mvvmbaseproject.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Date;
import java.util.List;

public class LSKhamBenh {
    private int id;
    private String position;
    private String avatar;
    private int created_by;
    private String date;
    private String doctor_name;
    private String service_specialty_name;
    private String service_specialty_icon;
    private String date_medical_histories;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getService_specialty_name() {
        return service_specialty_name;
    }

    public void setService_specialty_name(String service_specialty_name) {
        this.service_specialty_name = service_specialty_name;
    }

    public String getService_specialty_icon() {
        return service_specialty_icon;
    }

    public void setService_specialty_icon(String service_specialty_icon) {
        this.service_specialty_icon = service_specialty_icon;
    }

    public String getDate_medical_histories() {
        return date_medical_histories;
    }

    public void setDate_medical_histories(String date_medical_histories) {
        this.date_medical_histories = date_medical_histories;
    }

    public LSKhamBenh(int id, String position, String avatar, int created_by, String date, String doctor_name, String service_specialty_name, String service_specialty_icon, String date_medical_histories) {
        this.id = id;
        this.position = position;
        this.avatar = avatar;
        this.created_by = created_by;
        this.date = date;
        this.doctor_name = doctor_name;
        this.service_specialty_name = service_specialty_name;
        this.service_specialty_icon = service_specialty_icon;
        this.date_medical_histories = date_medical_histories;
    }
}
