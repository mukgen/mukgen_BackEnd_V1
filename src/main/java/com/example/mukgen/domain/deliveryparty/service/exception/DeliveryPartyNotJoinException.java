package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyNotJoinException extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyNotJoinException();

    public DeliveryPartyNotJoinException() {
        super(ErrorCode.DELIVERY_PARTY_NOT_JOIN);
    }
}
