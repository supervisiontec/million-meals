package com.millionmeals.transaction.order.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-26.
 */
@Entity
@Table(name = "t_table_receive", schema = "million_meals", catalog = "")
public class TTableReceive {
    private int indexNo;
    private int mTable;
    private TOrder tOrder;
    private Boolean status;

    public TTableReceive() {
    }


    @Id
    @Column(name = "index_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(int indexNo) {
        this.indexNo = indexNo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "t_order")
    public TOrder gettOrder() {
        return tOrder;
    }

    public void settOrder(TOrder tOrder) {
        this.tOrder = tOrder;
    }

    @Basic
    @Column(name = "m_table", nullable = false)
    public int getmTable() {
        return mTable;
    }

    public void setmTable(int mTable) {
        this.mTable = mTable;
    }


    @Basic
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
