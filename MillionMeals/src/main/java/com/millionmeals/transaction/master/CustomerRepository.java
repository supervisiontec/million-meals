package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-02.
 */
public interface CustomerRepository extends JpaRepository<MCustomer,Integer>{
}
