package com.millionmeals.transaction.master.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by my on 2018-02-02.
 */
@Entity
@Table(name = "m_product", schema = "million_meals", catalog = "")
public class MProduct {
    private Integer indexNo;
    private MUnit mUnit;
    private Integer mItem;
    private BigDecimal price;

    @Id
    @Column(name = "index_no")
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }
    @ManyToOne
    @JoinColumn(name = "m_unit",referencedColumnName = "index_no")
    public MUnit getmUnit() {
        return mUnit;
    }

    public void setmUnit(MUnit mUnit) {
        this.mUnit = mUnit;
    }

    @Basic
    @Column(name = "m_item")
    public Integer getmItem() {
        return mItem;
    }

    public void setmItem(Integer mItem) {
        this.mItem = mItem;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
