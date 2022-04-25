package com.zsy.xuki.entity;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer sex;

    private String email;

    private String phone;

    private Short role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    public String toString() {
        return "id: (" + id + ") name: (" + name + ") password: (" + password + ") sex: ("
                + sex + ") email: (" + email + ") phone: (" + phone + ") role: (" + role + ")";
    }
}
