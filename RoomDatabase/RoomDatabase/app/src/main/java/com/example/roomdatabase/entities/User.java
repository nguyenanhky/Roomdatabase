package com.example.roomdatabase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "frist_name")
    private String firstname;

    @ColumnInfo(name = "add_ress")
    private String address;

    // ham tao

    public User() {
    }

    public User(String firstname, String address) {
        this.firstname = firstname;
        this.address = address;
    }

    public User(int id, String firstname, String address) {
        this.id = id;
        this.firstname = firstname;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
/*
- theo mac dinh thi room se lay ten lop de lam ten bang co so du lieu
- theo mac dinh thi room se lay ten thuoc tinh la ten cot luon
 */