package com.lj.redis.test;

/**
 * @Description: 线程测试
 * @ClassName: MyThread
 * @Author: liang_jun
 * @Date: 2020/8/22 17:39
 */
public class MyThread implements Runnable {

    private String name;

    public MyThread(String name) {
        this.name = name;      // 通过构造方法配置name属性
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + "运行,i=" + i);
        }
    }


}
