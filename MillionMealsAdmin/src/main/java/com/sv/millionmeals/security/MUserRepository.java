/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.security;


import com.sv.millionmeals.security.model.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author kavish manjitha
 */
public interface MUserRepository extends JpaRepository<MUser, Integer> {

    public List<MUser> findByUsername(String userName);
}
