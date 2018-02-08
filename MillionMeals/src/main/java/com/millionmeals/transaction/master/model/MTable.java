package com.millionmeals.transaction.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-02.
 */
@Entity
@Table(name = "m_table", schema = "million_meals", catalog = "")
public class MTable {
    private Integer indexNo;
    private Integer mBranch;
    private String name;

    @Id
    @Column(name = "index_no")
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "m_branch")
    public Integer getmBranch() {
        return mBranch;
    }

    public void setmBranch(Integer mBranch) {
        this.mBranch = mBranch;
    }

    @Basic
    @Column(name = "name")
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

        MTable mTable = (MTable) o;

        if (indexNo != null ? !indexNo.equals(mTable.indexNo) : mTable.indexNo != null) return false;
        if (mBranch != null ? !mBranch.equals(mTable.mBranch) : mTable.mBranch != null) return false;
        if (name != null ? !name.equals(mTable.name) : mTable.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo != null ? indexNo.hashCode() : 0;
        result = 31 * result + (mBranch != null ? mBranch.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
