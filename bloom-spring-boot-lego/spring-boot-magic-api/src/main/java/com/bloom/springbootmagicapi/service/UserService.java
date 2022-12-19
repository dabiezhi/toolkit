package com.bloom.springbootmagicapi.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author curry
 * Created by on 2022-11-07 下午4:42
 */
@Service(value = "userService")
public class UserService {

    public List<String> userList() {
        List<String> list = new ArrayList<>();
        list.add("库里");
        list.add("汤普森");
        list.add("格林");
        return list;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<UserService> clz= UserService.class;
        UserService userService = clz.getConstructor().newInstance();
        System.out.println(userService.userList());
    }
}