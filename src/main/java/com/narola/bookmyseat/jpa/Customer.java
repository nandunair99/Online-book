package com.narola.bookmyseat.jpa;

import javax.persistence.*;

@Entity
public class Customer {

    @EmbeddedId
    private CustomerId customerId;
    @Column
    private String customerName;
    @Embedded
    private Address address;
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
