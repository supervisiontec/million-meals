package com.millionmeals.transaction.payment;

import com.millionmeals.transaction.payment.model.TPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by my on 2018-02-12.
 */
public interface PaymentRepository extends JpaRepository<TPayment,Integer> {

    @Query(value = "select ifnull(max(t_invoice.invoice_no)+1,1) as next_no\n" +
            "from t_invoice\n" +
            "where date(t_invoice.date) =:date and t_invoice.branch=:branch",nativeQuery = true)
    public int findLastInvoiceNoByDate(@Param("date") String date, @Param("branch") int branch);
}
