/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.security;


import com.sv.millionmeals.security.model.MBranch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kavish manjitha
 */
public interface MBranchRepository extends JpaRepository<MBranch, Integer>{
    
}
