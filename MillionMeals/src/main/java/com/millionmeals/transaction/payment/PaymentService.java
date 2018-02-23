package com.millionmeals.transaction.payment;

import com.millionmeals.transaction.order.OrderRepository;
import com.millionmeals.transaction.order.model.TOrder;
import com.millionmeals.transaction.payment.model.TInvoice;
import com.millionmeals.transaction.payment.model.TPayment;
import com.millionmeals.transaction.payment.model.TPaymentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by my on 2018-02-12.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;


    public TInvoice findOne(int index) {
        return invoiceRepository.findOne(index);
    }

    @Transactional
    public TInvoice savePayment(TInvoice invoice) {
        int  branch = 1;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

       int invoiceNo = paymentRepository.findLastInvoiceNoByDate(dateFormat.format(date),branch);

        invoice.gettPaymentByIndexNo().setDate(new Date());
        invoice.gettPaymentByIndexNo().settInvoice(invoice);

        for (TPaymentDetails paymentDetails : invoice.gettPaymentByIndexNo().gettPaymentDetailssByIndexNo()) {
            paymentDetails.settPaymentByTPayment(invoice.gettPaymentByIndexNo());
        }

        invoice.setInvoiceNo(invoiceNo);
        invoice.setDate(new Date());
        TOrder order = orderRepository.findOne(invoice.gettOrder());
        order.setStatus("complete");
        orderRepository.save(order);
        invoiceRepository.save(invoice);

        return invoice;
    }
}
