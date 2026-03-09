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
		Semaphore ping = new Semaphore(1,true);
		Semaphore pong = new Semaphore(0,true);


		new Pinger(pong, ping).start();
		new Ponger(ping, pong).start();
	}

}
