package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-05.
 */
public interface SubCategoryRepository extends JpaRepository<MSubCategory,Integer> {
}
