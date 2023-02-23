package com.spl.customerRewards.domain;

import java.util.List;
import java.util.Objects;

public class Request {

    List<CustomerInfo> customerInfoList;

    public List<CustomerInfo> getCustomerInfoList() {
        return customerInfoList;
    }

    public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
        this.customerInfoList = customerInfoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(getCustomerInfoList(), request.getCustomerInfoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerInfoList());
    }

    @Override
    public String toString() {
        return "Request{" +
                "customerInfoList=" + customerInfoList +
                '}';
    }
}