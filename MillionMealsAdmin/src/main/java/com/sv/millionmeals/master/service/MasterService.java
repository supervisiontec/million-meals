/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.master.service;

import com.sv.millionmeals.master.model.MCategory;
import com.sv.millionmeals.master.model.MCustomer;
import com.sv.millionmeals.master.model.MItem;
import com.sv.millionmeals.master.model.MMainCategory;
import com.sv.millionmeals.master.model.MProduct;
import com.sv.millionmeals.master.model.MSubCategory;
import com.sv.millionmeals.master.model.MTable;
import com.sv.millionmeals.master.model.MUnit;
import com.sv.millionmeals.master.repository.CategoryRepository;
import com.sv.millionmeals.master.repository.CustomerRepository;
import com.sv.millionmeals.master.repository.ItemRepository;
import com.sv.millionmeals.master.repository.MainCategoryRepository;
import com.sv.millionmeals.master.repository.ProductRepository;
import com.sv.millionmeals.master.repository.SubCategoryRepository;
import com.sv.millionmeals.master.repository.TableRepository;
import com.sv.millionmeals.master.repository.UnitRepository;
import com.sv.millionmeals.master.system.exception.DuplicateEntityException;
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
        MSubCategory findByName = subCategoryRepository.findByName(subCategory.getName());

        if (findByName == null) {
            return subCategoryRepository.save(subCategory);
        } else {
            throw new DuplicateEntityException("Duplicate Sub Category");
        }
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
        MMainCategory findByName = mainCategoryRepository.findByName(mainCategory.getName());

        if (findByName == null) {
            return mainCategoryRepository.save(mainCategory);
        } else {
            throw new DuplicateEntityException("Duplicate Main Category");
        }

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
        MTable findByName = tableRepository.findByName(table.getName());
        
        if (findByName == null) {
        return tableRepository. save(table);
        } else {
            throw new DuplicateEntityException("Duplicate Table");
        }
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
        MUnit findByName = unitRepository.findByName(unit.getName());

        if (findByName == null) {
            return unitRepository.save(unit);
        } else {
            throw new DuplicateEntityException("Duplicate Unit");
        }
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
        MItem findByName = itemRepository.findByName(item.getName());

        if (findByName == null) {
            return itemRepository.save(item);
        } else {
            throw new DuplicateEntityException("Duplicate Item");
        }
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
        MProduct findByName = productRepository.findByItemAndUnit(product.getItem(), product.getUnit());

        if (findByName == null) {
            return productRepository.save(product);
        } else {
            throw new DuplicateEntityException("Duplicate Item And Unit");
        }
    }

    public void deleteProduct(Integer indexNo) {
        try {
            productRepository.delete(indexNo);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this product because there are details in other transaction");
        }
    }
}
