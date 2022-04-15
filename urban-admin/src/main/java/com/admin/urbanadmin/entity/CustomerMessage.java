package com.admin.urbanadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerMessage {
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBookingUpdate() {
        return bookingUpdate;
    }

    public void setBookingUpdate(String bookingUpdate) {
        this.bookingUpdate = bookingUpdate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Integer getProviderLocationCode() {
        return providerLocationCode;
    }

    public void setProviderLocationCode(Integer providerLocationCode) {
        this.providerLocationCode = providerLocationCode;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    private String messageId;
    private String bookingUpdate;
    private String providerName;
    private String providerEmail;
    private String providerType;
    private Integer providerLocationCode;
    private Date messageDate;
}
