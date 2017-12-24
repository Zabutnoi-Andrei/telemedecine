package com.example.zveri.telemedicine.api.entities;

/**
 * Created by deDwarf on 12/16/2017.
 */

public class UserAuthBody {
    private String email;
    private String password;

    public UserAuthBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
