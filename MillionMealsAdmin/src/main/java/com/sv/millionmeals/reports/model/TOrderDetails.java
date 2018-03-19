//package com.sv.millionmeals.reports.model;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//
///**
// * Created by my on 2018-02-21.
// */
//@Entity
//@Table(name = "t_order_details", schema = "million_meals", catalog = "")
//public class TOrderDetails {
//    private int indexNo;
//    private String unit;
//    private String itemType;
//    private Integer qty;
//    private BigDecimal price;
//    private BigDecimal value;
//    private String status;
//    private Byte isChange;
//    private String itemName;
//    private Timestamp date;
//    private BigDecimal discount;
//
//    @Id
//    @Column(name = "index_no", nullable = false)
//    public int getIndexNo() {
//        return indexNo;
//    }
//
//    public void setIndexNo(int indexNo) {
//        this.indexNo = indexNo;
//    }
//
//    @Basic
//    @Column(name = "unit", nullable = true, length = 45)
//    public String getUnit() {
//        return unit;
//    }
//
//    public void setUnit(String unit) {
//        this.unit = unit;
//    }
//
//    @Basic
//    @Column(name = "item_type", nullable = true, length = 45)
//    public String getItemType() {
//        return itemType;
//    }
//
//    public void setItemType(String itemType) {
//        this.itemType = itemType;
//    }
//
//    @Basic
//    @Column(name = "qty", nullable = true)
//    public Integer getQty() {
//        return qty;
//    }
//
//    public void setQty(Integer qty) {
//        this.qty = qty;
//    }
//
//    @Basic
//    @Column(name = "price", nullable = true, precision = 2)
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    @Basic
//    @Column(name = "value", nullable = true, precision = 2)
//    public BigDecimal getValue() {
//        return value;
//    }
//
//    public void setValue(BigDecimal value) {
//        this.value = value;
//    }
//
//    @Basic
//    @Column(name = "status", nullable = true, length = 45)
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Basic
//    @Column(name = "is_change", nullable = true)
//    public Byte getIsChange() {
//        return isChange;
//    }
//
//    public void setIsChange(Byte isChange) {
//        this.isChange = isChange;
//    }
//
//    @Basic
//    @Column(name = "item_name", nullable = true, length = 45)
//    public String getItemName() {
//        return itemName;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    @Basic
//    @Column(name = "date", nullable = true)
//    public Timestamp getDate() {
//        return date;
//    }
//
//    public void setDate(Timestamp date) {
//        this.date = date;
//    }
//
//    @Basic
//    @Column(name = "discount", nullable = true, precision = 2)
//    public BigDecimal getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(BigDecimal discount) {
//        this.discount = discount;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        TOrderDetails that = (TOrderDetails) o;
//
//        if (indexNo != that.indexNo) return false;
//        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
//        if (itemType != null ? !itemType.equals(that.itemType) : that.itemType != null) return false;
//        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;
//        if (price != null ? !price.equals(that.price) : that.price != null) return false;
//        if (value != null ? !value.equals(that.value) : that.value != null) return false;
//        if (status != null ? !status.equals(that.status) : that.status != null) return false;
//        if (isChange != null ? !isChange.equals(that.isChange) : that.isChange != null) return false;
//        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;
//        if (date != null ? !date.equals(that.date) : that.date != null) return false;
//        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = indexNo;
//        result = 31 * result + (unit != null ? unit.hashCode() : 0);
//        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
//        result = 31 * result + (qty != null ? qty.hashCode() : 0);
//        result = 31 * result + (price != null ? price.hashCode() : 0);
//        result = 31 * result + (value != null ? value.hashCode() : 0);
//        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (isChange != null ? isChange.hashCode() : 0);
//        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
//        result = 31 * result + (date != null ? date.hashCode() : 0);
//        result = 31 * result + (discount != null ? discount.hashCode() : 0);
//        return result;
//    }
//}
