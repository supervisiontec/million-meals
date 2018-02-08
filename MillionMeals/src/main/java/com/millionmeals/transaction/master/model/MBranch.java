package com.millionmeals.transaction.master.model;

import javax.persistence.*;

/**
 * Created by my on 2018-02-02.
 */
@Entity
@Table(name = "m_branch", schema = "million_meals", catalog = "")
public class MBranch {
    private Integer indexNo;
    private String name;
    private String colour;
    private String branchCode;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String header;
    private String footer;

    @Id
    @Column(name = "index_no")
    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "colour")
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Basic
    @Column(name = "branch_code")
    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Basic
    @Column(name = "address_line1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Basic
    @Column(name = "address_line2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Basic
    @Column(name = "address_line3")
    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @Basic
    @Column(name = "header")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Basic
    @Column(name = "footer")
    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MBranch mBranch = (MBranch) o;

        if (indexNo != null ? !indexNo.equals(mBranch.indexNo) : mBranch.indexNo != null) return false;
        if (name != null ? !name.equals(mBranch.name) : mBranch.name != null) return false;
        if (colour != null ? !colour.equals(mBranch.colour) : mBranch.colour != null) return false;
        if (branchCode != null ? !branchCode.equals(mBranch.branchCode) : mBranch.branchCode != null) return false;
        if (addressLine1 != null ? !addressLine1.equals(mBranch.addressLine1) : mBranch.addressLine1 != null)
            return false;
        if (addressLine2 != null ? !addressLine2.equals(mBranch.addressLine2) : mBranch.addressLine2 != null)
            return false;
        if (addressLine3 != null ? !addressLine3.equals(mBranch.addressLine3) : mBranch.addressLine3 != null)
            return false;
        if (header != null ? !header.equals(mBranch.header) : mBranch.header != null) return false;
        if (footer != null ? !footer.equals(mBranch.footer) : mBranch.footer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = indexNo != null ? indexNo.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (branchCode != null ? branchCode.hashCode() : 0);
        result = 31 * result + (addressLine1 != null ? addressLine1.hashCode() : 0);
        result = 31 * result + (addressLine2 != null ? addressLine2.hashCode() : 0);
        result = 31 * result + (addressLine3 != null ? addressLine3.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (footer != null ? footer.hashCode() : 0);
        return result;
    }
}
