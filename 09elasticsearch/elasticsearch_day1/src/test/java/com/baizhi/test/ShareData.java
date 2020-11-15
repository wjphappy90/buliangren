package com.baizhi.test;
class ShareData {



    private int number = 0;

    public synchronized void add() throws InterruptedException {

        if (number == 0) {
            ++number;
            System.out.println("线程名称: " + Thread.currentThread().getName() + "\t " + number);
            this.notifyAll();
        } else {
            this.wait();
        }

    }

    public synchronized void jian() throws InterruptedException {
        if (number == 0) {
            this.wait();
        } else {
            --number;
            System.out.println("线程名称: " + Thread.currentThread().getName() + "\t " + number);
            this.notifyAll();
        }
    }
}
