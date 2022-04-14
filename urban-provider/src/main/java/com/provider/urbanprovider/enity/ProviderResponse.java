package com.provider.urbanprovider.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProviderResponse {
    private String name;
    private String phone;
    private String email;
    private Address currentAdddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getCurrentAdddress() {
        return currentAdddress;
    }

    public void setCurrentAdddress(Address currentAdddress) {
        this.currentAdddress = currentAdddress;
    }
}
