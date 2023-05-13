package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyNotFoundException();
    public DeliveryPartyNotFoundException() {
        super(ErrorCode.DELIVERY_PARTY_NOT_FOUND);
    }
}
