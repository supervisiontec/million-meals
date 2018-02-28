package com.sv.millionmeals.master.repository;

import com.sv.millionmeals.master.model.MUserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by my on 2018-02-27.
 */
public interface UserPrivilegeRepository extends JpaRepository<MUserPrivilege, Integer> {

    public List<MUserPrivilege> findBymUserType(Integer index);
}
