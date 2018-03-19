/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.master.repository;

import com.sv.millionmeals.master.model.MItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author kalum
 */
public interface ItemRepository extends JpaRepository<MItem, Integer> {

    public MItem findByName(String name);

    public List<MItem> findByType(String TYPE_ROW_ITEM);

    public List<MItem> findByTypeNotIn(String TYPE_ROW_ITEM);

//    public List<MProduct> findByNotType(String TYPE_ROW_ITEM);
    @Query(value = "select \n"
            + "m_product.index_no,\n"
            + "m_item.name as item,\n"
            + "m_unit.name as unit,\n"
            + "m_product.price\n"
            + "from m_product\n"
            + "left join m_unit on m_unit.index_no = m_product.m_unit\n"
            + "left join m_item on m_item.index_no = m_product.m_item", nativeQuery = true)
    public List<Object[]> getAllItemObject();
//    @Query(value = "select m_product.index_no,\n"
//            + "CONCAT(m_item.name,\" - \",m_unit.name) as name from\n"
//            + " m_product\n"
//            + "left join\n"
//            + "m_unit on m_unit.index_no=m_product.m_unit\n"
//            + "left join\n"
//            + "m_item on m_item.index_no =m_product.m_item", nativeQuery = true)
//    public List<Object> getAllItemObject();
    
    public List<MItem> findByTypeOrType(String TYPE_ROW_ITEM, String TYPE_ROW_ITEM_AND_FINAL);

}
