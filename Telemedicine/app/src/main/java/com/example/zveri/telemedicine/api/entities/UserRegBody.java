package com.example.zveri.telemedicine.api.entities;

import java.util.Date;

/**
 * Created by deDwarf on 12/16/2017.
 */

public class UserRegBody {
    private String fullName;
    private Date birthday;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private String base64photo;

    public UserRegBody(String fullName, Date birthday, String email, String phone,
                       String address, String username, String password, String base64photo) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.base64photo = base64photo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBase64photo() {
        return base64photo;
    }

    public void setBase64photo(String base64photo) {
        this.base64photo = base64photo;
    }
}
