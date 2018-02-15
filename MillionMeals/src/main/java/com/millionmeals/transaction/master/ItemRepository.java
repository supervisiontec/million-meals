package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MItem;
import com.millionmeals.transaction.master.model.MSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by my on 2018-02-02.
 */
public interface ItemRepository extends JpaRepository<MItem,Integer> {

   public List<MItem> findBymMainCategory(int maincategory);

   public List<MItem> findBymSubCategory(MSubCategory subCategory);

   @Query(value = "select * from \n" +
           "m_item \n" +
           "where \n" +
           "m_item.m_main_category=:maincategory\n" +
           "group by m_item.m_sub_category",nativeQuery = true)
   List <MItem> findBymMainCategoryGroupBymSubCategory(@Param("maincategory") int maincategory);
}
