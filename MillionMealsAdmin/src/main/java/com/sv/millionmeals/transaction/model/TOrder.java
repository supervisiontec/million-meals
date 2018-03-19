/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.transaction.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kalum
 */
@Entity
@Table(name = "t_order")
public class TOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "index_no")
    private Integer indexNo;
    
    @Column(name = "order_no")
    private Integer orderNo;
    
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @Column(name = "total_sub")
    private BigDecimal totalSub;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_tax")
    private BigDecimal totalTax;
    
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    
    @Size(max = 45)
    @Column(name = "order_type1")
    private String orderType1;
    
    @Size(max = 50)
    @Column(name = "order_type2")
    private String orderType2;
    
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    
    @Column(name = "kot_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kotTime;
    
    @Column(name = "kot_status")
    private Boolean kotStatus;
    
    @Column(name = "temporary_bill")
    private Boolean temporaryBill;
    
    @Column(name = "order_cancel")
    private Boolean orderCancel;
    
    @Size(max = 200)
    @Column(name = "cancel_reason")
    private String cancelReason;
    
    @Column(name = "cancel_user")
    private Integer cancelUser;
    
    @OneToMany(mappedBy = "tOrderByTOrder")
    private Collection<TOrderDetails> tOrderDetailssByIndexNo;

    public TOrder() {
    }

    public TOrder(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public TOrder(Integer indexNo, BigDecimal totalTax) {
        this.indexNo = indexNo;
        this.totalTax = totalTax;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalSub() {
        return totalSub;
    }

    public void setTotalSub(BigDecimal totalSub) {
        this.totalSub = totalSub;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderType1() {
        return orderType1;
    }

    public void setOrderType1(String orderType1) {
        this.orderType1 = orderType1;
    }

    public String getOrderType2() {
        return orderType2;
    }

    public void setOrderType2(String orderType2) {
        this.orderType2 = orderType2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getKotTime() {
        return kotTime;
    }

    public void setKotTime(Date kotTime) {
        this.kotTime = kotTime;
    }

    public Boolean getKotStatus() {
        return kotStatus;
    }

    public void setKotStatus(Boolean kotStatus) {
        this.kotStatus = kotStatus;
    }

    public Boolean getTemporaryBill() {
        return temporaryBill;
    }

    public void setTemporaryBill(Boolean temporaryBill) {
        this.temporaryBill = temporaryBill;
    }

    public Boolean getOrderCancel() {
        return orderCancel;
    }

    public void setOrderCancel(Boolean orderCancel) {
        this.orderCancel = orderCancel;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(Integer cancelUser) {
        this.cancelUser = cancelUser;
    }

    public Collection<TOrderDetails> gettOrderDetailssByIndexNo() {
        return tOrderDetailssByIndexNo;
    }

    public void settOrderDetailssByIndexNo(Collection<TOrderDetails> tOrderDetailssByIndexNo) {
        this.tOrderDetailssByIndexNo = tOrderDetailssByIndexNo;
    }

    @Override
    public String toString() {
        return "TOrder{" + "indexNo=" + indexNo + ", orderNo=" + orderNo + ", date=" + date + ", totalSub=" + totalSub + ", totalTax=" + totalTax + ", totalAmount=" + totalAmount + ", orderType1=" + orderType1 + ", orderType2=" + orderType2 + ", status=" + status + ", kotTime=" + kotTime + ", kotStatus=" + kotStatus + ", temporaryBill=" + temporaryBill + ", orderCancel=" + orderCancel + ", cancelReason=" + cancelReason + ", cancelUser=" + cancelUser + ", tOrderDetailssByIndexNo=" + tOrderDetailssByIndexNo + '}';
    }

}
