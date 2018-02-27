package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by my on 2018-02-26.
 */
public interface TableRepository extends JpaRepository<MTable, Integer> {

    List<MTable> findBymBranch(int branch);
}
