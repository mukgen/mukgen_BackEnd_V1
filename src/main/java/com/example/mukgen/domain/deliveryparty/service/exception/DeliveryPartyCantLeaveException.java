package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyCantLeaveException extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyCantLeaveException();

    public DeliveryPartyCantLeaveException() {
        super(ErrorCode.DELIVERY_PARTY_WRITER_CANT_LEAVE);
    }
}
