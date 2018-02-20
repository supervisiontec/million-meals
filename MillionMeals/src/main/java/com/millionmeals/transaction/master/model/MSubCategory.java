package com.millionmeals.transaction.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-02.
 */
@Entity
@Table(name = "m_sub_category", schema = "million_meals", catalog = "")
public class MSubCategory {
    private Integer indexNo;
    private String name;
    private String description;

    @Id
    @Column(name = "index_no")
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
