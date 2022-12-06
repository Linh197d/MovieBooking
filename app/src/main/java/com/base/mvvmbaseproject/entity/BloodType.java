package com.base.mvvmbaseproject.entity;

public class BloodType {
    private String bloodtype;
    private boolean isChecked;

    public BloodType(String bloodtype, boolean isChecked) {
        this.bloodtype = bloodtype;
        this.isChecked = isChecked;
    }

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
