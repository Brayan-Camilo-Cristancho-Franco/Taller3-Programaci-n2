package com.example.src;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Users {

    @CsvBindByName
    private String mail;

    @CsvBindByName
    private String password;

    public Users (String mail, String password) {

        this.mail = mail;
        this.password = password;

        

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return password == users.password &&
                Objects.equals(mail, users.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password);
    }
}
