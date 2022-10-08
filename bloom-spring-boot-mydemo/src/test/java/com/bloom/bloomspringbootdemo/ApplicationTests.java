package com.bloom.bloomspringbootdemo;

class ApplicationTests {

    public static void main(String[] args) {
        //获取时间戳
        long baseTime = System.currentTimeMillis();
        long l2 = ((baseTime - 1588262400000l) / 1000) % (1L << 32);
        System.out.println(l2);
        long l = l2 << 20;
        System.out.println(l);

        //自增码
        int i = (int) ((1L << 20) * Math.random());
        System.out.println(i);

        System.out.println(1L<<20);

        //获取机器吗
        System.out.println(392L<<52);
        long l1 = ((48819) % (1L << 20)) << 20;
        System.out.println(l1);


        System.out.println(5<<1);

        long l3 = ~(-1L << 12);
        System.out.println(4096&l3);

        System.out.println(2<<15);
    }
}
