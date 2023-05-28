package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyFull extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyFull();

    public DeliveryPartyFull() {
        super(ErrorCode.DELIVERY_PARTY_FULL);
    }
}
