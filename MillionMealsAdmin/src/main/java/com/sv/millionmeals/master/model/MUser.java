package com.sv.millionmeals.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-27.
 */
@Entity
@Table(name = "m_user", schema = "million_meals", catalog = "")
public class MUser {
    private int indexNo;
    private Integer branch;
    private String name;
    private String role;

    @Id
    @Column(name = "index_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "branch", nullable = true)
    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MUser mUser = (MUser) o;

        if (indexNo != mUser.indexNo) return false;
        if (branch != null ? !branch.equals(mUser.branch) : mUser.branch != null) return false;
        if (name != null ? !name.equals(mUser.name) : mUser.name != null) return false;
        if (role != null ? !role.equals(mUser.role) : mUser.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo;
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
