package com.narola.bookmyseat.jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CustomerId implements Serializable {
    private int customerId;
    private String customerMobile;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
}
