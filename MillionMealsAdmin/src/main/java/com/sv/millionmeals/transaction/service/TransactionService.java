/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.transaction.service;

import com.sv.millionmeals.transaction.model.TOrder;
import com.sv.millionmeals.transaction.model.TOrderDetails;
import com.sv.millionmeals.transaction.repository.OrderDetailRepository;
import com.sv.millionmeals.transaction.repository.OrderRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kalum
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransactionService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<TOrder> findAllOrders() {
        return orderRepository.findAll();
    }

    public TOrder findOrderByIndexNo(Integer indexNo) {
        return orderRepository.findByIndexNo(indexNo);
    }

    @Transactional
    public TOrderDetails canselOrderDetailByIndexNo(TOrderDetails orderDetails) {
        orderDetails.setCancelStatus(Boolean.TRUE);
        return orderDetailRepository.save(orderDetails);
//        TOrder order = orderRepository.findOne(orderDetails.);
    }

    @Transactional
    public TOrder saveOrder(TOrder order) {
        Collection<TOrderDetails> tOrderDetailssByIndexNo = order.gettOrderDetailssByIndexNo();
        for (TOrderDetails tOrderDetails : tOrderDetailssByIndexNo) {
            tOrderDetails.settOrderByTOrder(order);
            orderDetailRepository.save(tOrderDetails);
        }
        return orderRepository.save(order);
    }
}
