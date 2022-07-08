package com.narola.bookmyseat.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;
    @Column
    private String branchCode;
    @Column
    private String branchName;

    @OneToMany(mappedBy = "branch")
    private List<Account> accountList;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchId=" + branchId +
                ", branchCode='" + branchCode + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}
