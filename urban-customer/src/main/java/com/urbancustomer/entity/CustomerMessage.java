package com.urbancustomer.entity;

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
    private String messageId;
    private String bookingUpdate;
    private String providerName;
    private String providerEmail;
    private String providerType;
    private Integer providerLocationCode;
    private Date messageDate;
}
