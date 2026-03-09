package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Pinger extends ActiveComponent {
	private final Semaphore semaphore;

	public Pinger(Semaphore semaphore) {
		this.semaphore = semaphore;
	}	
	
	public void run() {
		while (true) {
			try {
				this.semaphore.acquire();
				println("ping");
			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
				this.semaphore.release();
			}
        }
	}
}