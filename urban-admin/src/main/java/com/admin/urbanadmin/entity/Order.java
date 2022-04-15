package com.admin.urbanadmin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private String orderStatus;
    private String professionalType;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date scheduledDate;
    private String jobDescription;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private Integer providerId;
    private Integer locationcode;
    private String customerAdddress;

    public String getCustomerAdddress() {
        return customerAdddress;
    }

    public void setCustomerAdddress(String customerAdddress) {
        this.customerAdddress = customerAdddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public Order() {
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    private Provider provider;
    private Date createdDate;
    private Date updatedDate;

    public Integer getOrderId() {
        return orderId;
    }

    public Order(Integer customerId, String orderStatus, String professionalType, String scheduledDate,
            String jobDescription) {
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.professionalType = professionalType;
        this.scheduledDate = parseDate(scheduledDate);
        this.jobDescription = jobDescription;
        this.createdDate = Date.from(Instant.now());
        this.updatedDate = Date.from(Instant.now());
    }

    public Order(Integer orderId, Integer customerId, String orderStatus, String professionalType, Date scheduledDate,
            String jobDescription, Integer providerId, Provider provider, Date createdDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.professionalType = professionalType;
        this.scheduledDate = scheduledDate;
        this.jobDescription = jobDescription;
        this.providerId = providerId;
        this.provider = provider;
        this.createdDate = createdDate;
        this.updatedDate = Date.from(Instant.now());
    }

    public Order(Integer orderId, Integer customerId, String orderStatus, String professionalType, Date scheduledDate,
            String jobDescription, Date createdDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.professionalType = professionalType;
        this.scheduledDate = scheduledDate;
        this.jobDescription = jobDescription;
        this.createdDate = createdDate;
        this.updatedDate = Date.from(Instant.now());
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProfessionalType() {
        return professionalType;
    }

    public void setProfessionalType(String professionalType) {
        this.professionalType = professionalType;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Order(Integer orderId, Integer customerId, String orderStatus, String professionalType, Date scheduledDate,
            String jobDescription, Date createdDate, Date updatedDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.professionalType = professionalType;
        this.scheduledDate = scheduledDate;
        this.jobDescription = jobDescription;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(Integer locationcode) {
        this.locationcode = locationcode;
    }
}
