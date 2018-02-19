package com.millionmeals.transaction.order;

import com.millionmeals.transaction.master.model.MCustomer;
import com.millionmeals.transaction.master.model.MItem;
import com.millionmeals.transaction.master.model.MMainCategory;
import com.millionmeals.transaction.master.model.MProduct;
import com.millionmeals.transaction.order.model.TOrder;
import com.millionmeals.transaction.order.model.TOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
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

    @RequestMapping(path = "/save-order/{index}",method = RequestMethod.POST)
    public TOrder saveOrder (@RequestBody TOrder order,@PathVariable("index") int index){
        return orderService.saveOrder(order,index);
    }

    @RequestMapping(path = "/update-order/{index}/{subTotal}/{totalAmount}",method = RequestMethod.POST)
    public Boolean saveOrder (@RequestBody TOrderDetails order, @PathVariable("index") int index, @PathVariable("subTotal")BigDecimal subTotal,@PathVariable("totalAmount")BigDecimal totalAmount){
        return orderService.updateOrder(order,index,subTotal,totalAmount);
    };

    @RequestMapping(path = "/delete-order/{index}/{index2}/{subTotal}/{totalAmount}",method = RequestMethod.DELETE)
    public Boolean deleteOrder(@PathVariable("index") int index,@PathVariable("index2") int index2, @PathVariable("subTotal")BigDecimal subTotal,@PathVariable("totalAmount")BigDecimal totalAmount){
        return orderService.deleteOrder(index,index2,subTotal,totalAmount);
    };

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

    @RequestMapping(path = "/find-employee-mobile/{mobile}",method = RequestMethod.GET)
    public MCustomer findAllProduct(@PathVariable("mobile") String mobile) {
        return orderService.findByTelNo(mobile);
    }

    @RequestMapping(path = "/save-customer",method = RequestMethod.POST)
    public MCustomer saveCustomer(@RequestBody MCustomer customer) {
        return orderService.saveCustomer(customer);
    }

    @RequestMapping(value = "/food-image/{path}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void loadFoodImage(@PathVariable("path") String path, HttpServletResponse response) {
        System.out.println("sssssssssssssssssssssssssssssss" + path);
//        File file = new File("./files/" + path + ".jpg");
        File file = new File("./files/" + path +".jpg");

        System.out.println(file.getAbsolutePath());
        try {
            OutputStream outputStream = response.getOutputStream();

            FileInputStream inputStream = new FileInputStream(file);
            byte[] read = new byte[8196];
            int c = 0;
            while ((c = inputStream.read(read, 0, read.length)) > 0) {
                outputStream.write(read, 0, c);
                outputStream.flush();
            }
            inputStream.close();
            outputStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
