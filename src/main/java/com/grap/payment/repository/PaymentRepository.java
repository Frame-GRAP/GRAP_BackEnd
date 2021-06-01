package com.grap.payment.repository;

import com.grap.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findFirstByCustomerUidOrderByIdDesc(String customerUid);
}
