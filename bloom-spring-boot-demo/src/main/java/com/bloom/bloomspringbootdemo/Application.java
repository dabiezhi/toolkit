package com.bloom.bloomspringbootdemo;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.bloom.autoconfigure.kaptcha.KaptchaRender;
import com.bloom.bloomspringbootdemo.javassist.demo1.DemoClient;
import com.bloom.bloomspringbootdemo.valid.BaseResult;
import com.bloom.bloomspringbootdemo.javassist.demo2.DemoFacade;
import com.bloom.bloomspringbootdemo.valid.User;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Validated
public class Application {

    @Resource
    private KaptchaRender kaptchaRender;
    @Resource
    private DemoFacade    demoFacade;
    @Resource
    private DemoClient demoClient;

    @RequestMapping(value = "/hello")
    public String hello(@RequestBody @Valid User user) {
        return kaptchaRender.render();
    }

    @RequestMapping(value = "/get")
    public BaseResult<String> demo(@RequestParam(value = "userId") @NotBlank(message = "userId不能为空") String userId) {
        return BaseResult.success(demoClient.demo(userId));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
