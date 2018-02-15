package com.millionmeals.transaction.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by my on 2018-02-12.
 */
@Entity
@Table(name = "t_payment_details", schema = "million_meals", catalog = "")
public class TPaymentDetails {
    private int indexNo;
    private Timestamp chequeDate;
    private BigDecimal amount;
    private String type;
    private String bank;
    private String bankBranch;
    private String cardType;
    private String form;
    private Integer number;
    private TPayment tPaymentByTPayment;

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
    @Column(name = "cheque_date", nullable = true)
    public Timestamp getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Timestamp chequeDate) {
        this.chequeDate = chequeDate;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "bank", nullable = true, length = 45)
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Basic
    @Column(name = "bank_branch", nullable = true, length = 45)
    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    @Basic
    @Column(name = "card_type", nullable = true, length = 45)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "form", nullable = true, length = 45)
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "number", nullable = true, length = 20)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "t_payment", referencedColumnName = "index_no")
    public TPayment gettPaymentByTPayment() {
        return tPaymentByTPayment;
    }

    public void settPaymentByTPayment(TPayment tPaymentByTPayment) {
        this.tPaymentByTPayment = tPaymentByTPayment;
    }
}
