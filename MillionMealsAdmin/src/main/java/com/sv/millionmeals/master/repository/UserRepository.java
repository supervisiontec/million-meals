package com.sv.millionmeals.master.repository;

import com.sv.millionmeals.master.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by my on 2018-02-27.
 */
public interface UserRepository extends JpaRepository<MUser,Integer> {
}
