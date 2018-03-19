/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.master.service;

import com.sv.millionmeals.master.model.*;
import com.sv.millionmeals.master.model.pojo.Ingredians;
import com.sv.millionmeals.master.repository.*;
import com.sv.millionmeals.master.system.exception.DuplicateEntityException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class MasterService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private MainCategoryRepository mainCategoryRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Autowired
    private IngrediansRepository ingrediansRepository;

    //constants
    public static final String TYPE_ROW_ITEM = "row_item";
    public static final String TYPE_ROW_ITEM_AND_FINAL = "finish/row_item";

    public List<MCustomer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public MCustomer saveCustomer(MCustomer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer indexNo) {
        try {
            customerRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this customer because there are details in other transaction");
        }
    }

    public List<MCategory> findAllCategory() {
        return categoryRepository.findAll();
    }

    public MCategory saveCategory(MCategory category) {
        MCategory findByName = categoryRepository.findByName(category.getName());

        if (findByName == null) {
            return categoryRepository.save(category);
        } else {
            throw new DuplicateEntityException("Duplicate Category");
        }
    }

    public void deleteCategory(Integer indexNo) {
        try {
            categoryRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this category because there are details in other transaction");
        }

    }

    public List<MSubCategory> findAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    public MSubCategory saveSubCategory(MSubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    public void deleteSubCategory(Integer indexNo) {
        try {
            subCategoryRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this sub category because there are details in other transaction");
        }
    }

    public List<MMainCategory> findAllMainCategory() {
        return mainCategoryRepository.findAll();
    }

    public MMainCategory saveMainCategory(MMainCategory mainCategory) {
        return mainCategoryRepository.save(mainCategory);
    }

    public void deleteMainCategory(Integer indexNo) {
        try {
            mainCategoryRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this main category because there are details in other transaction");
        }
    }

    public List<MTable> findAllTable() {
        return tableRepository.findAll();
    }

    public MTable saveTable(MTable table) {
        return tableRepository.save(table);
    }

    public void deleteTable(Integer indexNo) {
        try {
            tableRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this table because there are details in other transaction");
        }
    }

    public List<MUnit> getAllUnit() {
        return unitRepository.findAll();
    }

    public MUnit saveUnit(MUnit unit) {
        return unitRepository.save(unit);
    }

    public void deleteUnit(Integer indexNo) {
        try {
            unitRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this unit because there are details in other transaction");
        }
    }

    public List<MItem> getAllItem() {
        return itemRepository.findAll();
    }

    public MItem saveItem(MItem item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Integer indexNo) {
        try {
            itemRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this item because there are details in other transaction");
        }
    }

    public List<MProduct> getAllProduct() {
        return productRepository.findAll();
    }

    public MProduct saveProduct(MProduct product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer indexNo) {
        try {
            productRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this product because there are details in other transaction");
        }
    }

    public List<MBranch> findAllBranch() {
        return branchRepository.findAll();
    }

    public List<MUserPrivilege> getAllUserPrivileges(Integer index) {
        return userPrivilegeRepository.findBymUserType(index);
    }

    public List<MUserType> getAllUserType() {
        return userTypeRepository.findAll();
    }

    @Transactional
    public Boolean userApprovel(Integer index, Boolean status) {
        MUserPrivilege userPrivilege = userPrivilegeRepository.findOne(index);
        System.out.println(userPrivilege.toString());
        userPrivilege.setStatus(status);
        userPrivilegeRepository.save(userPrivilege);
        return true;
    }

    public List<MItem> getAllProductByTpeNotRowItem() {
        return itemRepository.findByTypeNotIn(TYPE_ROW_ITEM);
    }

    public List<MItem> getAllProducByTypeRowItem() {
        return itemRepository.findByTypeOrType(TYPE_ROW_ITEM, TYPE_ROW_ITEM_AND_FINAL);
    }

    public List<Ingredians> getAllItemObject() {

        List<Ingredians> list = new ArrayList<>();
        List<Object[]> itemObjectList = itemRepository.getAllItemObject();
        for (int i = 0; i < itemObjectList.size(); i++) {
            Ingredians ingredians = new Ingredians();
            ingredians.setIndexNo((Integer) itemObjectList.get(i)[0]);
            ingredians.setItem((String) itemObjectList.get(i)[1]);
            ingredians.setUnit((String) itemObjectList.get(i)[2]);
            ingredians.setPrice((BigDecimal) itemObjectList.get(i)[3]);
            list.add(ingredians);
        }
        return list;
    }

    public MIngredians saveIngredians(List<MIngredians> mIngredianses) {

        MIngredians ingredians = new MIngredians();
        
        for (int i = 0; i < mIngredianses.size(); i++) {
            ingredians = mIngredianses.get(i);
            MIngredians save = ingrediansRepository.save(ingredians);
        }
        return ingredians;
    }

    public List<MIngredians> findIngrediansByProduct(Integer indexNo) {
        return ingrediansRepository.findByProduct(indexNo);
    }

    public void deleteIngrediansByIndexNo(Integer indexNo) {
        ingrediansRepository.delete(indexNo);
    }
}
