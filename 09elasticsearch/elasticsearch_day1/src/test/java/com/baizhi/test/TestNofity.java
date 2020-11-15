package com.baizhi.test;

public class TestNofity {

    public static void main(String[] args) {


        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    shareData.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();


    }

}

