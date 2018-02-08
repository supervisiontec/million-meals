package com.millionmeals.transaction.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-02.
 */
@Entity
@Table(name = "m_item", schema = "million_meals", catalog = "")
public class MItem {
    private Integer indexNo;
    private Integer mCategory;
    private MSubCategory mSubCategory;
    private Integer mMainCategory;
    private Integer mBranch;
    private String name;
    private String type;

    @Id
    @Column(name = "index_no")
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "m_category")
    public Integer getmCategory() {
        return mCategory;
    }

    public void setmCategory(Integer mCategory) {
        this.mCategory = mCategory;
    }

    @ManyToOne
    @JoinColumn(name = "m_sub_category",referencedColumnName = "index_no")
    public MSubCategory getmSubCategory() {
        return mSubCategory;
    }

    public void setmSubCategory(MSubCategory mSubCategory) {
        this.mSubCategory = mSubCategory;
    }

    @Basic
    @Column(name = "m_main_category")
    public Integer getmMainCategory() {
        return mMainCategory;
    }

    public void setmMainCategory(Integer mMainCategory) {
        this.mMainCategory = mMainCategory;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        MItem mItem = (MItem) o;

        if (indexNo != null ? !indexNo.equals(mItem.indexNo) : mItem.indexNo != null) return false;
        if (mCategory != null ? !mCategory.equals(mItem.mCategory) : mItem.mCategory != null) return false;
        if (mSubCategory != null ? !mSubCategory.equals(mItem.mSubCategory) : mItem.mSubCategory != null) return false;
        if (mMainCategory != null ? !mMainCategory.equals(mItem.mMainCategory) : mItem.mMainCategory != null)
            return false;
        if (mBranch != null ? !mBranch.equals(mItem.mBranch) : mItem.mBranch != null) return false;
        if (type != null ? !type.equals(mItem.type) : mItem.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo != null ? indexNo.hashCode() : 0;
        result = 31 * result + (mCategory != null ? mCategory.hashCode() : 0);
        result = 31 * result + (mSubCategory != null ? mSubCategory.hashCode() : 0);
        result = 31 * result + (mMainCategory != null ? mMainCategory.hashCode() : 0);
        result = 31 * result + (mBranch != null ? mBranch.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
