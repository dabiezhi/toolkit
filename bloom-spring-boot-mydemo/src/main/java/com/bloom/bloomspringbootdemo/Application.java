package com.bloom.bloomspringbootdemo;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.bloom.autoconfigure.kaptcha.KaptchaRender;
import com.bloom.bloomspringbootdemo.javassist.demo1.DemoClient;
import com.bloom.bloomspringbootdemo.utils.ApolloUtils;
import com.bloom.bloomspringbootdemo.valid.BaseResult;
import com.bloom.bloomspringbootdemo.javassist.demo2.DemoFacade;
import com.bloom.bloomspringbootdemo.valid.User;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Validated
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {

    @Resource
    private KaptchaRender kaptchaRender;
    @Resource
    private DemoFacade    demoFacade;
    @Resource
    private DemoClient demoClient;

    @RequestMapping(value = "/hello")
    public String hello(@RequestBody @Valid User user) {
        System.out.println(ApolloUtils.authorLabelKey);
        return kaptchaRender.render();
    }

    @RequestMapping(value = "/get")
    public BaseResult<String> demo(@RequestParam(value = "userId") @NotBlank(message = "userId不能为空") String userId) {
        return BaseResult.success(demoClient.demo(userId));
    }

    @RequestMapping(value = "/arthas")
    public String arthas(@RequestParam(value = "userId") String userId) {
        return tt(userId);
    }

    public String tt(String userId){
        return userId+"cuchuan";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
