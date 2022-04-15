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
public class ProviderMessage {
    private String messageId;
    private String message;
    private Integer providerId;
    private Integer orderId;
    private Date messageDate;
}
