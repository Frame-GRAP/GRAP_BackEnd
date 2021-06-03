package com.grap.payment.controller;

import com.grap.payment.dto.PaymentReserveRequestDto;
import com.grap.payment.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.ScheduleData;
import com.siot.IamportRestClient.request.ScheduleEntry;
import com.siot.IamportRestClient.request.UnscheduleData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class PaymentApiController {

    @Value("${api.key}")
    private String imp_key;

    @Value("${api.secret}")
    private String imp_secret;

    private final PaymentService paymentService;

    @PostMapping("/api/checkPayment/iamport-callback")
    public String reservePayment(@RequestBody PaymentReserveRequestDto requestDto) throws IOException, IamportResponseException {

        IamportClient client = new IamportClient(imp_key, imp_secret);
        com.siot.IamportRestClient.response.Payment response = client.paymentByImpUid(requestDto.getImp_uid()).getResponse();

        long num = Long.parseLong(response.getMerchantUid().replaceAll("[^0-9]","")) + 1;
        String merchantUid = "정기결제_" + num;

        if(paymentService.savePayment(response.getCustomerUid(), merchantUid, response.getAmount()) > 0) {
            createSchedule(client, merchantUid, response.getCustomerUid(), response.getAmount());
            return "예약 완료";
        }
        else
            return "예약 취소";
    }

    @PostMapping("/api/checkPayment/unsubscribe/{customerUid}")
    public void cancleSubscription(@PathVariable String customerUid) throws IOException, IamportResponseException {

        UnscheduleData unscheduleData = new UnscheduleData(customerUid);
        IamportClient client = new IamportClient(imp_key, imp_secret);
        client.unsubscribeSchedule(unscheduleData);
    }

    private void createSchedule(IamportClient client, String merchantUid, String customerUid, BigDecimal amount) throws IOException, IamportResponseException {

        Date reserve = new Date();
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(reserve);
        myCal.add(Calendar.MINUTE, +10);
        reserve = myCal.getTime();

        ScheduleData scheduleData = new ScheduleData(customerUid);
        scheduleData.addSchedule(new ScheduleEntry(merchantUid, reserve, amount));
        client.subscribeSchedule(scheduleData);
    }
}
