package com.example.mukgen.domain.user.service;

import com.example.mukgen.domain.user.controller.response.UserInfoResponse;
import com.example.mukgen.domain.user.controller.response.UserProfileResponse;
import com.example.mukgen.domain.user.entity.User;
import com.example.mukgen.domain.user.repository.UserRepository;
import com.example.mukgen.domain.user.service.exception.UserNotFoundException;
import com.example.mukgen.infra.s3.service.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;

    private final S3Util s3Util;

    private final UserRepository userRepository;

    public UserInfoResponse findUser(){

        User user = userFacade.currentUser();

        return UserInfoResponse.of(user);

    }

    public String profileUpload(MultipartFile multipartFile) throws IOException {

        User user = userFacade.currentUser();

        String profileUrl = user.getProfileUrl();

        if(!profileUrl.isEmpty()){
            s3Util.deleteFile(profileUrl.split("/")[3]);
        }

        profileUrl = s3Util.upload(multipartFile);

        user.modifyProfileUrl(profileUrl);

        return profileUrl;
    }

}
