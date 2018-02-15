package com.millionmeals.transaction.payment;

import com.millionmeals.transaction.payment.model.TInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-12.
 */
public interface InvoiceRepository extends JpaRepository<TInvoice,Integer> {
}
