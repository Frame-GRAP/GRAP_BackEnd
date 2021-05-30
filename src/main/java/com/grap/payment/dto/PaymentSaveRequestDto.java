package com.grap.payment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentSaveRequestDto {

    private String customerUid;

    @Builder
    public PaymentSaveRequestDto(String customerUid) {
        this.customerUid = customerUid;
    }


}