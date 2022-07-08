package com.narola.bookmyseat.jpa;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column
    private int accountNumber;
    @Column
    private String fullName;
    @Column
    private double balance;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date accActivationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branchId", referencedColumnName = "branchId")
    Branch branch;

    public Date getAccActivationDate() {
        return accActivationDate;
    }

    public void setAccActivationDate(Date accActivationDate) {
        this.accActivationDate = accActivationDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber=" + accountNumber +
                ", fullName='" + fullName + '\'' +
                ", balance=" + balance +
                ", branch=" + branch +
                '}';
    }
}
