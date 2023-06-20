package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyWriterMismatchException extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyWriterMismatchException();

    public DeliveryPartyWriterMismatchException() {
        super(ErrorCode.DELIVERY_PARTY_WRITER_MISMATCH);
    }
}
