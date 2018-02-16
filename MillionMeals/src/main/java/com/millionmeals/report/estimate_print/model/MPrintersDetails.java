/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.millionmeals.report.estimate_print.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author kavish manjitha
 */
@Entity
@Table(name = "m_printers_details")
public class MPrintersDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_no")
    private Integer indexNo;

    @Column(name = "branch_index_no")
    private Integer branchIndexNo;

    @Size(max = 50)
    @Column(name = "branch_name")
    private String branchName;
    
    @Size(max = 50)
    @Column(name = "branch_contact_no")
    private String branchContactNo;

    @Size(max = 50)
    @Column(name = "printers")
    private String printers;

    @Size(max = 50)
    @Column(name = "print_status")
    @Enumerated(EnumType.STRING)
    private ActivationStatus printStatus;


    public MPrintersDetails() {
    }


    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public Integer getBranchIndexNo() {
        return branchIndexNo;
    }

    public void setBranchIndexNo(Integer branchIndexNo) {
        this.branchIndexNo = branchIndexNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchContactNo() {
        return branchContactNo;
    }

    public void setBranchContactNo(String branchContactNo) {
        this.branchContactNo = branchContactNo;
    }

    public String getPrinters() {
        return printers;
    }

    public void setPrinters(String printers) {
        this.printers = printers;
    }

    public ActivationStatus getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(ActivationStatus printStatus) {
        this.printStatus = printStatus;
    }


}
