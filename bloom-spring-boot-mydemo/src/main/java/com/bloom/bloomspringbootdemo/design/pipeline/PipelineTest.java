package com.bloom.bloomspringbootdemo.design.pipeline;

/**
 * @author curry
 * Created by on 2022-08-18 下午6:05
 */
public class PipelineTest {

    public static void main(String[] args) {
        Pipeline<String, Integer> pipeline = new Pipeline<>(new RemoveAlphabetsHandler())
            .addHandler(new RemoveDigitsHandler()).addHandler(new ConvertToCharArrayHandler());
        System.out.println(pipeline.execute("GoYankees123!"));
    }
}