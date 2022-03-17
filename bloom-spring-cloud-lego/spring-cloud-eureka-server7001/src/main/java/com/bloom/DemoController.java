/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taosy
 * Created by on 2022-02-13 下午10:24
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public String demo() {
        return "我是demo";
    }
}