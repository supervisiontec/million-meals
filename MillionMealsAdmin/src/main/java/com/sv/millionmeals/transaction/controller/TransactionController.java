/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.transaction.controller;

import com.sv.millionmeals.transaction.model.TOrder;
import com.sv.millionmeals.transaction.model.TOrderDetails;
import com.sv.millionmeals.transaction.service.TransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kalum
 */

@CrossOrigin
@RestController
@RequestMapping("/api/v1/millionMeals/transasction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @RequestMapping(value = "/order/get-all-orders", method = RequestMethod.GET)
    public List<TOrder> findAllOrders(){
        return transactionService.findAllOrders();
    }
    @RequestMapping(value = "/order/find-orders-by-orderNo/{indexNo}", method = RequestMethod.GET)
    public TOrder findOrderByIndexNo(@PathVariable Integer indexNo){
        return transactionService.findOrderByIndexNo(indexNo);
        
    }
    @RequestMapping(value = "/order-detail/cansel-orders-detail", method = RequestMethod.POST)
    public TOrderDetails canselOrderDetailByIndexNo(@RequestBody TOrderDetails orderDetails){
        return transactionService.canselOrderDetailByIndexNo(orderDetails);
        
    }
    @RequestMapping(value = "/order/save-orders", method = RequestMethod.POST)
    public TOrder saveOrder(@RequestBody TOrder order){
        return transactionService.saveOrder(order);
        
    }
}
