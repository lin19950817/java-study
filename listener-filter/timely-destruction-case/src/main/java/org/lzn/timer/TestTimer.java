package org.lzn.timer;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer 测试
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/03/18 22:07
 */
public class TestTimer {

    @Test
    public void test() throws Exception {
        // 创建一个计时器
        Timer t = new Timer();

        // 调度任务，延迟 2000ms，间隔 1000ms
        t.schedule(new TimerTask() {
            /**
             * 要执行的任务
             *
             * @author LinZhenNan lin_hehe@qq.com 2020-03-18 22:10
             */
            @Override
            public void run() {
                System.out.println(new Date().toLocaleString());
            }
        }, 2000, 1000);

        // junit 不支持多线程测试
        Thread.sleep(10000);
    }
}
