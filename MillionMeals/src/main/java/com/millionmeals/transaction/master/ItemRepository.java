package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MItem;
import com.millionmeals.transaction.master.model.MSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by my on 2018-02-02.
 */
public interface ItemRepository extends JpaRepository<MItem,Integer> {

   public List<MItem> findBymMainCategory(int maincategory);

   public List<MItem> findBymSubCategory(MSubCategory subCategory);
}
