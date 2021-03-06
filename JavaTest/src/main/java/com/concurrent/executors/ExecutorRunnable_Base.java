package com.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author: King.Z <br>x
 * date:  2017/8/14 20:40 <br>
 * description: XXXXXXX <br>
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * <p>
 * 加入线程池的线程属于托管状态，[线程的运行不受加入顺序的影响]
 * Executor执行Callable任务
 */
public class ExecutorRunnable_Base {
    public static void main(String[] args) {
        //CachedThreadPoolTest.start();
        //FixedThreadPoolTest.start();
        //ScheduledThreadPoolTest.startDelay();
        SingleThreadExecutor.start();
    }

    static void printMsg(ExecutorService executor) {
        final int[] id = {0};
        for (int i = 0; i < 1; i++) {
            executor.execute(() -> {
                try {
                    while (true) {
                        id[0]++;
                        Thread.sleep(2 * 1000);
                        if (id[0] == 5) {
                            System.out.println("Finished!");
                            return;
                        }
                        System.out.println("printMsg   " + Thread.currentThread().toString() + "  " + id[0]);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}


class CachedThreadPoolTest {
    //根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们
    static void start() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorRunnable_Base.printMsg(cachedThreadPool);
    }
}

class FixedThreadPoolTest {
    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    static void start() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        ExecutorRunnable_Base.printMsg(fixedThreadPool);
    }
}

class SingleThreadExecutor {
    static void start() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorRunnable_Base.printMsg(singleThreadExecutor);
    }
}


class ScheduledThreadPoolTest {
    //创建一个定长线程池，支持定时及周期性任务执行
    static void startDelay() {
        //延迟执行
        ScheduledThreadPoolExecutor scheduledThreadPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }

    static void startDelayAndLoop() {
        //延迟制定时间后循环固定时间执行
        ScheduledThreadPoolExecutor scheduledThreadPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}

