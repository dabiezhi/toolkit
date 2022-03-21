package com.bloom.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author taosy
 * Created by on 2022-03-17 上午11:44
 */
@RestController
public class UserSigController {

    @Resource
    private UserSigService userSigService;

    @GetMapping("getUserSig")
    public String getUserSig(String userId) {
        return userSigService.generateUserSig(userId);
    }

}