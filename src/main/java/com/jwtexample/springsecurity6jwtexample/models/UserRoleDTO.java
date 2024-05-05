package com.jwtexample.springsecurity6jwtexample.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_role_tl", schema = "jwt_test_spring_security_6", catalog = "")
public class UserRoleDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USER_ROLE_ID", nullable = false)
    private int userRoleId;
    @Basic
    @Column(name = "USER_ROLE_NAME", nullable = false, length = 255)
    private String userRoleName;
    @Basic
    @Column(name = "USER_ROLE_DESC", nullable = true, length = -1)
    private String userRoleDesc;

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleDesc() {
        return userRoleDesc;
    }

    public void setUserRoleDesc(String userRoleDesc) {
        this.userRoleDesc = userRoleDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleDTO that = (UserRoleDTO) o;
        return userRoleId == that.userRoleId && Objects.equals(userRoleName, that.userRoleName) && Objects.equals(userRoleDesc, that.userRoleDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId, userRoleName, userRoleDesc);
    }
}
