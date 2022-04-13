package com.admin.urbanadmin.entity;

public class JsonRequest {
    private String status;
    private String type;
    private Integer provider;

    public String getStatus() {
        return status;
    }

    public JsonRequest(String status, String type, Integer provider) {
        this.status = status;
        this.type = type;
        this.provider = provider;
    }

    public JsonRequest(String status, String type) {
        this.status = status;
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProvider(Integer provider) {
        this.provider = provider;
    }

    public String getType() {
        return type;
    }

    public JsonRequest() {
    }

    public Integer getProvider() {
        return provider;
    }
}
