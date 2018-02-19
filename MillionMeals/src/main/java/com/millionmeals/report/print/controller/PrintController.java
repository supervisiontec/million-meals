package com.millionmeals.report.print.controller;///*
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.millionmeals.report.print.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kavish manjitha
 */
@RestController
@CrossOrigin
@RequestMapping("/api/care-point/print-service")
public class PrintController {

    @Autowired
    private PrintService printService;

    @RequestMapping(value = "/print-incoice/{order}", method = RequestMethod.GET)
    public Integer printEstimate(@PathVariable Integer order){
        return printService.printInvoice(order);
    }
}
