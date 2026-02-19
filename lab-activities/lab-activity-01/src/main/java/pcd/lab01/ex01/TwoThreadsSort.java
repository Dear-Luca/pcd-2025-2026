package pcd.lab01.ex01;

import static pcd.lab01.ex01.SequentialSort.*;

public class TwoThreadsSort {

    public static void main(String[] args) throws InterruptedException {
        //GENERATION
        log("Generating array");
        int[] v = genArray(VECTOR_SIZE);
        log("Array generated");

        //SORTING
        log("Generating array");

        // THREADS
        //thread 1
        Thread threadOne = new Thread(new RunnableTwoThreads(0, v.length / 2, v));
        //thread 2
        Thread threadTwo = new Thread(new RunnableTwoThreads(v.length / 2, v.length, v));

        log("Sorting " + VECTOR_SIZE + " elements");
        long startTime = System.nanoTime();
        threadOne.start();
        threadTwo.start();

        // complete the execution of the two threads
        //join
        threadOne.join();
        threadTwo.join();
        log("Sorting completed");
        //merge
        int[] res = new int[v.length];
        int i = 0;
        int j = v.length / 2;
        int curr = 0;

        while (i < v.length / 2 && j < v.length){
            if (v[i] <= v[j]){
                res[curr] = v[i];
                i += 1;
            } else{
                res[curr] = v[j];
                j += 1;
            }
            curr += 1;
        }

        while (i < v.length / 2){
            res[curr] = v[i];
            i += 1;
            curr += 1;
        }

        while (j < v.length){
            res[curr] = v[j];
            curr += 1;
            j += 1;
        }
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1000_000;
        log("Total time = " + elapsedTime + " ms");
        //dumpArray(res);
    }
}
