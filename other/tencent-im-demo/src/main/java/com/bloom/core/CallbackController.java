package com.bloom.core;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author taosy
 * Created by on 2022-03-18 下午1:53
 */
@RestController
public class CallbackController {
    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping("/callback")
    public String callback(@RequestBody Msg msg) {
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(JSON.toJSONString(msg));
        return msg.toString();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.parse("2022-04-01").getTime()/1000);
    }
}