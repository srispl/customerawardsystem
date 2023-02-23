package com.spl.customerRewards.domain;

import java.util.List;
import java.util.Objects;

public class Response {

    List<CustomerInfo> customerInfoList;

    String error;

    public List<CustomerInfo> getCustomerInfoList() {
        return customerInfoList;
    }

    public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
        this.customerInfoList = customerInfoList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return Objects.equals(getCustomerInfoList(), response.getCustomerInfoList()) && Objects.equals(getError(), response.getError());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerInfoList(), getError());
    }

    @Override
    public String toString() {
        return "Response{" +
                "customerInfoList=" + customerInfoList +
                ", error='" + error + '\'' +
                '}';
    }
}