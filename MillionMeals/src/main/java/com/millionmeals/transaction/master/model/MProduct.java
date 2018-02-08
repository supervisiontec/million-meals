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
    private Integer qty;

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

    @Basic
    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MProduct mProduct = (MProduct) o;

        if (indexNo != null ? !indexNo.equals(mProduct.indexNo) : mProduct.indexNo != null) return false;
        if (mUnit != null ? !mUnit.equals(mProduct.mUnit) : mProduct.mUnit != null) return false;
        if (mItem != null ? !mItem.equals(mProduct.mItem) : mProduct.mItem != null) return false;
        if (price != null ? !price.equals(mProduct.price) : mProduct.price != null) return false;
        if (qty != null ? !qty.equals(mProduct.qty) : mProduct.qty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo != null ? indexNo.hashCode() : 0;
        result = 31 * result + (mUnit != null ? mUnit.hashCode() : 0);
        result = 31 * result + (mItem != null ? mItem.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        return result;
    }
}
