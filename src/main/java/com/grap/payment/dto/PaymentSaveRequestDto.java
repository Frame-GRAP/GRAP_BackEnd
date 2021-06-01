package com.grap.payment.dto;

import com.grap.payment.domain.Payment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class PaymentSaveRequestDto {

    private String customerUid;
    private String merchantUid;
    private BigDecimal paidAmount;
    private Long userId;
    private Long membershipId;

    @Builder
    public PaymentSaveRequestDto(String customerUid, String merchantUid, BigDecimal paidAmount) {
        this.customerUid = customerUid;
        this.merchantUid = merchantUid;
        this.paidAmount = paidAmount;
    }

    public Payment toEntity() {
        return Payment.builder()
                .customerUid(customerUid)
                .merchantUid(merchantUid)
                .paidAmount(paidAmount)
                .build();
    }
}