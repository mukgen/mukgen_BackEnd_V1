package com.example.mukgen.domain.user.service;

import com.example.mukgen.domain.user.controller.response.UserInfoResponse;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.infra.s3.service.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserFacade userFacade;

    private final S3Util s3Util;

    private final UserRepository userRepository;

    public UserInfoResponse findUser(){

        User user = userFacade.currentUser();

        return UserInfoResponse.of(user);

    }

    @Transactional
    public String profileUpload(MultipartFile multipartFile) throws IOException {

        User user = userFacade.currentUser();

        String profileUrl = "";

        if(user.getProfileUrl() != null && !user.getProfileUrl().isEmpty()){
            profileUrl = user.getProfileUrl();
            s3Util.deleteFile(profileUrl.split("/")[3]);
        }

        profileUrl = s3Util.upload(multipartFile);

        user.modifyProfileUrl(profileUrl);

        return profileUrl;
    }

}
