package com.base.mvvmbaseproject.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteLSKB {
    private int user_id;
    private String date;
    private String address;
    private String test_result_url;
    private List<Object> test_another_url;
    private List<Object> prescription_url;
    private String judgment;
    private String judgment_note;
    private List<Object> user_test_id;
    private int created_by;
    private String appointment_schedule;
    private String deleted_at;
    private String users_name;
    private String users_phone;
    private String position;
    private int doctor_id;
    private String histories_doctor_name;
    private String medical_examination_form;
    private int appointment_schedule_status;
    private String doctor_name;
    private String date_format_detail;
    private int id;
    private String updated_at;
    private List<ContenTest> contentTest;
    private String date_medical_histories;

    public int getUser_id() {
        return user_id;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getTest_result_url() {
        return test_result_url;
    }

    public List<Object> getTest_another_url() {
        return test_another_url;
    }

    public List<Object> getPrescription_url() {
        return prescription_url;
    }

    public String getJudgment() {
        return judgment;
    }

    public String getJudgment_note() {
        return judgment_note;
    }

    public List<Object> getUser_test_id() {
        return user_test_id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public String getAppointment_schedule() {
        return appointment_schedule;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getUsers_name() {
        return users_name;
    }

    public String getUsers_phone() {
        return users_phone;
    }

    public String getPosition() {
        return position;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getHistories_doctor_name() {
        return histories_doctor_name;
    }

    public String getMedical_examination_form() {
        return medical_examination_form;
    }

    public int getAppointment_schedule_status() {
        return appointment_schedule_status;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDate_format_detail() {
        return date_format_detail;
    }

    public int getId() {
        return id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public List<ContenTest> getContentTest() {
        return contentTest;
    }

    public String getDate_medical_histories() {
        return date_medical_histories;
    }

    public DeleteLSKB(int user_id, String date, String address, String test_result_url, List<Object> test_another_url, List<Object> prescription_url, String judgment, String judgment_note, List<Object> user_test_id, int created_by, String appointment_schedule, String deleted_at, String users_name, String users_phone, String position, int doctor_id, String histories_doctor_name, String medical_examination_form, int appointment_schedule_status, String doctor_name, String date_format_detail, int id, String updated_at, List<ContenTest> contentTest, String date_medical_histories) {
        this.user_id = user_id;
        this.date = date;
        this.address = address;
        this.test_result_url = test_result_url;
        this.test_another_url = test_another_url;
        this.prescription_url = prescription_url;
        this.judgment = judgment;
        this.judgment_note = judgment_note;
        this.user_test_id = user_test_id;
        this.created_by = created_by;
        this.appointment_schedule = appointment_schedule;
        this.deleted_at = deleted_at;
        this.users_name = users_name;
        this.users_phone = users_phone;
        this.position = position;
        this.doctor_id = doctor_id;
        this.histories_doctor_name = histories_doctor_name;
        this.medical_examination_form = medical_examination_form;
        this.appointment_schedule_status = appointment_schedule_status;
        this.doctor_name = doctor_name;
        this.date_format_detail = date_format_detail;
        this.id = id;
        this.updated_at = updated_at;
        this.contentTest = contentTest;
        this.date_medical_histories = date_medical_histories;
    }
}
