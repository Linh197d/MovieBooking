package com.base.mvvmbaseproject.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData2 {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("facebook_id")
    @Expose
    private Object facebookId;
    @SerializedName("google_id")
    @Expose
    private Object googleId;
    @SerializedName("zalo_id")
    @Expose
    private Object zaloId;
    @SerializedName("apple_id")
    @Expose
    private Object appleId;
    @SerializedName("login_type")
    @Expose
    private Integer loginType;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("check_user")
    @Expose
    private Object checkUser;
    @SerializedName("typeAccount")
    @Expose
    private Integer typeAccount;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Object facebookId) {
        this.facebookId = facebookId;
    }

    public Object getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Object googleId) {
        this.googleId = googleId;
    }

    public Object getZaloId() {
        return zaloId;
    }

    public void setZaloId(Object zaloId) {
        this.zaloId = zaloId;
    }

    public Object getAppleId() {
        return appleId;
    }

    public void setAppleId(Object appleId) {
        this.appleId = appleId;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(Object checkUser) {
        this.checkUser = checkUser;
    }

    public Integer getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(Integer typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
