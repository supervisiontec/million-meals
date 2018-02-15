package com.millionmeals.transaction.payment;

import com.millionmeals.transaction.payment.model.TInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by my on 2018-02-12.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/restaurant/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(path = "/invoice/{index}",method = RequestMethod.GET)
    public TInvoice findOne(@PathVariable("index") int index){
       return paymentService.findOne(index);
    }

    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public Boolean savePayment(@RequestBody TInvoice invoice){
       return paymentService.savePayment(invoice);
    }
}
