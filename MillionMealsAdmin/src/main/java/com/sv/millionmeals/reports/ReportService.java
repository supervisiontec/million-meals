package com.sv.millionmeals.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by my on 2018-02-21.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;


    public List<Object> productWise(String fromDate, String toDate, String branch, String mainCategory, String subCategory, String type) {
       return reportRepository.productWise(fromDate,toDate,branch,mainCategory,subCategory,type);
    }

    public List<Object> invoiceSummery(String fromDate, String toDate, String branch){
        return reportRepository.invoiceSummery(fromDate,toDate,branch);
    }

}
