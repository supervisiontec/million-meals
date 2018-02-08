package com.millionmeals.transaction.master;

import com.millionmeals.transaction.master.model.MBranch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-02.
 */
public interface BranchRepository extends JpaRepository<MBranch,Integer>{
}
