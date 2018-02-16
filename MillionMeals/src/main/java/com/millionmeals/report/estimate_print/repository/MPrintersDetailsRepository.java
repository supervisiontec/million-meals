/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.millionmeals.report.estimate_print.repository;


import java.util.List;

import com.millionmeals.report.estimate_print.model.MPrintersDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kavish manjitha
 */
@Repository
public interface MPrintersDetailsRepository extends JpaRepository<MPrintersDetails, Integer> {

    public MPrintersDetails findByBranchIndexNo(Integer branch);

}
