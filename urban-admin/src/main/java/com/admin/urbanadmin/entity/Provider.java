package com.admin.urbanadmin.entity;

public class Provider {
    private Integer providerId;
    private String name;
    private String email;
    private String type;
    private Integer locationCode;
    private String status;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Integer locationCode) {
        this.locationCode = locationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Provider(String status) {
        this.status = status;
    }

    public Provider() {
    }

    public Provider(Integer providerId, String name, String email, String type, Integer locationCode, String status) {
        this.providerId = providerId;
        this.name = name;
        this.email = email;
        this.type = type;
        this.locationCode = locationCode;
        this.status = status;
    }
}
