package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyAlreadyExists extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyAlreadyExists();

    public DeliveryPartyAlreadyExists() {
        super(ErrorCode.DELIVERY_PARTY_ALREADY_EXISTS);
    }
}
