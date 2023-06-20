package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyFullException extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyFullException();

    public DeliveryPartyFullException() {
        super(ErrorCode.DELIVERY_PARTY_FULL);
    }
}
