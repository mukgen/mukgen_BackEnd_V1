package com.example.mukgen.domain.deliveryparty.service.exception;

import com.example.mukgen.global.error.exception.BusinessException;
import com.example.mukgen.global.error.exception.ErrorCode;

public class DeliveryPartyMeetTimeException extends BusinessException {

    public static final BusinessException EXCEPTION = new DeliveryPartyMeetTimeException();

    public DeliveryPartyMeetTimeException() {

        super(ErrorCode.BAD_REQUEST);
    }
}
