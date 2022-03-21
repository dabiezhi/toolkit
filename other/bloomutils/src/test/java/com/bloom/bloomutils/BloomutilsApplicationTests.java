package com.bloom.bloomutils;

import com.bloom.bloomutils.core.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BloomutilsApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {

        System.out.println(DateUtil.formatDate("2022-03-20").getTime());
    }
}
