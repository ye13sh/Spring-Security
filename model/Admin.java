package com.ye13sh.model;

import javax.persistence.*;
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @SequenceGenerator(name="adminsequencegenerator", sequenceName="admin_id_seq",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="adminsequencegenerator")
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name ="name", nullable = false)
    private String name;
    @Column(name ="mobileno", nullable = false)
    private String mobileno;
    @Column(name ="email", nullable = false)
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "unique_id",updatable = false)
    private Integer unique_id;

    public Admin() {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.mobileno = mobileno;
        this.email = email;
        this.role = role;
        this.unique_id = unique_id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer user_id) {
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
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", unique_id=" + unique_id +
                '}';
    }
}
