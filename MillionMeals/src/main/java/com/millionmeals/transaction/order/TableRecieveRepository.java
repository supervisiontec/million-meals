package com.millionmeals.transaction.order;

import com.millionmeals.transaction.order.model.TTableReceive;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-26.
 */
public interface TableRecieveRepository extends JpaRepository<TTableReceive,Integer> {

    public TTableReceive findByStatusAndMTable(boolean b, int tableIndex);
}
