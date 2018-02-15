package com.millionmeals.transaction.order.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by my on 2018-02-06.
 */
@Entity
@Table(name = "t_order", schema = "million_meals", catalog = "")
public class TOrder {
    private int indexNo;
    private int mCustomer;
    private int mBranch;
    private int orderNo;
    private Date date;
    private BigDecimal totalSub;
    private BigDecimal totalTax;
    private BigDecimal totalAmount;
    private String orderType1;
    private String orderType2;
    private String status;
    private Collection<TOrderDetails> tOrderDetailssByIndexNo;

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
    @Column(name = "m_customer", nullable = false)
    public int getmCustomer() {
        return mCustomer;
    }

    public void setmCustomer(int mCustomer) {
        this.mCustomer = mCustomer;
    }

    @Basic
    @Column(name = "m_branch", nullable = false)
    public int getmBranch() {
        return mBranch;
    }

    public void setmBranch(int mBranch) {
        this.mBranch = mBranch;
    }

    @Basic
    @Column(name = "order_no", nullable = true, length = 45)
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "total_sub", nullable = true, precision = 2)
    public BigDecimal getTotalSub() {
        return totalSub;
    }

    public void setTotalSub(BigDecimal totalSub) {
        this.totalSub = totalSub;
    }

    @Basic
    @Column(name = "total_tax", nullable = true, precision = 2)
    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    @Basic
    @Column(name = "total_amount", nullable = true, precision = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }



    @Basic
    @Column(name = "order_type1", nullable = true, length = 45)
    public String getOrderType1() {
        return orderType1;
    }

    public void setOrderType1(String orderType1) {
        this.orderType1 = orderType1;
    }
    @Basic
    @Column(name = "order_type2", nullable = true, length = 45)
    public String getOrderType2() {
        return orderType2;
    }

    public void setOrderType2(String orderType2) {
        this.orderType2 = orderType2;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "tOrderByTOrder")
    public Collection<TOrderDetails> gettOrderDetailssByIndexNo() {
        return tOrderDetailssByIndexNo;
    }

    public void settOrderDetailssByIndexNo(Collection<TOrderDetails> tOrderDetailssByIndexNo) {
        this.tOrderDetailssByIndexNo = tOrderDetailssByIndexNo;
    }

    @Override
    public String toString() {
        return "TOrder{" +
                "indexNo=" + indexNo +
                ", mCustomer=" + mCustomer +
                ", mBranch=" + mBranch +
                ", orderNo=" + orderNo +
                ", date=" + date +
                ", totalSub=" + totalSub +
                ", totalTax=" + totalTax +
                ", totalAmount=" + totalAmount +
                ", orderType1='" + orderType1 + '\'' +
                ", orderType2='" + orderType2 + '\'' +
                ", status='" + status + '\'' +
                ", tOrderDetailssByIndexNo=" + tOrderDetailssByIndexNo +
                '}';
    }
}
