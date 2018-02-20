package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MMainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by my on 2018-02-02.
 */
public interface MainCategoryRepository extends JpaRepository<MMainCategory,Integer> {

//   public List<MMainCategory> findByMBranch(int branch);
}