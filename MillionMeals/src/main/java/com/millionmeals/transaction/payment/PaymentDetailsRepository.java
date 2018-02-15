package com.millionmeals.transaction.payment;

import com.millionmeals.transaction.payment.model.TPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-12.
 */
public interface PaymentDetailsRepository extends JpaRepository<TPaymentDetails,Integer> {
}
