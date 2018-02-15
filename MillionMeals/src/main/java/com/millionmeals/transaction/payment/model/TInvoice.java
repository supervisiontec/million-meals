package com.millionmeals.transaction.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by my on 2018-02-12.
 */
@Entity
@Table(name = "t_invoice", schema = "million_meals", catalog = "")
public class TInvoice {
    private int indexNo;
    private int tOrder;
    private Integer invoiceNo;
    private Date date;
    private Integer branch;
    private Integer discountRate;
    private BigDecimal discountValue;
    private BigDecimal finalAmount;
    private TPayment tPaymentByIndexNo;

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
    @Column(name = "t_order", nullable = false)
    public int gettOrder() {
        return tOrder;
    }

    public void settOrder(int tOrder) {
        this.tOrder = tOrder;
    }

    @Basic
    @Column(name = "invoice_no", nullable = true)
    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
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
    @Column(name = "branch", nullable = true)
    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    @Basic
    @Column(name = "discount_rate", nullable = true)
    public Integer getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    @Basic
    @Column(name = "discount_value", nullable = true, precision = 2)
    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    @Basic
    @Column(name = "final_amount", nullable = true, precision = 2)
    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "t_payment", referencedColumnName = "index_no", nullable = false)
//    public TPayment gettPaymentByTPayment() {
//        return tPaymentByTPayment;
//    }
//
//    public void settPaymentByTPayment(TPayment tPaymentByTPayment) {
//        this.tPaymentByTPayment = tPaymentByTPayment;
//    }

    @OneToOne(mappedBy = "tInvoice",cascade = CascadeType.ALL)
    public TPayment gettPaymentByIndexNo() {
        return tPaymentByIndexNo;
    }

    public void settPaymentByIndexNo(TPayment tPaymentByIndexNo) {
        this.tPaymentByIndexNo = tPaymentByIndexNo;
    }
}
