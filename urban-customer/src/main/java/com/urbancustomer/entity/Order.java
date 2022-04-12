package com.urbancustomer.entity;
import java.time.Instant;
import java.util.Date;

public class Order {
    
    private Integer orderId;
    private Integer customerId;

    public Order() {
    }

    private String orderStatus;
    private String professionalType;
    private Date scheduledDate;
    private String jobDescription;
    private Date createdDate;
    private Date updatedDate;

    public Integer getOrderId() {
        return orderId;
    }

    public Order(Integer orderId, Integer customerId, String orderStatus, String professionalType, Date scheduledDate, String jobDescription, Date createdDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.professionalType = professionalType;
        this.scheduledDate = scheduledDate;
        this.jobDescription = jobDescription;
        this.createdDate = createdDate;
        this.updatedDate = Date.from( Instant.now());
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

    public Order(Integer orderId, Integer customerId, String orderStatus, String professionalType, Date scheduledDate, String jobDescription, Date createdDate, Date updatedDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.professionalType = professionalType;
        this.scheduledDate = scheduledDate;
        this.jobDescription = jobDescription;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
