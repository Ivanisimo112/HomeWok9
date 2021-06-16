package com.company;

import java.util.Random;

public class MathGame {
    Random random = new Random();
    private final int[] answers;
    private final String[] tasks;
    private final int[] a;
    private final int[] b;
    private final boolean[] thisPlus;

    public MathGame(int examples, int max) {
        answers = new int[examples];
        a = new int[examples];
        for (int i = 0; i < a.length; i++) {
            if (max == 0) {
                a[i] = 0;
            } else {
                a[i] = random.nextInt(max);
            }
        }
        b = new int[examples];
        for (int i = 0; i < b.length; i++) {
            if (a[i] == 0) {
                b[i] = 0;
            } else {
                b[i] = random.nextInt(a[i]);
            }
        }
        thisPlus = new boolean[examples];
        for (int i = 0; i < thisPlus.length; i++) {
            thisPlus[i] = random.nextBoolean();
        }
        tasks = new String[examples];
        for (int i = 0; i < tasks.length; i++) {
            if (thisPlus[i]) {
                tasks[i] = "" + a[i] + " + " + b[i] + " = ";
                answers[i] = a[i] + b[i];
            } else {
                tasks[i] = "" + a[i] + " - " + b[i] + " = ";
                answers[i] = a[i] - b[i];
            }
        }
    }

    public String getTask(int i) {
        return tasks[i];
    }

    public int getAnswer(int i) {
        return answers[i];
    }
}



