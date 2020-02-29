package org.lzn.threadlocal;

/**
 * 自定义线程类
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/21 17:29
 */
public class MyThread extends Thread {

    private ThreadLocal threadLocal;

    public MyThread(ThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        System.out.println("MyThread : " + threadLocal.get());
    }
}
