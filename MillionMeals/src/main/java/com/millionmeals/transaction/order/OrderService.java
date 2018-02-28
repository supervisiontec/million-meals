package com.millionmeals.transaction.order;

import com.millionmeals.transaction.master.*;
import com.millionmeals.transaction.master.model.*;
import com.millionmeals.transaction.order.model.TOrder;
import com.millionmeals.transaction.order.model.TOrderDetails;
import com.millionmeals.transaction.order.model.TTableReceive;
import com.millionmeals.zutil.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by my on 2018-02-01.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MainCategoryRepository mainCategoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private TableRecieveRepository tableRecieveRepository;


    public List<TOrder> getAllOrder() {
        return orderRepository.findAll();
    }

    @Transactional
    public TOrder saveOrder(TOrder order, int index,int tableIndex) {
        if (order.gettOrderDetailssByIndexNo().size() > 0) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            //get last order no
            int lastOrderNo = orderRepository.findLastOrderNoByDate(dateFormat.format(date),SecurityUtil.getCurrentUser().getBranch());

            //update existing order
            TOrder findone = orderRepository.findOne(index);
            if (findone != null) {
                findone.setTotalSub(order.getTotalSub());
                findone.setTotalAmount(order.getTotalAmount());
                orderRepository.save(findone);
                for (TOrderDetails details : order.gettOrderDetailssByIndexNo()) {
                    details.settOrderByTOrder(findone);
                    details.setDate(new Date());
                    orderDetailsRepository.save(details);
                }

                return findone;
            } else {
                //save new order
                order.setDate(new Date());
                order.setOrderNo(lastOrderNo);
                TOrder saveOrder = orderRepository.save(order);
                System.out.println(order.toString());
                for (TOrderDetails details : order.gettOrderDetailssByIndexNo()) {
                    details.settOrderByTOrder(saveOrder);
                    details.setDate(new Date());
                    orderDetailsRepository.save(details);
                }
                TTableReceive tTableReceive = new TTableReceive();
                tTableReceive.setmTable(tableIndex);
                tTableReceive.settOrder(saveOrder);
                tTableReceive.setStatus(true);
                tableRecieveRepository.save(tTableReceive);
                return saveOrder;
            }
        } else {
            return null;
        }

    }

    @Transactional
    public Boolean updateOrder(TOrderDetails orderDetail, int index, BigDecimal subTotal, BigDecimal totalAmount) {
        TOrderDetails orderDetails = orderDetailsRepository.findOne(orderDetail.getIndexNo());
        orderDetails.setQty(orderDetail.getQty());
        orderDetails.setDiscount(orderDetail.getDiscount());
        orderDetails.setValue(orderDetail.getValue());
        orderDetails.setDiscount(orderDetail.getDiscount());
        orderDetailsRepository.save(orderDetails);

        TOrder findOrder = orderRepository.findOne(index);
        findOrder.setTotalSub(subTotal);
        findOrder.setTotalAmount(totalAmount);

        orderRepository.save(findOrder);

        return true;
    }

    @Transactional
    public Boolean deleteOrder(int index, int index2, BigDecimal subTotal, BigDecimal totalAmount) {
        TOrder order = orderRepository.findOne(index);
        order.setTotalSub(subTotal);
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        orderDetailsRepository.delete(index2);
        return true;
    }

    //other funtions
    public List<MMainCategory> findAllMainCategory() {
        return mainCategoryRepository.findAll();
    }

    public List<MItem> findAllSubCategoryByMainCategory(int maincategory) {
        return itemRepository.findBymMainCategoryGroupBymSubCategory(maincategory);
    }

    public List<MProduct> findProductByItem(int index) {
        return productRepository.findBymItem(index);
    }

    public List<MItem> findBySubCategory(int index) {
        MSubCategory subCategory = subCategoryRepository.findOne(index);

        return itemRepository.findBymSubCategory(subCategory);
    }

    public MCustomer findByTelNo(String mobile) {
        System.out.println(mobile);
        return customerRepository.findByMobile(mobile);
    }

    public MCustomer saveCustomer(MCustomer customer) {
        MCustomer findOne = customerRepository.findByMobile(customer.getMobile());
        if (findOne != null) {
            findOne.setMobile(customer.getMobile());
            findOne.setName(customer.getName());
            return customerRepository.save(findOne);
        } else {
            return customerRepository.save(customer);
        }
    }

    public List<MTable> findAllTbales() {
        return tableRepository.findBymBranch(SecurityUtil.getCurrentUser().getBranch());
    }

    public TTableReceive findTableRecievedetails(int tableIndex){
       return tableRecieveRepository.findByStatusAndMTable(false,tableIndex);
    }
}
