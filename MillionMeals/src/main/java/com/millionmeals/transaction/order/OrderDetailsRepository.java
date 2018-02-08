package com.millionmeals.transaction.order;

import com.millionmeals.transaction.order.model.TOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-06.
 */
public interface OrderDetailsRepository extends JpaRepository<TOrderDetails,Integer> {
}
