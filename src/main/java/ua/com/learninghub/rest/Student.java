package ua.com.learninghub.rest;

import javax.xml.bind.annotation.XmlRootElement;


public class Student {
    int id;
    int idCategory;
    String login;
    String password;
    String email;

    public Student() {
    }

    public Student(int id, int idCategory, String login, String password, String email) {
        this.id = id;
        this.idCategory = idCategory;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
