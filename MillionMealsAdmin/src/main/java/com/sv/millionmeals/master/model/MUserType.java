package com.sv.millionmeals.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-27.
 */
@Entity
@Table(name = "m_user_type", schema = "million_meals", catalog = "")
public class MUserType {
    private int indexNo;
    private String name;

    @Id
    @Column(name = "index_no", nullable = false)
    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MUserType mUserType = (MUserType) o;

        if (indexNo != mUserType.indexNo) return false;
        if (name != null ? !name.equals(mUserType.name) : mUserType.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
