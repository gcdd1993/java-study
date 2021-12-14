package io.github.gcdd1993.concurrency.chapter24;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 者线程池
 * 第一步：定义一个王者线程池：TheKingThreadPool
 *
 *
 * @author gcdd1993
 * @since 2021/12/14
 */
public class TheKingThreadPool {
    private final BlockingQueue<Task> taskQueue;
    private final List<Worker> workers = new ArrayList<>();
    private ThreadPoolStatus status;

    /**
     * 初始化构建线程池
     *
     * @param worksNumber 线程池中的工作线程数量
     * @param taskQueue   任务队列
     */
    public TheKingThreadPool(int worksNumber, BlockingQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
        status = ThreadPoolStatus.RUNNING;
        for (int i = 0; i < worksNumber; i++) {
            workers.add(new Worker("Worker" + i, taskQueue));
        }
        for (Worker worker : workers) {
            Thread workThread = new Thread(worker);
            workThread.setName(worker.getName());
            workThread.start();
        }
    }

    /**
     * 提交任务
     *
     * @param task 待执行的任务
     */
    public synchronized void execute(Task task) {
        if (!this.status.isRunning()) {
            throw new IllegalStateException("线程池非运行状态，停止接单啦~");
        }
        this.taskQueue.offer(task);
    }

    /**
     * 等待所有任务执行结束
     */
    public synchronized void waitUntilAllTasksFinished() {
        while (this.taskQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭线程池
     */
    public synchronized void shutdown() {
        this.status = ThreadPoolStatus.SHUTDOWN;
    }

    /**
     * 停止线程池
     */
    public synchronized void stop() {
        this.status = ThreadPoolStatus.SHUTDOWN;
        for (Worker worker : workers) {
            worker.doStop();
        }
    }
}

