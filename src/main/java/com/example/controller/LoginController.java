package com.example.controller;

import com.example.advice.ResponseHelper;
import com.example.advice.WebResponse;
import com.example.entity.UserToken;
import com.example.model.LoginRequest;
import com.example.model.UserTokenResponse;
import com.example.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ResponseHelper responseHelper;

    @PostMapping(value = "/api/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserTokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserTokenResponse userToken = loginService.login(loginRequest);

                UserTokenResponse response = UserTokenResponse.builder()
                        .token(userToken.getToken())
                        .expired_at(userToken.getExpired_at())
                        .build();

        return responseHelper.ok(response, "Login successful");

    }
}
