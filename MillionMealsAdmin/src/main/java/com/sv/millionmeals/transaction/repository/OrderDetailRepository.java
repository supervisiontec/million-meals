/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.transaction.repository;

import com.sv.millionmeals.transaction.model.TOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kalum
 */
public interface OrderDetailRepository extends JpaRepository<TOrderDetails, Integer>{
    
}
