package com.base.moviebooking.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class PriceSeat implements Serializable {
    private String gheThuong;
    private int slgheThuong;
    private int slgheVip;
    private String gheVip;
    private String tienGheThuong;
    private String tienGheVip;
    private String slNuoc;
    private String slBong;
    private String slCombo;
    private String tienNuoc;
    private String tienBong;
    private String tienCombo;
    private String tongTien;
    private ArrayList<Integer> listIdGhe;

    public PriceSeat() {
    }

    public PriceSeat(String gheThuong, String gheVip, String tienGheThuong, String tienGheVip, String slNuoc, String slBong, String slCombo, String tienNuoc, String tienBong, String tienCombo, String tongTien, int slthuong, int slvip, ArrayList<Integer> listGhe) {
        this.gheThuong = gheThuong;
        this.gheVip = gheVip;
        this.tienGheThuong = tienGheThuong;
        this.tienGheVip = tienGheVip;
        this.slNuoc = slNuoc;
        this.slBong = slBong;
        this.slCombo = slCombo;
        this.tienNuoc = tienNuoc;
        this.tienBong = tienBong;
        this.tienCombo = tienCombo;
        this.tongTien = tongTien;
        this.slgheThuong = slthuong;
        this.slgheVip = slvip;
        this.listIdGhe = listGhe;
    }

    public ArrayList<Integer> getListIdGhe() {
        return listIdGhe;
    }

    public void setListIdGhe(ArrayList<Integer> listGhe) {
        this.listIdGhe = listGhe;
    }

    public int getSlgheThuong() {
        return slgheThuong;
    }

    public void setSlgheThuong(int slgheThuong) {
        this.slgheThuong = slgheThuong;
    }

    public int getSlgheVip() {
        return slgheVip;
    }

    public void setSlgheVip(int slgheVip) {
        this.slgheVip = slgheVip;
    }

    public String getGheThuong() {
        return gheThuong;
    }

    public void setGheThuong(String gheThuong) {
        this.gheThuong = gheThuong;
    }

    public String getGheVip() {
        return gheVip;
    }

    public void setGheVip(String gheVip) {
        this.gheVip = gheVip;
    }

    public String getTienGheThuong() {
        return tienGheThuong;
    }

    public void setTienGheThuong(String tienGheThuong) {
        this.tienGheThuong = tienGheThuong;
    }

    public String getTienGheVip() {
        return tienGheVip;
    }

    public void setTienGheVip(String tienGheVip) {
        this.tienGheVip = tienGheVip;
    }

    public String getSlNuoc() {
        return slNuoc;
    }

    public void setSlNuoc(String slNuoc) {
        this.slNuoc = slNuoc;
    }

    public String getSlBong() {
        return slBong;
    }

    public void setSlBong(String slBong) {
        this.slBong = slBong;
    }

    public String getSlCombo() {
        return slCombo;
    }

    public void setSlCombo(String slCombo) {
        this.slCombo = slCombo;
    }

    public String getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(String tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public String getTienBong() {
        return tienBong;
    }

    public void setTienBong(String tienBong) {
        this.tienBong = tienBong;
    }

    public String getTienCombo() {
        return tienCombo;
    }

    public void setTienCombo(String tienCombo) {
        this.tienCombo = tienCombo;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
