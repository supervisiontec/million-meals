package com.millionmeals.transaction.order;

import com.millionmeals.transaction.order.model.TOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by my on 2018-02-01.
 */
public interface OrderRepository extends JpaRepository<TOrder,Integer> {

    @Query(value = "select ifnull(max(t_order.order_no)+1,1) as next_no\n" +
            "from t_order\n" +
            "where date(t_order.date) =:date and t_order.m_branch=:branch",nativeQuery = true)
    public int findLastOrderNoByDate(@Param("date") String date,@Param("branch") int branch);
}
