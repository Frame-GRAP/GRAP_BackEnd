package com.grap.payment.controller;

import com.grap.payment.dto.PaymentSaveRequestDto;
import com.grap.payment.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class PaymentApiController {

    private final PaymentService paymentService;
    public static final String imp_key = "";
    public static final String imp_secret = "";

    @PostMapping("/api/checkPayment")
    public Long checkPayment(@RequestBody PaymentSaveRequestDto requestDto) throws IOException, IamportResponseException {

        IamportClient client = new IamportClient(imp_key, imp_secret);
        IamportResponse<Certification> certificationResponse = client.certificationByImpUid(requestDto.getCustomerUid());

        System.out.println("getMessage ->" + certificationResponse.getMessage());
        System.out.println("getResponse ->" + certificationResponse.getResponse());
        System.out.println("getBirth ->" + certificationResponse.getResponse().getBirth().toString());
        System.out.println("getGender ->" + certificationResponse.getResponse().getGender());
        System.out.println("getName ->" + certificationResponse.getResponse().getName());
        System.out.println("getPgProvider ->" + certificationResponse.getResponse().getPgProvider());
        System.out.println("getImpUid ->" + certificationResponse.getResponse().getImpUid().toString());
        System.out.println("getMerchantUid ->" + certificationResponse.getResponse().getMerchantUid().toString());
        System.out.println("getPgTid ->" + certificationResponse.getResponse().getPgTid().toString());
        System.out.println("getUniqueKey ->" + certificationResponse.getResponse().getUniqueKey().toString());
        System.out.println("getUniqueInSite ->" + certificationResponse.getResponse().getUniqueInSite().toString());
        System.out.println("getOrigin ->" + certificationResponse.getResponse().getOrigin().toString());
        System.out.println("getCarrier ->" + certificationResponse.getResponse().getCarrier().toString());
        System.out.println("getPhone ->" + certificationResponse.getResponse().getPhone().toString());

        return (long) 1;
    }

}
