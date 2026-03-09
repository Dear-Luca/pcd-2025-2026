package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1,true);

		new Pinger(semaphore).start();
		new Ponger(semaphore).start();
	}

}
