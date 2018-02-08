package com.millionmeals.transaction.order;

import com.millionmeals.transaction.order.model.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-01.
 */
public interface OrderRepository extends JpaRepository<TOrder,Integer> {
}
