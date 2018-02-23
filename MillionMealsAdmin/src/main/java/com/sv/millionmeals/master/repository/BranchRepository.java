package com.sv.millionmeals.master.repository;

import com.sv.millionmeals.master.model.MBranch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-21.
 */
public interface BranchRepository extends JpaRepository<MBranch,Integer> {
}
