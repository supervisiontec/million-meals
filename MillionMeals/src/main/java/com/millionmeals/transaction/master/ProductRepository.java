package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by my on 2018-02-02.
 */
public interface ProductRepository extends JpaRepository<MProduct,Integer>{
  public  List<MProduct> findBymItem(int index);
}
