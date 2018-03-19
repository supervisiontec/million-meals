/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.master.repository;

import com.sv.millionmeals.master.model.MIngredians;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kalum
 */
public interface IngrediansRepository extends JpaRepository<MIngredians, Integer>{

    public List<MIngredians> findByProduct(Integer indexNo);
    
}
