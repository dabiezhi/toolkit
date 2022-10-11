package com.bloom.bloomspringbootdemo;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.bloom.autoconfigure.kaptcha.KaptchaRender;
import com.bloom.bloomspringbootdemo.design.chain.CurryHandler;
import com.bloom.bloomspringbootdemo.design.chain.EsContext;
import com.bloom.bloomspringbootdemo.design.chain.HelloHandler;
import com.bloom.bloomspringbootdemo.design.chain.HuaHandler;
import com.bloom.bloomspringbootdemo.design.chain.Pipeline;
import com.bloom.bloomspringbootdemo.easyrules.Handler;
import com.bloom.bloomspringbootdemo.easyrules.TestAction;
import com.bloom.bloomspringbootdemo.easyrules.TsyAction;
import com.bloom.bloomspringbootdemo.javassist.demo1.DemoClient;
import com.bloom.bloomspringbootdemo.utils.ApolloUtils;
import com.bloom.bloomspringbootdemo.valid.BaseResult;
import com.bloom.bloomspringbootdemo.javassist.demo2.DemoFacade;
import com.bloom.bloomspringbootdemo.valid.User;

import org.hibernate.validator.constraints.NotBlank;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
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
    private DemoClient    demoClient;
    @Resource
    private Handler       handler;
    @Resource
    private TestAction testAction;
    @Resource
    private TsyAction tsyAction;
    @Resource
    private HelloHandler helloHandler;
    @Resource
    private CurryHandler curryHandler;
    @Resource
    private HuaHandler huaHandler;

    @RequestMapping(value = "/hello")
    public String hello(@RequestBody User user) {

        Rule rule = new RuleBuilder().name("weather rule")
            .description("if it rains then take an umbrella").priority(3).when(facts -> true)
            .then(testAction).then(tsyAction).build();

        Rules rules = new Rules();
        rules.register(rule);

        //创建规则执行引擎，并执行规则
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.put("person", "12");
        rulesEngine.fire(rules, facts);

        Pipeline<EsContext> pipeline = new Pipeline<>(helloHandler).addHandler(huaHandler).addHandler(curryHandler);
        System.out.println(pipeline.execute(new EsContext()));

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

    public String tt(String userId) {
        return userId + "cuchuan";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
