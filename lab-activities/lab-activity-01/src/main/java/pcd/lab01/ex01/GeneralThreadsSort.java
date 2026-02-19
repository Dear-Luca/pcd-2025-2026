package pcd.lab01.ex01;

import static pcd.lab01.ex01.SequentialSort.*;
import static pcd.lab01.ex01.SequentialSort.log;

public class GeneralThreadsSort {
    public static void main(String[] args) throws InterruptedException {
        //GENERATION
        log("Generating array");
        int[] v = genArray(VECTOR_SIZE);
        log("Array generated");

        int cores = Runtime.getRuntime().availableProcessors();
        log("Number of processors: " + cores);

        long startTime = System.nanoTime();

        // SPAWNING THREADS
        Thread[] threads = new Thread[cores];
        int threadSize = v.length / cores;
        for (int i = 0; i < cores; i++) {
            if (i == cores - 1) {
                threads[i] = new Thread(new RunnableGeneralThreads(i * threadSize, v.length, v));
            } else{
               threads[i] = new Thread(new RunnableGeneralThreads(i * threadSize, i * threadSize + threadSize, v));
            }
            threads[i].start();
        }

        // COMPLETE EXECUTION
        for (Thread t: threads){
            t.join();
        }

        // MERGING
        mergeAllRuns(v, cores);

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1_000_000;
        log("Total time = " + elapsedTime + " ms");
    }

    static void merge(int[] src, int[] dst, int left, int mid, int right) {
        int i = left, j = mid, k = left;

        while (i < mid && j < right) {
            if (src[i] <= src[j]) dst[k++] = src[i++];
            else dst[k++] = src[j++];
        }
        while (i < mid) dst[k++] = src[i++];
        while (j < right) dst[k++] = src[j++];
    }

    static void mergeAllRuns(int[] v, int cores) {
        int n = v.length;
        int baseRun = n / cores;
        int[] tmp = new int[n];

        int runSize = baseRun;
        int[] src = v;
        int[] dst = tmp;

        // Each round merges adjacent runs of size runSize into size 2*runSize
        while (runSize < n) {
            for (int left = 0; left < n; left += 2 * runSize) {
                int mid = Math.min(left + runSize, n);
                int right = Math.min(left + 2 * runSize, n);

                if (mid < right) {
                    merge(src, dst, left, mid, right);
                } else {
                    // only one run remains, just copy it
                    System.arraycopy(src, left, dst, left, right - left);
                }
            }

            // swap src/dst (ping-pong buffers)
            int[] swap = src;
            src = dst;
            dst = swap;

            runSize *= 2;
        }

        // If final data ended up in tmp, copy back into v
        if (src != v) {
            System.arraycopy(src, 0, v, 0, n);
        }
    }
}
