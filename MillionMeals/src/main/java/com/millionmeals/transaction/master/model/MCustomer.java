package com.millionmeals.transaction.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-02.
 */
@Entity
@Table(name = "m_customer", schema = "million_meals", catalog = "")
public class MCustomer {
    private Integer indexNo;
    private String title;
    private String name;
    private String mobile;
    private String address1;
    private String address2;
    private String city;
    private String birthday;

    @Id
    @Column(name = "index_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "address_1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "address_2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MCustomer mCustomer = (MCustomer) o;

        if (indexNo != null ? !indexNo.equals(mCustomer.indexNo) : mCustomer.indexNo != null) return false;
        if (title != null ? !title.equals(mCustomer.title) : mCustomer.title != null) return false;
        if (name != null ? !name.equals(mCustomer.name) : mCustomer.name != null) return false;
        if (mobile != null ? !mobile.equals(mCustomer.mobile) : mCustomer.mobile != null) return false;
        if (address1 != null ? !address1.equals(mCustomer.address1) : mCustomer.address1 != null) return false;
        if (address2 != null ? !address2.equals(mCustomer.address2) : mCustomer.address2 != null) return false;
        if (city != null ? !city.equals(mCustomer.city) : mCustomer.city != null) return false;
        if (birthday != null ? !birthday.equals(mCustomer.birthday) : mCustomer.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo != null ? indexNo.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
