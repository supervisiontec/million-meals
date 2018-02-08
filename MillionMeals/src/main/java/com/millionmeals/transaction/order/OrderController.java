package com.millionmeals.transaction.order;

import com.millionmeals.transaction.master.model.MItem;
import com.millionmeals.transaction.master.model.MMainCategory;
import com.millionmeals.transaction.master.model.MProduct;
import com.millionmeals.transaction.order.model.TOrder;
import com.millionmeals.transaction.order.model.TOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by my on 2018-02-01.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/restaurant/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TOrder> getAllOrders(){
       return orderService.getAllOrder();
    }

    @RequestMapping(path = "/save-order",method = RequestMethod.POST)
    public TOrder saveOrder (@RequestBody TOrder order){
        return orderService.saveOrder(order);
    }

    // other funtions
    @RequestMapping(path = "/all-maincategory",method = RequestMethod.GET)
    public List<MMainCategory> findAllMainCategory(){
        return orderService.findAllMainCategory();
    }

    @RequestMapping(path = "/all-subcategory/{index}",method = RequestMethod.GET)
    public List<MItem> findAllSubCategoryByMainCategory(@PathVariable("index") int index){
        return orderService.findAllSubCategoryByMainCategory(index);
    }

    @RequestMapping(path = "/all-items/{index}",method = RequestMethod.GET)
    public List<MItem> findBySubCategory(@PathVariable("index") int index){
        return orderService.findBySubCategory(index);
    }

    @RequestMapping(path = "/find-product/{index}",method = RequestMethod.GET)
    public List<MProduct> findAllProduct(@PathVariable("index") int index) {
        return orderService.findProductByItem(index);
    }
}
