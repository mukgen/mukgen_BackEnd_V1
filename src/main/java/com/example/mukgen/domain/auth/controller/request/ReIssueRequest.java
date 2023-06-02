package com.example.mukgen.domain.auth.controller.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReIssueRequest {

    private String refreshToken;
}
