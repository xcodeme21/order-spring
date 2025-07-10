package com.example.controller;

import com.example.advice.ResponseHelper;
import com.example.advice.WebResponse;
import com.example.entity.User;
import com.example.model.UserResponse;
import com.example.service.ProfileService;
import com.example.service.TokenService;
import com.example.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResponseHelper responseHelper;

    @Autowired
    private AuthUtil authUtil;

    @GetMapping(value = "/api/profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> profile(@RequestHeader("Authorization") String authHeader) {
        User user = authUtil.getUserFromAuth(authHeader);

        UserResponse response = profileService.get(user);
        return responseHelper.ok(response, "Successfully get profile");
    }

    @PostMapping(value = "/api/profile/logout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> logout(@RequestHeader("Authorization") String authHeader) {
        User user = authUtil.getUserFromAuth(authHeader);

        profileService.logout(user);
        return responseHelper.ok(null, "Successfully logged out");
    }
}
