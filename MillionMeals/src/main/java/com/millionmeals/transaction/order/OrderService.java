package com.millionmeals.transaction.order;

import com.millionmeals.transaction.master.ItemRepository;
import com.millionmeals.transaction.master.MainCategoryRepository;
import com.millionmeals.transaction.master.ProductRepository;
import com.millionmeals.transaction.master.SubCategoryRepository;
import com.millionmeals.transaction.master.model.MItem;
import com.millionmeals.transaction.master.model.MMainCategory;
import com.millionmeals.transaction.master.model.MProduct;
import com.millionmeals.transaction.master.model.MSubCategory;
import com.millionmeals.transaction.order.model.TOrder;
import com.millionmeals.transaction.order.model.TOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by my on 2018-02-01.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class OrderService {

    private static int branch = 1;

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


    public List<TOrder> getAllOrder() {
        return orderRepository.findAll();
    }

    @Transactional
    public  TOrder saveOrder(TOrder order) {
//       TOrder saveOrder =  orderRepository.save(order);
//        System.out.println(order.toString());
//        for (TOrderDetails details : order.gettOrderDetailssByIndexNo()) {
//            details.settOrderByTOrder(saveOrder.getIndexNo());
//            System.out.println(details.toString());
//            return orderDetailsRepository.save(details);
//        }

        TOrder saveOrder = orderRepository.save(order);
        System.out.println(order.toString());
        for (TOrderDetails details : order.gettOrderDetailssByIndexNo()) {
            details.settOrderByTOrder(saveOrder);
            orderDetailsRepository.save(details);
        }

        return saveOrder;
    }

    //other funtions
    public List<MMainCategory> findAllMainCategory() {
        return mainCategoryRepository.findByMBranch(branch);
    }

    public List<MItem> findAllSubCategoryByMainCategory(int maincategory) {
        return itemRepository.findBymMainCategory(maincategory);
    }

    public List<MProduct> findProductByItem(int index) {
        return productRepository.findBymItem(index);
    }

    public List<MItem> findBySubCategory(int index) {
        MSubCategory subCategory = subCategoryRepository.findOne(index);

        return itemRepository.findBymSubCategory(subCategory);
    }
}
