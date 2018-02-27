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
    private String name;
    private String code;
    private String type;
    private String image;

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


    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
