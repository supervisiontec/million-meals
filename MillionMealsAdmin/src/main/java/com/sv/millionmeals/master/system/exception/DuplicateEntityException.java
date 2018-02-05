/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.millionmeals.master.system.exception;

import com.sv.millionmeals.master.system.exception.handler.SystemErrorResponse;
import com.sv.millionmeals.master.system.exception.handler.SystemException;



/**
 *
 * @author kalum
 */
@SystemErrorResponse(4001)
public class DuplicateEntityException extends SystemException{
    public DuplicateEntityException() {
    }

    public DuplicateEntityException(String message) {
        super(message);
    }
}
