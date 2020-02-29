package org.lzn.threadlocal;

import org.junit.Test;

/**
 * 学习使用 ThreadLocal
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/21 17:25
 */
public class ThestThreadLocal {

    /**
     * 测试多线程，但似乎 junit4 不适合测试多线程，会导致某些线程不开启。
     *
     *
     * @author LinZhenNan lin_hehe@qq.com 2020-02-24 23:01
     */
   @Test
   public void testMain() {
       ThreadLocal threadLocal = new ThreadLocal();
       threadLocal.set("p");

       MyThread myThread = new MyThread(threadLocal);
       myThread.start();

       new Thread(() -> {
           System.out.println("heheThread");
       }).start();

       System.out.println(threadLocal.get());
   }
}
