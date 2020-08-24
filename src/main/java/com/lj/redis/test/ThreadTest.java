package com.lj.redis.test;

/**
 * @Description:
 * @ClassName: ThreadTest
 * @Author: liang_jun
 * @Date: 2020/8/22 17:43
 */
public class ThreadTest {

    public static void main(String[] args) {
        MyThread m1 = new MyThread("A");
        MyThread m2 = new MyThread("B");
        Thread t1 = new Thread(m1);
        Thread t2 = new Thread(m2);

        t1.start();
        t2.start();


    }
}
