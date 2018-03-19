/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author kalum
 */
@Entity
@Table(name = "t_order_details")
public class TOrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;
    
    @Size(max = 45)
    @Column(name = "unit")
    private String unit;
    
    @Size(max = 45)
    @Column(name = "item_type")
    private String itemType;
    
    @Size(max = 45)
    @Column(name = "item_type2")
    private String itemType2;
    
    @Column(name = "qty")
    private Integer qty;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "value")
    private BigDecimal value;
    
    @Column(name = "discount")
    private BigDecimal discount;
    
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    
    @Column(name = "is_change")
    private Boolean isChange;
    
    @Size(max = 45)
    @Column(name = "item_name")
    private String itemName;
    
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "cancel_status")
    private Boolean cancelStatus;
    
    @Size(max = 300)
    @Column(name = "cancel_reason")
    private String cancelReason;
    
    @Column(name = "kot_status")
    private Boolean kotStatus;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "t_order", referencedColumnName = "index_no", nullable = false)
    private TOrder tOrderByTOrder;

    public TOrderDetails() {
    }

    public TOrderDetails(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType2() {
        return itemType2;
    }

    public void setItemType2(String itemType2) {
        this.itemType2 = itemType2;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsChange() {
        return isChange;
    }

    public void setIsChange(Boolean isChange) {
        this.isChange = isChange;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Boolean getKotStatus() {
        return kotStatus;
    }

    public void setKotStatus(Boolean kotStatus) {
        this.kotStatus = kotStatus;
    }

    public TOrder gettOrderByTOrder() {
        return tOrderByTOrder;
    }

    public void settOrderByTOrder(TOrder tOrderByTOrder) {
        this.tOrderByTOrder = tOrderByTOrder;
    }

    @Override
    public String toString() {
        return "TOrderDetails{" + "indexNo=" + indexNo + ", unit=" + unit + ", itemType=" + itemType + ", itemType2=" + itemType2 + ", qty=" + qty + ", price=" + price + ", value=" + value + ", discount=" + discount + ", status=" + status + ", isChange=" + isChange + ", itemName=" + itemName + ", date=" + date + ", cancelStatus=" + cancelStatus + ", cancelReason=" + cancelReason + ", kotStatus=" + kotStatus + ", tOrderByTOrder=" + tOrderByTOrder + '}';
    }

}
