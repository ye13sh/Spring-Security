package com.ye13sh.dto;


public class userDto  {
    private int id;
    private String username;
    private String password;
//    private boolean enabled;
    private String name;
    private String mobileno;
    private String email;

    private String role;

    private Integer unique_id;
    private boolean enabled;


    public userDto() {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobileno = mobileno;
        this.email = email;
        this.role = role;
        this.unique_id = unique_id;
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(Integer unique_id) {
        this.unique_id = unique_id;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

//    public boolean isEnabled() {
//        return enabled;
//    }

//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "userDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", unique_id=" + unique_id +
                ", enabled=" + enabled +
                '}';
    }
}
