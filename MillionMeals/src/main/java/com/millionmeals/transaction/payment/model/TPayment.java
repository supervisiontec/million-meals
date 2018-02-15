package com.millionmeals.transaction.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by my on 2018-02-12.
 */
@Entity
@Table(name = "t_payment", schema = "million_meals")
public class TPayment {
    private int indexNo;
    private TInvoice tInvoice;
    private BigDecimal totalAmount;
    private BigDecimal cashAmount;
    private BigDecimal cardAmount;
    private BigDecimal chequeAmount;
    private BigDecimal overPayment;
    private BigDecimal PayAmount;
    private BigDecimal balance;
    private Date date;
    private Collection<TPaymentDetails> tPaymentDetailssByIndexNo;

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
    @Column(name = "total_amount", nullable = true, precision = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "cash_amount", nullable = true, precision = 2)
    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    @Basic
    @Column(name = "card_amount", nullable = true, precision = 2)
    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    @Basic
    @Column(name = "cheque_amount", nullable = true, precision = 2)
    public BigDecimal getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(BigDecimal chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    @Basic
    @Column(name = "over_payment", nullable = true, precision = 2)
    public BigDecimal getOverPayment() {
        return overPayment;
    }

    public void setOverPayment(BigDecimal overPayment) {
        this.overPayment = overPayment;
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
    @Column(name = "pay_amount")
    public BigDecimal getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        PayAmount = payAmount;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @OneToMany(mappedBy = "tPaymentByTPayment",cascade = CascadeType.ALL)
    public Collection<TPaymentDetails> gettPaymentDetailssByIndexNo() {
        return tPaymentDetailssByIndexNo;
    }

    public void settPaymentDetailssByIndexNo(Collection<TPaymentDetails> tPaymentDetailssByIndexNo) {
        this.tPaymentDetailssByIndexNo = tPaymentDetailssByIndexNo;
    }


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "t_Invoice", referencedColumnName = "index_no")
    public TInvoice gettInvoice() {
        return tInvoice;
    }

    public void settInvoice(TInvoice tInvoice) {
        this.tInvoice = tInvoice;
    }
}
