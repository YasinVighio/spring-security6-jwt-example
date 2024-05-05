package com.jwtexample.springsecurity6jwtexample.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_tl", schema = "jwt_test_spring_security_6", catalog = "")
public class UserDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ID", nullable = false)
    private int userId;
    @Basic
    @Column(name = "USERNAME", nullable = false, length = 255)
    private String username;
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String lastName;
    @Basic
    @Column(name = "IS_ACTIVE", nullable = false)
    private byte isActive;
    @ManyToOne
    @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "USER_ROLE_ID", nullable = false)
    private UserRoleDTO userRoleTlByUserRoleId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }


    public UserRoleDTO getUserRoleTlByUserRoleId() {
        return userRoleTlByUserRoleId;
    }

    public void setUserRoleTlByUserRoleId(UserRoleDTO userRoleTlByUserRoleId) {
        this.userRoleTlByUserRoleId = userRoleTlByUserRoleId;
    }
}
