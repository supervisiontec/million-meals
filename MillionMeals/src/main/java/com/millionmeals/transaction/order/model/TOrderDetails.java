package com.millionmeals.transaction.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by my on 2018-02-06.
 */
@Entity
@Table(name = "t_order_details", schema = "million_meals")
public class TOrderDetails {
    private int indexNo;
    private int mProduct;
    private String unit;
    private String itemType;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal value;
    private String status;
    private Byte isChange;
    private String itemName;
    private Timestamp date;
    private BigDecimal discount;
    private TOrder tOrderByTOrder;

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
    @Column(name = "m_product", nullable = false)
    public int getmProduct() {
        return mProduct;
    }

    public void setmProduct(int mProduct) {
        this.mProduct = mProduct;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 45)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "item_type", nullable = true, length = 45)
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Basic
    @Column(name = "qty", nullable = true)
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 2)
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "is_change", nullable = true)
    public Byte getIsChange() {
        return isChange;
    }

    public void setIsChange(Byte isChange) {
        this.isChange = isChange;
    }

    @Basic
    @Column(name = "item_name", nullable = true, length = 45)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 2)
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "t_order", referencedColumnName = "index_no", nullable = false)
    public TOrder gettOrderByTOrder() {
        return tOrderByTOrder;
    }

    public void settOrderByTOrder(TOrder tOrderByTOrder) {
        this.tOrderByTOrder = tOrderByTOrder;
    }

//    @Basic
//    @Column(name = "t_order")
//    public int gettOrderByTOrder() {
//        return tOrderByTOrder;
//    }
//
//    public void settOrderByTOrder(int tOrderByTOrder) {
//        this.tOrderByTOrder = tOrderByTOrder;
//    }

    @Override
    public String toString() {
        return "TOrderDetails{" +
                "indexNo=" + indexNo +
                ", mProduct=" + mProduct +
                ", unit='" + unit + '\'' +
                ", itemType='" + itemType + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", value=" + value +
                ", status='" + status + '\'' +
                ", isChange=" + isChange +
                ", itemName='" + itemName + '\'' +
                ", date=" + date +
                ", discount=" + discount +
                ", tOrderByTOrder=" + tOrderByTOrder +
                '}';
    }
}
