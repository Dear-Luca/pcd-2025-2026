package pcd.lab01.ex01;

import java.util.Arrays;

import static pcd.lab01.ex01.SequentialSort.log;

public class RunnableGeneralThreads implements Runnable{
    private final int start;
    private final int end;
    private final int[] array;

    public RunnableGeneralThreads(int start, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    public void run() {
        //log("Sorting from " + start + " to " + end);
        Arrays.sort(array, start, end);
    }
}
