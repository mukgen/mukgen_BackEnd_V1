package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyWriterMismatch extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyWriterMismatch();

    public DeliveryPartyWriterMismatch() {
        super(ErrorCode.DELIVERY_PARTY_WRITER_MISMATCH);
    }
}
