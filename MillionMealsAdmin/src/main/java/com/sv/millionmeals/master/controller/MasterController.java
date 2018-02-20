/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.master.controller;

import com.sv.millionmeals.master.model.MCategory;
import com.sv.millionmeals.master.model.MCustomer;
import com.sv.millionmeals.master.model.MItem;
import com.sv.millionmeals.master.model.MMainCategory;
import com.sv.millionmeals.master.model.MProduct;
import com.sv.millionmeals.master.model.MSubCategory;
import com.sv.millionmeals.master.model.MTable;
import com.sv.millionmeals.master.model.MUnit;
import com.sv.millionmeals.master.service.MasterService;
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
@RequestMapping("/api/v1/millionMeals/master")
public class MasterController {
    
    @Autowired
    private MasterService masterService;
    
    // Get All Customers
    @RequestMapping(value = "/get-all-customer", method = RequestMethod.GET)
    public List<MCustomer> getAllCustomer(){
        return masterService.findAllCustomer();
    }
    // Save Customers
    @RequestMapping(value = "/save-customer", method = RequestMethod.POST)
    public MCustomer saveCustomer(@RequestBody MCustomer customer){
        return masterService.saveCustomer(customer);
    }
    // Delete Customer
    @RequestMapping(value = "/delete-customer/{indexNo}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Integer indexNo){
        masterService.deleteCustomer(indexNo);
    }
    // Get All Category
    @RequestMapping(value = "/get-all-category", method = RequestMethod.GET)
    public List<MCategory> getAllCategory(){
        return masterService.findAllCategory();
    }
    // Save Category
    @RequestMapping(value = "/save-category", method = RequestMethod.POST)
    public MCategory saveCategory(@RequestBody MCategory category){
        return masterService.saveCategory(category);
    }
    // Delete Category
    @RequestMapping(value = "/delete-category/{indexNo}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable Integer indexNo){
        masterService.deleteCategory(indexNo);
    }
    // Get All SubCategory
    @RequestMapping(value = "/get-all-subCategory", method = RequestMethod.GET)
    public List<MSubCategory> getAllSubCategory(){
        return masterService.findAllSubCategory();
    }
    // Save SubCategory
    @RequestMapping(value = "/save-subCategory", method = RequestMethod.POST)
    public MSubCategory saveSubCategory(@RequestBody MSubCategory subCategory){
        return masterService.saveSubCategory(subCategory);
    }
    // Delete SubCategory
    @RequestMapping(value = "/delete-subCategory/{indexNo}", method = RequestMethod.DELETE)
    public void deleteSubCategory(@PathVariable Integer indexNo){
        masterService.deleteSubCategory(indexNo);
    }
    // Get All MainCategory
    @RequestMapping(value = "/get-all-mainCategory", method = RequestMethod.GET)
    public List<MMainCategory> getAllMainCategory(){
        return masterService.findAllMainCategory();
    }
    // Save MainCategory
    @RequestMapping(value = "/save-mainCategory", method = RequestMethod.POST)
    public MMainCategory saveMainCategory(@RequestBody MMainCategory mainCategory){
        return masterService.saveMainCategory(mainCategory);
    }
    // Delete MainCategory
    @RequestMapping(value = "/delete-mainCategory/{indexNo}", method = RequestMethod.DELETE)
    public void deleteMainCategory(@PathVariable Integer indexNo){
        masterService.deleteMainCategory(indexNo);
    }
    // Get All Table
    @RequestMapping(value = "/get-all-table", method = RequestMethod.GET)
    public List<MTable> getAllTable(){
        return masterService.findAllTable();
    }
    // Save Table
    @RequestMapping(value = "/save-table", method = RequestMethod.POST)
    public MTable saveTable(@RequestBody MTable table){
        table.setMBranch(1);
        return masterService.saveTable(table);
    }
    // Delete Table
    @RequestMapping(value = "/delete-table/{indexNo}", method = RequestMethod.DELETE)
    public void deleteTable(@PathVariable Integer indexNo){
        masterService.deleteTable(indexNo);
    }
    // Get All Unit
    @RequestMapping(value = "/get-all-unit", method = RequestMethod.GET)
    public List<MUnit> getAllUnit(){
        return masterService.getAllUnit();
    }
    // Save Unit
    @RequestMapping(value = "/save-unit", method = RequestMethod.POST)
    public MUnit saveUnit(@RequestBody MUnit unit){
        return masterService.saveUnit(unit);
    }
    // Delete Unit
    @RequestMapping(value = "/delete-unit/{indexNo}", method = RequestMethod.DELETE)
    public void deleteUnit(@PathVariable Integer indexNo){
        masterService.deleteUnit(indexNo);
    }
    // Get All Item
    @RequestMapping(value = "/get-all-item", method = RequestMethod.GET)
    public List<MItem> getAllItem(){
        return masterService.getAllItem();
    }
    // Save Item
    @RequestMapping(value = "/save-item", method = RequestMethod.POST)
    public MItem saveItem(@RequestBody MItem item){
        return masterService.saveItem(item);
    }
    // Delete Item
    @RequestMapping(value = "/delete-item/{indexNo}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable Integer indexNo){
        masterService.deleteItem(indexNo);
    }
    // Get All Product
    @RequestMapping(value = "/get-all-product", method = RequestMethod.GET)
    public List<MProduct> getAllProduct(){
        return masterService.getAllProduct();
    }
    // Save Product
    @RequestMapping(value = "/save-product", method = RequestMethod.POST)
    public MProduct saveProduct(@RequestBody MProduct product){
        return masterService.saveProduct(product);
    }
    // Delete Product
    @RequestMapping(value = "/delete-product/{indexNo}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Integer indexNo){
        masterService.deleteProduct(indexNo);
    }
}
