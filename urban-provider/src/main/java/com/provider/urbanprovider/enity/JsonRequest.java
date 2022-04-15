package com.provider.urbanprovider.enity;

public class JsonRequest {
    public JsonRequest() {
    }

    private String request;
    private Integer orderId;
    private Integer providerId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public JsonRequest(String request) {
        this.request = request;
    }
}
