package com.ye13sh.model;


import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @SequenceGenerator(name="authoritysequencegenerator", sequenceName="authority_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="authoritysequencegenerator")
    @Column(name = "id")
    private int id;
    @Column(name = "username",unique = true,nullable = false)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile")
    private String mobileno;
    @Column(name = "role")
    private String role;
    @Column(name="unique_id")
    private Integer unique_id;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;


    public Authority() {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mobileno = mobileno;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
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

}
