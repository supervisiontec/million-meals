//package com.sv.millionmeals.reports;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by my on 2018-02-21.
// */
//@CrossOrigin
//@RestController
//@RequestMapping(path = "/api/v1/millionMeals/reports")
//public class ReportController {
//
//    @Autowired
//    private ReportService reportService;
//
//    @RequestMapping(value = "/product-wise/{fromDate}/{toDate}/{branch}/{mainCategory}/{subCategory}/{type}", method = RequestMethod.GET)
//    public List<Object> productWise(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,@PathVariable("branch") String branch,@PathVariable("mainCategory") String mainCategory,@PathVariable("subCategory") String subCategory,@PathVariable("type") String type) {
//        return reportService.productWise(fromDate, toDate,branch,mainCategory,subCategory,type);
//    }
//
//    @RequestMapping(value = "/invoice-summery/{fromDate}/{toDate}/{branch}", method = RequestMethod.GET)
//    public List<Object> invoiceSummery(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate,@PathVariable("branch") String branch) {
//        return reportService.invoiceSummery(fromDate, toDate,branch);
//    }
//}
