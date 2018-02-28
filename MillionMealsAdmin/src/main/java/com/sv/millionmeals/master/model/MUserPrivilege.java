package com.sv.millionmeals.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-27.
 */
@Entity
@Table(name = "m_user_privilege", schema = "million_meals", catalog = "")
public class MUserPrivilege {
    private int indexNo;
    private int mUserType;
    private String funtion;
    private Boolean status;

    @Id
    @Column(name = "index_no", nullable = false)
    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }


    @Basic
    @Column(name = "m_user_type", nullable = false)
    public int getmUserType() {
        return mUserType;
    }

    public void setmUserType(int mUserType) {
        this.mUserType = mUserType;
    }


    @Basic
    @Column(name = "funtion", nullable = true, length = 100)
    public String getFuntion() {
        return funtion;
    }

    public void setFuntion(String funtion) {
        this.funtion = funtion;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "MUserPrivilege{" +
                "indexNo=" + indexNo +
                ", mUserType=" + mUserType +
                ", funtion='" + funtion + '\'' +
                ", status=" + status +
                '}';
    }
}
