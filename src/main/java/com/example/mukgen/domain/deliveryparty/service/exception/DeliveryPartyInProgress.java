package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyInProgress extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyInProgress();

    public DeliveryPartyInProgress() {
        super(ErrorCode.DELIVERY_PARTY_IN_PROGRESS);
    }
}
