package io.github.gcdd1993.concurrency.chapter24;

/**
 * 第三步：设计并制作任务
 *
 * @author gcdd1993
 * @since 2021/12/14
 */
public interface Task extends Runnable {
    String getTaskDesc();
}
