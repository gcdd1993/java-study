package io.github.gcdd1993.concurrency.chapter17;

import org.junit.jupiter.api.Test;

/**
 * @author gcdd1993
 * @since 2021/12/13
 */
class ForHelpSemaphoreTest {

    @Test
    void sendSignal() {
        ForHelpSemaphore helpSemaphore = new ForHelpSemaphore();

        Thread daqiaoThread = new Thread(helpSemaphore::sendSignal);
        Thread nezhaThread = new Thread(() -> {
            try {
                helpSemaphore.receiveSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        daqiaoThread.start();
        nezhaThread.start();
    }
}